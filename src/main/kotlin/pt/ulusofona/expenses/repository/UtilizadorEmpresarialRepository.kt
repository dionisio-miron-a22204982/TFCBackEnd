package pt.ulusofona.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.UtilizadorEmpresarial

interface UtilizadorEmpresarialRepository : JpaRepository<UtilizadorEmpresarial, Long> {
    fun findUtilizadorEmpresarialsByNome(nome: String)
}
