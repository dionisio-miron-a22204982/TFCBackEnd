package pt.ulusofona.expenses.dao

import jakarta.persistence.*

@Entity
class Propostas (
    @Id @GeneratedValue
    val id: Long = 0,

    val comentarios: String? = null,

    val area: String = "",

    @ManyToOne
    @JoinColumn(name = "author_id")
    val author: UtilizadorParticular? = null,

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    val candidato: UtilizadorParticular? = null,

    @Lob
    val ficheiros: ByteArray? = null
)