package ru.bmstu.iu7.moviesrecommendersystem.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.bmstu.iu7.moviesrecommendersystem.backend.domain.Movie
import java.util.*

interface MovieRepository : JpaRepository<Movie, UUID>
