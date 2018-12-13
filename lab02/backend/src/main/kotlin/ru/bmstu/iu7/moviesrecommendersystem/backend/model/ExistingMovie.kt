package ru.bmstu.iu7.moviesrecommendersystem.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import java.util.*

@Data
data class ExistingMovie(
        @JsonProperty(value = "id")
        val id: UUID,

        @JsonProperty(value = "title")
        val title: String
)
