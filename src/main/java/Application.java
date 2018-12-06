import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.schedule.MapSchedule;
import org.nd4j.linalg.schedule.ScheduleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Application {
    private static final Logger log =
            LoggerFactory.getLogger(Application.class);

    private static final int inWidth = 28;
    private static final int inHeight = 28;
    private static final int inDepth = 1;

    private static final int numClasses = 10;

    private static final int batchSize = 32;
    private static final int seed = 123;
    private static final int numEpochs = 1;

    public static void main(String[] args) throws Exception {
        log.info("Загрузка данных...");

        var mnistTrainSet = new MnistDataSetIterator(batchSize, true, 12345);
        var mnistTestSet = new MnistDataSetIterator(batchSize, false, 12345);

        log.info("Данные загружены");
        log.info("Построение модели...");

        var lrSchedule = new HashMap<Integer, Double>();
        lrSchedule.put(0, 0.06);
        lrSchedule.put(200, 0.05);
        lrSchedule.put(600, 0.028);
        lrSchedule.put(800, 0.0060);
        lrSchedule.put(1000, 0.001);

        var conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .l2(0.0005)
                .updater(new Nesterovs(new MapSchedule(ScheduleType.ITERATION, lrSchedule)))
                .weightInit(WeightInit.XAVIER)
                .list()
                .layer(0, new ConvolutionLayer.Builder(5, 5)
                        .nIn(inDepth)
                        .stride(1, 1)
                        .nOut(20)
                        .activation(Activation.IDENTITY)
                        .build())
                .layer(1, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build())
                .layer(2, new ConvolutionLayer.Builder(5, 5)
                        .stride(1, 1) // nIn need not specified in later layers
                        .nOut(50)
                        .activation(Activation.IDENTITY)
                        .build())
                .layer(3, new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build())
                .layer(4, new DenseLayer.Builder().activation(Activation.RELU)
                        .nOut(500).build())
                .layer(5, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(numClasses)
                        .activation(Activation.SOFTMAX)
                        .build())
                .setInputType(InputType.convolutionalFlat(inHeight, inWidth, inDepth))
                .build();

        var net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(10));

        log.info("Модель построена");
        log.info("Число обучаемых параметров: {}", net.numParams());

        for (var i = 0; i < numEpochs; ++i) {
            log.info("Обучение. Эпоха {}", i);

            net.fit(mnistTrainSet);

            log.info("Оценка модели...");

            var eval = new Evaluation(numClasses);
            while (mnistTestSet.hasNext()) {
                var set = mnistTestSet.next();
                var output = net.output(set.getFeatures(), false);
                eval.eval(set.getLabels(), output);
            }
            mnistTestSet.reset();

            log.info(eval.stats());
        }
    }
}
