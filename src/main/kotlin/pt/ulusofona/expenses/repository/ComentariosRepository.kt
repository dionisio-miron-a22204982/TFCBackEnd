package pt.ulusofona.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.Comentarios


interface ComentariosRepository : JpaRepository<Comentarios, Long> {

}