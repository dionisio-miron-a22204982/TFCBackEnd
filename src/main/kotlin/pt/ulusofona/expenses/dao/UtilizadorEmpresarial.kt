package pt.ulusofona.expenses.dao

import jakarta.persistence.*
import java.util.Date

@Entity
class UtilizadorEmpresarial(
        @Id
        @GeneratedValue
        val id: Long = 0,

        var name: String = "",

        var username: String = "",

        var password: String = "",

        var email: String = "",

        var contacto: String = "",

        var profissao: String = "",

        var empresa: String = "",

        @Temporal(TemporalType.DATE)
        @Column(name = "data_de_nascimento")
        var dataDeNascimento: Date? = null,


        var permissoes: Long = 0
)