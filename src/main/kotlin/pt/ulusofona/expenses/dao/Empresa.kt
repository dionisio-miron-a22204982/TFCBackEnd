package pt.ulusofona.expenses.dao

import jakarta.persistence.*

@Entity
class Empresa(
        @Id @GeneratedValue
        val id: Long = 0,

        val nome: String = "",

        val email: String = "",

        val contacto: Long = 0
)
