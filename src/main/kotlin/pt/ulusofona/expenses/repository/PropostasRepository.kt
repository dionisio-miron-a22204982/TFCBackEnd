package pt.ulusofona.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.Propostas


interface PropostasRepository : JpaRepository<Propostas, Long> {

}