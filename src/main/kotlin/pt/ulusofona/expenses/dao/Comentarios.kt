package pt.ulusofona.expenses.dao

import jakarta.persistence.*

import java.util.Date

@Entity
class Comentarios (
    @Id @GeneratedValue
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: UtilizadorParticular? = null,

    @Column
    val data: Date? = null,

    @Column
    val comentario: String = ""
)
