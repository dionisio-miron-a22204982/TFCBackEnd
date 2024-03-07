package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.Comentarios
import pt.ulusofona.expenses.repository.ComentariosRepository

@RestController
@RequestMapping("/api/comentarios")
class ComentariosController(private val comentariosRepository: ComentariosRepository) {
    @GetMapping("/search/{input}")
    fun getUserById(@PathVariable input: String): ResponseEntity<Comentarios> {
        if (input.all { it.isDigit() }){
            val comentarios: Comentarios =
                    comentariosRepository.findByIdOrNull(input.toLong())
                            ?: throw IllegalArgumentException("Comentario não existe.")
            return ResponseEntity(comentarios, HttpStatus.OK)
        } else {
            throw IllegalArgumentException("O Id deve ser do tipo numerico.")
        }
    }
}