package pt.ulusofona.expenses.dao

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class FormacaoAcademica(
        @Id @GeneratedValue
        val id: Long = 0,

        val formacao: String = "",

        val instituto: String = "",

        @ManyToOne(optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        @JsonIgnore
        val owner: UtilizadorParticular? = null
)
