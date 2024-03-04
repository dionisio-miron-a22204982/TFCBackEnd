package pt.ulusofona.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.UtilizadorParticular
import java.util.*


interface UtilizadorParticularRepository : JpaRepository<UtilizadorParticular, Long> {

    fun findByEmail(email: String): UtilizadorParticular?
}

