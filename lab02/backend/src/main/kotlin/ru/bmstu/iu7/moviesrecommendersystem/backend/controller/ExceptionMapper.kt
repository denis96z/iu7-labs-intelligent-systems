package ru.bmstu.iu7.moviesrecommendersystem.backend.controller

import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.bmstu.iu7.moviesrecommendersystem.backend.model.Error as ErrorModel

@RestControllerAdvice
class ExceptionMapper {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<ErrorModel> {
        val error = ErrorModel(exception.message!!)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
