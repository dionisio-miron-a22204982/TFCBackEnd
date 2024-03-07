package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.Competencias
import pt.ulusofona.expenses.repository.CompetenciasRepository

@RestController
@RequestMapping("/api/comentarios")
class CompetenciasController(private val competenciasRepository: CompetenciasRepository) {
    @GetMapping("/search/{input}")
    fun getUserById(@PathVariable input: String): ResponseEntity<out Any> {
        if (input.all { it.isDigit() }){
            val competencias: Competencias =
                    competenciasRepository.findByIdOrNull(input.toLong())
                            ?: throw IllegalArgumentException("Competencia não encontrada.")
            return ResponseEntity(competencias, HttpStatus.OK)
        } else {
            val tipo_de_competencia: List<Competencias> = competenciasRepository.findAll()
                    .filter { it.tipo.contains(input, ignoreCase = true)}
            return ResponseEntity(tipo_de_competencia, HttpStatus.OK)
        }
    }
}