package pt.ulusofona.expenses.dao

import jakarta.persistence.*

@Entity
class HashingAlgorithm(
        @Id @GeneratedValue
        @Column(name = "HashAlgorithmId")
        val hashAlgorithmId: Long = 0,

        @Column(name = "AlgorithmName")
        val algorithmName: String = ""
)