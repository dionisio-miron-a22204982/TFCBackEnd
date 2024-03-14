package pt.ulusofona.expenses.repository

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.ExperienciaLaboral


interface ExperienciaLaboralRepository : JpaRepository<ExperienciaLaboral, Long> {

}
