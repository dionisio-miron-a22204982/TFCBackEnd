package pt.ulusofona.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.Empresa


interface EmpresaRepository : JpaRepository<Empresa, Long> {
    fun findEmpresaByNome(nome: String)
}