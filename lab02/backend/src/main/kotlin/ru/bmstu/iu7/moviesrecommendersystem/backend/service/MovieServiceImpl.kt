package ru.bmstu.iu7.moviesrecommendersystem.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.bmstu.iu7.moviesrecommendersystem.backend.domain.Movie
import ru.bmstu.iu7.moviesrecommendersystem.backend.exception.NotFoundException
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.ExistingMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.NewMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.SearchParameters
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.SearchResultItem
import ru.bmstu.iu7.moviesrecommendersystem.backend.repository.MovieRepository
import java.util.*

@Service
class MovieServiceImpl(
        @Autowired
        val movieRepository: MovieRepository) : MovieService {
    override fun addMovie(newMovie: NewMovie): ExistingMovie {
        var movie = this.convert(newMovie)
        movie = this.movieRepository.save(movie)
        return this.convert(movie)
    }

    override fun getMovie(movieId: UUID): ExistingMovie {
        val movie = this.movieRepository.findById(movieId)
                .orElseThrow { NotFoundException("Movie Not Found") }
        return this.convert(movie)
    }

    override fun getMovies(searchParameters: SearchParameters): Iterable<SearchResultItem> {
        TODO()
    }

    private fun convert(newMovie: NewMovie): Movie {
        return Movie(
                title = newMovie.title,
                year = newMovie.year,
                country = newMovie.country,
                genre = newMovie.genre
        )
    }

    private fun convert(movie: Movie): ExistingMovie {
        return ExistingMovie(
                id = movie.id!!,
                title = movie.title,
                year = movie.year,
                country = movie.country,
                genre = movie.genre
        )
    }
}
