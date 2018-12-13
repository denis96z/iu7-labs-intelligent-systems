package ru.bmstu.iu7.moviesrecommendersystem.backend.model

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.JsonPropertyName
import java.util.*

@Data
data class SearchResultItem (
        @JsonProperty(value = JsonPropertyName.MOVIE_ID)
        val movieId: UUID,

        @JsonProperty(value = JsonPropertyName.MATCH_MARK)
        val matchMark: Float
)
