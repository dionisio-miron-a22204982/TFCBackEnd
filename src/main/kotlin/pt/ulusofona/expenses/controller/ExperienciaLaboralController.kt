package pt.ulusofona.expenses.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.ExperienciaLaboral
import pt.ulusofona.expenses.repository.ExperienciaLaboralRepository
import pt.ulusofona.expenses.request.SearchExperienciaLaboralRequest

@RestController
@RequestMapping("/api/experiencias-laborais")
class ExperienciaLaboralController(private val experienciaLaboralRepository: ExperienciaLaboralRepository) {

    @GetMapping("/search/{id}")
    fun getExperienciaLaboralById(@RequestBody experienciaLaboral: SearchExperienciaLaboralRequest): ResponseEntity<Any> {
        val experiencia = experienciaLaboralRepository.findById(experienciaLaboral.id)
        return if (experiencia.isPresent) {
            ResponseEntity(experiencia.get(), HttpStatus.OK)
        } else {
            ResponseEntity("Experiencia laboral não encontrada", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/add/{experiencia}")
    fun addExperienciaLaboral(@RequestBody experienciaLaboral: SearchExperienciaLaboralRequest): ResponseEntity<ExperienciaLaboral> {
        val savedExperienciaLaboral = experienciaLaboralRepository.save(experienciaLaboral.experiencia)
        return ResponseEntity(savedExperienciaLaboral, HttpStatus.CREATED)
    }

    @PutMapping("/edit/{id}")
    fun updateExperienciaLaboral(@RequestBody experienciaLaboral: SearchExperienciaLaboralRequest): ResponseEntity<ExperienciaLaboral> {
        return if (experienciaLaboralRepository.existsById(experienciaLaboral.id)) {
            val updatedExperienciaLaboral = experienciaLaboralRepository.save(experienciaLaboral.experiencia)
            ResponseEntity(updatedExperienciaLaboral, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteExperienciaLaboral(@RequestBody experienciaLaboral: SearchExperienciaLaboralRequest): ResponseEntity<Any> {
        return if (experienciaLaboralRepository.existsById(experienciaLaboral.id)) {
            experienciaLaboralRepository.deleteById(experienciaLaboral.id)
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity("Experiencia laboral não encontrada", HttpStatus.NOT_FOUND)
        }
    }
}
