package ru.bmstu.iu7.moviesrecommendersystem.backend.domain

import lombok.Data
import lombok.experimental.Accessors
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Data
@Accessors(chain = true)
@Entity
data class Movie(
        @Id
        @GeneratedValue
        var id: UUID? = null,

        var title: String,
        var year: Int
)
