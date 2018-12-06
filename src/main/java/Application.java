import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.split.FileSplit;
import org.datavec.image.loader.NativeImageLoader;
import org.datavec.image.recordreader.ImageRecordReader;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.modelimport.keras.preprocessors.ReshapePreprocessor;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private static final int INPUT_WIDTH = 28;
    private static final int INPUT_HEIGHT = 28;
    private static final int INPUT_NUM_CHANNELS = 1;

    private static final int NUM_CLASSES = 10;

    private static final int NUM_EPOCHS = 1;

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            log.error("Datasets path required!");
            return;
        }

        final var basePath = args[0];
        var trIterator = prepareDataset(basePath + "/training");
        var tsIterator = prepareDataset(basePath + "/testing");


        var configArg = "--simple";
        if (args.length == 2) {
            configArg = args[1];
        }

        MultiLayerConfiguration config;
        switch (configArg) {
            case "--simple":
                config = configSimple();
                config.setInputPreProcessors(new HashMap<>() {{
                    put(0, new ReshapePreprocessor(new long[]{
                            INPUT_NUM_CHANNELS,
                            INPUT_WIDTH, INPUT_HEIGHT
                    }, new long[]{
                            INPUT_NUM_CHANNELS * INPUT_WIDTH * INPUT_HEIGHT
                    }));
                }});
                break;

            case "--LeNet":
                config = configLeNet();
                break;

            default:
                log.error("Unknown configuration!");
                return;
        }

        var model = prepareModel(config);
        log.info("Number of trained parameters: {}", model.numParams());

        for (var i = 0; i < NUM_EPOCHS; i++) {
            log.info("---------- EPOCH {} ----------", i + 1);
            log.info("Training...");

            model.fit(trIterator);

            log.info("Testing...");

            var eval = model.evaluate(tsIterator);

            log.info(eval.stats());

            trIterator.reset();
            tsIterator.reset();
        }
    }

    private static RecordReaderDataSetIterator prepareDataset(final String path) throws Exception {
        final var batchSize = 64;
        final var randSeed = 1234;

        var random = new Random(randSeed);

        var data = new File(path);
        var split = new FileSplit(data, NativeImageLoader.ALLOWED_FORMATS, random);

        var labelMaker = new ParentPathLabelGenerator();
        var reader = new ImageRecordReader(INPUT_HEIGHT, INPUT_WIDTH, INPUT_NUM_CHANNELS, labelMaker);
        reader.initialize(split);

        var iterator = new RecordReaderDataSetIterator(reader, batchSize, 1, NUM_CLASSES);

        var scaler = new ImagePreProcessingScaler(0, 1);
        scaler.fit(iterator);
        iterator.setPreProcessor(scaler);

        return iterator;
    }

    private static MultiLayerNetwork prepareModel(MultiLayerConfiguration conf) {
        var net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(10));
        return net;
    }

    private static MultiLayerConfiguration configSimple() {
        return new NeuralNetConfiguration.Builder()
                .seed(1234)
                .l2(0.01)
                .updater(new Sgd())
                .weightInit(WeightInit.SIGMOID_UNIFORM)
                .list()
                .layer(0, new DenseLayer.Builder()
                        .activation(Activation.RELU)
                        .nOut(256)
                        .build())
                .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nOut(NUM_CLASSES)
                        .build())
                .setInputType(InputType.feedForward(INPUT_HEIGHT * INPUT_WIDTH * INPUT_NUM_CHANNELS))
                .build();
    }

    private static MultiLayerConfiguration configLeNet() {
        return new NeuralNetConfiguration.Builder()
                .updater(new Adam())
                .weightInit(WeightInit.NORMAL)
                .list()
                .layer(0, new ConvolutionLayer.Builder()
                        .nIn(INPUT_NUM_CHANNELS)
                        .kernelSize(5, 5)
                        .stride(1, 1)
                        .nOut(20)
                        .activation(Activation.RELU)
                        .build())
                .layer(1, new SubsamplingLayer.Builder()
                        .poolingType(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build())
                .layer(2, new ConvolutionLayer.Builder()
                        .kernelSize(5, 5)
                        .stride(1, 1)
                        .nOut(50)
                        .activation(Activation.RELU)
                        .build())
                .layer(3, new SubsamplingLayer.Builder()
                        .poolingType(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build())
                .layer(4, new DenseLayer.Builder()
                        .nOut(500)
                        .activation(Activation.RELU)
                        .build())
                .layer(5, new OutputLayer.Builder()
                        .lossFunction(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(NUM_CLASSES)
                        .activation(Activation.SOFTMAX)
                        .build())
                .setInputType(InputType.convolutionalFlat(INPUT_HEIGHT, INPUT_WIDTH, INPUT_NUM_CHANNELS))
                .build();
    }
}
