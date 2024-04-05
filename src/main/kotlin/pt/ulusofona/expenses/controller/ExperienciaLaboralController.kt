package pt.ulusofona.expenses.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.ExperienciaLaboral
import pt.ulusofona.expenses.repository.ExperienciaLaboralRepository
import pt.ulusofona.expenses.request.SearchExperienciaLaboralRequest

@RestController
@RequestMapping("/api/experienciaslaborais")
class ExperienciaLaboralController(private val experienciaLaboralRepository: ExperienciaLaboralRepository) {

    @GetMapping("/search/{id}")
    fun getExperienciaLaboralById(@PathVariable id : SearchExperienciaLaboralRequest): ResponseEntity<Any> {
        val experiencia = experienciaLaboralRepository.findById(id.id)

        return if (experiencia.isPresent) {
            ResponseEntity(experiencia.get(), HttpStatus.OK)
        } else {
            ResponseEntity("Experiencia laboral não encontrada.", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/add/{experiencia}")
    fun addExperienciaLaboral(@PathVariable experiencia : SearchExperienciaLaboralRequest): ResponseEntity<ExperienciaLaboral> {
        val savedExperienciaLaboral = experienciaLaboralRepository.save(experiencia.experiencia)

        return ResponseEntity(savedExperienciaLaboral, HttpStatus.CREATED)
    }

    @PutMapping("/edit/{id}")
    fun updateExperienciaLaboral(@PathVariable id : SearchExperienciaLaboralRequest): ResponseEntity<ExperienciaLaboral> {

        return if (experienciaLaboralRepository.existsById(id.id)) {
            val updatedExperienciaLaboral = experienciaLaboralRepository.save(id.experiencia)
            ResponseEntity(updatedExperienciaLaboral, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteExperienciaLaboral(@PathVariable id : SearchExperienciaLaboralRequest): ResponseEntity<Any> {

        return if (experienciaLaboralRepository.existsById(id.id)) {
            experienciaLaboralRepository.deleteById(id.id)
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity("Experiencia laboral não encontrada", HttpStatus.NOT_FOUND)
        }
    }
}
