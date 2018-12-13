package ru.bmstu.iu7.moviesrecommendersystem.backend.service

import ru.bmstu.iu7.moviesrecommendersystem.backend.model.ExistingMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.NewMovie

interface MovieService {
    fun addMovie(newMovie: NewMovie) : ExistingMovie
}
