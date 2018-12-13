package ru.bmstu.iu7.moviesrecommendersystem.backend.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.ExistingMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.NewMovie
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.SearchParameters
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.SearchResultItem
import ru.bmstu.iu7.moviesrecommendersystem.backend.service.MovieService
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/movies")
class MovieController(
        @Autowired
        private val movieService: MovieService) {
    @PostMapping
    fun addMovie(@Valid @RequestBody newMovie: NewMovie): ResponseEntity<ExistingMovie?> {
        val result = this.movieService.addMovie(newMovie)
        val uri = MvcUriComponentsBuilder
                .fromController(this.javaClass).path("/{id}")
                .buildAndExpand(result.id).toUri()
        return ResponseEntity.created(uri).body(result)
    }

    @GetMapping("/{id}")
    fun getMovie(@PathVariable("id") movieId: UUID): ResponseEntity<ExistingMovie?> {
        val result = this.movieService.getMovie(movieId)
        return ResponseEntity.ok(result)
    }

    @GetMapping
    fun getMovies(@Valid @RequestBody searchParameters: SearchParameters): ResponseEntity<Iterable<SearchResultItem>> {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build()
    }
}
