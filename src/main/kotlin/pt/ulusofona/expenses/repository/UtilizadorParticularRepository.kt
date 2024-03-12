package pt.ulusofona.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.UtilizadorParticular


interface UtilizadorParticularRepository : JpaRepository<UtilizadorParticular, Long> {

    fun findUtilizadorParticularByNome(nome: String)
}

