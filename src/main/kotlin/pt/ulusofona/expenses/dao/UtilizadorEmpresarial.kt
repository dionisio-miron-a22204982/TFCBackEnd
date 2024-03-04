package pt.ulusofona.expenses.dao

import jakarta.persistence.*
import java.util.Date

@Entity
class UtilizadorEmpresarial(
        @Id
        @GeneratedValue
        val id: Long = 0,

        var nome: String = "",

        var email: String = "",

        @Column(name = "contacto_telefonico")
        var contactoTelefonico: Int = 0,

        var utilizador: String = "",

        var password: String = "",

        @Temporal(TemporalType.DATE)
        @Column(name = "data_de_nascimento")
        var dataDeNascimento: Date? = null,

        @ManyToOne
        @JoinColumn(name = "empresa_id")
        var empresa: Empresa? = null,

        @ManyToOne
        @JoinColumn(name = "hash_algorithm_id")
        var hashAlgorithm: HashingAlgorithm? = null,

        var permissoes: Long = 0
)