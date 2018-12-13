package ru.bmstu.iu7.moviesrecommendersystem.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data

@Data
data class NewMovie(
        @JsonProperty(value = "title", required = true)
        val title: String,

        @JsonProperty(value = "year", required = true)
        val year: Int
)
