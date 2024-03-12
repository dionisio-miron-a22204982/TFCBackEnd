package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.Competencias
import pt.ulusofona.expenses.repository.CompetenciasRepository
import pt.ulusofona.expenses.request.SearchCompetenciasRequest

@RestController
@RequestMapping("/api/comentarios")
class CompetenciasController(private val competenciasRepository: CompetenciasRepository) {
    @GetMapping("/search/{input}")
    fun getCompetenciasByIdOrType(@RequestBody request: SearchCompetenciasRequest): ResponseEntity<out Any> {

        val competencia = competenciasRepository.findByIdOrNull(request.id)

        return if (competencia != null) {
            ResponseEntity(competencia, HttpStatus.OK)
        } else {
            val tipoDeCompetencia: List<Competencias> = competenciasRepository.findAll()
                    .filter { it.tipo.contains(request.tipo, ignoreCase = true)}
            return ResponseEntity(tipoDeCompetencia, HttpStatus.OK)
        }
    }
}