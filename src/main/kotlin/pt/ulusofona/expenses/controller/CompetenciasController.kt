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
    @GetMapping("/searchID/{id}")
    fun getCompetenciasByIdOrType(@PathVariable id: SearchCompetenciasRequest): ResponseEntity<out Any> {

        val competencia = competenciasRepository.findByIdOrNull(id.id)

        return if (competencia != null) {
            ResponseEntity(competencia, HttpStatus.OK)
        } else {
            val tipoDeCompetencia: List<Competencias> = competenciasRepository.findAll()
                    .filter { it.tipo.contains(id.tipo, ignoreCase = true)}
            return ResponseEntity(tipoDeCompetencia, HttpStatus.OK)
        }
    }
}