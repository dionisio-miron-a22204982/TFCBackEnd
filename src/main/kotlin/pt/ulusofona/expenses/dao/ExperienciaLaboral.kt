package pt.ulusofona.expenses.dao

import jakarta.persistence.*
import java.util.Date

@Entity
class ExperienciaLaboral(
        @Id @GeneratedValue
        val id: Long = 0,

        val informacao: String = "",

        val cidade: String = "",

        val empresa: String = "",

        @Temporal(TemporalType.TIMESTAMP)
        val duracao: Date? = null,

        val pais: String = ""
)