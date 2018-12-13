package ru.bmstu.iu7.moviesrecommendersystem.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bmstu.iu7.moviesrecommendersystem.backend.domain.Movie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.ExistingMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.NewMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.repository.MovieRepository

@Service
class MovieServiceImpl(
        @Autowired
        val movieRepository: MovieRepository) : MovieService {
    override fun addMovie(newMovie: NewMovie): ExistingMovie {
        var movie = this.convert(newMovie)
        movie = this.movieRepository.save(movie)
        return this.convert(movie)
    }

    private fun convert(newMovie: NewMovie) : Movie {
        return Movie(
                title = newMovie.title,
                year = newMovie.year
        )
    }

    private fun convert(movie: Movie): ExistingMovie {
        return ExistingMovie(
                id = movie.id!!,
                title = movie.title
        )
    }
}
