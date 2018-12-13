package ru.bmstu.iu7.moviesrecommendersystem.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.JsonPropertyName
import java.util.*

@Data
data class ExistingMovie(
        @JsonProperty(value = JsonPropertyName.ID)
        val id: UUID,

        @JsonProperty(value = JsonPropertyName.TITLE)
        var title: String,

        @JsonProperty(value = JsonPropertyName.YEAR)
        var year: Int,

        @JsonProperty(value = JsonPropertyName.COUNTRY)
        var country: String,

        @JsonProperty(value = JsonPropertyName.GENRE)
        var genre: String
)
