package ru.bmstu.iu7.moviesrecommendersystem.backend.domain

import lombok.Data
import lombok.experimental.Accessors
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.ColumnName
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.TableName
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Data
@Accessors(chain = true)
@Entity
@Table(name = TableName.MOVIE)
data class Movie(
        @Id
        @GeneratedValue
        @Column(name = ColumnName.ID)
        var id: UUID? = null,

        @Column(name = ColumnName.TITLE)
        var title: String,

        @Column(name = ColumnName.YEAR)
        @Min(value = 1900)
        @Max(value = 2100)
        var year: Int,

        @Column(name = ColumnName.GENRE)
        var genre: String,

        @Column(name = ColumnName.RATING)
        @Min(value = 0)
        @Max(value = 0)
        var rating: Int
)
