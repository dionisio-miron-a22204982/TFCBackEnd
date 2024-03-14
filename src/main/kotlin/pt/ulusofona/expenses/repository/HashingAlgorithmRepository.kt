package pt.ulusofona.expenses.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.HashingAlgorithm

interface HashingAlgorithmRepository : JpaRepository<HashingAlgorithm, Long> {
    // You can add custom query methods here if needed
}
