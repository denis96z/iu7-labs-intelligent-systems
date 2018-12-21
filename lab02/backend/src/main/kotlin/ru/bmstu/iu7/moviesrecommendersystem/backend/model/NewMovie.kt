package ru.bmstu.iu7.moviesrecommendersystem.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.JsonPropertyName

@Data
data class NewMovie(
        @JsonProperty(value = JsonPropertyName.TITLE, required = true)
        var title: String,

        @JsonProperty(value = JsonPropertyName.YEAR, required = true)
        var year: Int,

        @JsonProperty(value = JsonPropertyName.GENRE, required = true)
        var genre: String,

        @JsonProperty(value = JsonPropertyName.RATING, required = true)
        var rating: Int
)
