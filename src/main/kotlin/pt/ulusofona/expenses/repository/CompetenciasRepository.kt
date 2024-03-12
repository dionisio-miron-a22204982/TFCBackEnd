package pt.ulusofona.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.Competencias


interface CompetenciasRepository : JpaRepository<Competencias, Long> {
    fun findCompetenciasByTipo(tipo : String)

}