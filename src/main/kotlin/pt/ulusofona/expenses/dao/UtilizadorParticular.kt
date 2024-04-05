package pt.ulusofona.expenses.dao

import jakarta.persistence.*
import java.util.Date

@Entity
class UtilizadorParticular(
        @Id
        @GeneratedValue
        val id: Long = 0,

        var email: String = "",

        var nome: String = "",

        var profissao: String = "",

        var contacto: String = "",

        @Temporal(TemporalType.DATE)
        @Column(name = "data_nascimento")
        var dataNascimento: Date? = null,

        var password: String = "",

        @ManyToOne
        @JoinColumn(name = "formacao_academica_id")
        var formacaoAcademica: FormacaoAcademica? = null,

        @ManyToOne
        @JoinColumn(name = "competencias_id")
        var competencias: Competencias? = null,

        @ManyToOne
        @JoinColumn(name = "experiencias_id")
        var experiencias: ExperienciaLaboral? = null,

        @ManyToOne
        @JoinColumn(name = "proposta_id")
        var proposta: Propostas? = null,

        @ManyToOne
        @JoinColumn(name = "hash_algorithm_id")
        var hashAlgorithm: HashingAlgorithm? = null
)
