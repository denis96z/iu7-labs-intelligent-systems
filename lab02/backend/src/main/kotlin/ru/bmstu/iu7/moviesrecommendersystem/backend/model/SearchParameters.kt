package ru.bmstu.iu7.moviesrecommendersystem.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.JsonPropertyValue

@Data
data class SearchParameters(
        @JsonProperty(value = JsonPropertyValue.YEAR)
        val year: Int? = null,

        @JsonProperty(value = JsonPropertyValue.COUNTRY)
        val country: String? = null,

        @JsonProperty(value = JsonPropertyValue.GENRE)
        val genre: String? = null
)
