package pt.ulusofona.expenses.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.FormacaoAcademica
import pt.ulusofona.expenses.repository.FormacaoAcademicaRepository
import pt.ulusofona.expenses.request.SearchFormacaoAcademica

@RestController
@RequestMapping("/api/formacao-academica")
class FormacaoAcademicaController(private val formacaoAcademicaRepository: FormacaoAcademicaRepository) {

    @GetMapping("/search/{id}")
    fun getFormacaoAcademicaById(@RequestBody formacaoAcademica: SearchFormacaoAcademica): ResponseEntity<Any> {
        val academica = formacaoAcademicaRepository.findById(formacaoAcademica.id)
        return if (academica.isPresent) {
            ResponseEntity(academica.get(), HttpStatus.OK)
        } else {
            ResponseEntity("Formação acadêmica não encontrada", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/add")
    fun addFormacaoAcademica(@RequestBody formacaoAcademica: SearchFormacaoAcademica): ResponseEntity<FormacaoAcademica> {
        val savedFormacaoAcademica = formacaoAcademicaRepository.save(formacaoAcademica.formacaoAcademica)
        return ResponseEntity(savedFormacaoAcademica, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateFormacaoAcademica(@RequestBody formacaoAcademica: SearchFormacaoAcademica): ResponseEntity<FormacaoAcademica> {
        return if (formacaoAcademicaRepository.existsById(formacaoAcademica.id)) {
            val updatedFormacaoAcademica = formacaoAcademicaRepository.save(formacaoAcademica.formacaoAcademica)
            ResponseEntity(updatedFormacaoAcademica, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteFormacaoAcademica(@RequestBody formacaoAcademica: SearchFormacaoAcademica): ResponseEntity<Any> {
        return if (formacaoAcademicaRepository.existsById(formacaoAcademica.id)) {
            formacaoAcademicaRepository.deleteById(formacaoAcademica.id)
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity("Formação acadêmica não encontrada", HttpStatus.NOT_FOUND)
        }
    }
}
