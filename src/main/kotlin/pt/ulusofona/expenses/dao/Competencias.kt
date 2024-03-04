package pt.ulusofona.expenses.dao

import jakarta.persistence.*

@Entity
class Competencias(
        @Id @GeneratedValue
        val id: Long = 0,

        val tipo: String = "",

        val nome: String = ""
)