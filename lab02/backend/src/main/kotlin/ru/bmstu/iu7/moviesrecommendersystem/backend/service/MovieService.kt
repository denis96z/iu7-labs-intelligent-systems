package ru.bmstu.iu7.moviesrecommendersystem.backend.service

import ru.bmstu.iu7.moviesrecommendersystem.backend.model.ExistingMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.NewMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.SearchParameters
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.SearchResultItem
import java.util.*

interface MovieService {
    fun addMovie(newMovie: NewMovie): ExistingMovie
    fun getMovie(movieId: UUID): ExistingMovie
    fun getMovies(searchParameters: SearchParameters): Iterable<SearchResultItem>
}
