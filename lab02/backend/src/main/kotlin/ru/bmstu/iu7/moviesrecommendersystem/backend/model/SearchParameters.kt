package ru.bmstu.iu7.moviesrecommendersystem.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.JsonPropertyName

@Data
data class SearchParameters(
        @JsonProperty(value = JsonPropertyName.YEAR)
        val year: Int? = null,

        @JsonProperty(value = JsonPropertyName.COUNTRY)
        val country: String? = null,

        @JsonProperty(value = JsonPropertyName.GENRE)
        val genre: String? = null
)
