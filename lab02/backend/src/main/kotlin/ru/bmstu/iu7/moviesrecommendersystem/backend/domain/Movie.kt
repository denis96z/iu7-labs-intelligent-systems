package ru.bmstu.iu7.moviesrecommendersystem.backend.domain

import lombok.Data
import lombok.experimental.Accessors
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.ColumnName
import ru.bmstu.iu7.moviesrecommendersystem.backend.constant.TableName
import java.util.*
import javax.persistence.*

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
        var year: Int,

        @Column(name = ColumnName.COUNTRY)
        var country: String,

        @Column(name = ColumnName.GENRE)
        var genre: String
)
