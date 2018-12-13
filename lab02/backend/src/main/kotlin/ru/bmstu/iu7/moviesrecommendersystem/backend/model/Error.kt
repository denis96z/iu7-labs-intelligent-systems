package ru.bmstu.iu7.moviesrecommendersystem.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.JsonPropertyName

@Data
data class Error (
        @JsonProperty(value = JsonPropertyName.MESSAGE)
        val message: String
)
