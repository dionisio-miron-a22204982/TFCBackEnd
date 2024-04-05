package pt.ulusofona.expenses.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.FormacaoAcademica
import pt.ulusofona.expenses.repository.FormacaoAcademicaRepository
import pt.ulusofona.expenses.request.SearchFormacaoAcademica

@RestController
@RequestMapping("/api/formacaoacademica")
class FormacaoAcademicaController(private val formacaoAcademicaRepository: FormacaoAcademicaRepository) {

    @GetMapping("/search/{id}")
    fun getFormacaoAcademicaById(@PathVariable id : SearchFormacaoAcademica): ResponseEntity<Any> {
        val academica = formacaoAcademicaRepository.findById(id.id)
        return if (academica.isPresent) {
            ResponseEntity(academica.get(), HttpStatus.OK)
        } else {
            ResponseEntity("Formação acadêmica não encontrada", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/add/{id}")
    fun addFormacaoAcademica(@PathVariable id : SearchFormacaoAcademica): ResponseEntity<FormacaoAcademica> {
        val savedFormacaoAcademica = formacaoAcademicaRepository.save(id.formacaoAcademica)
        return ResponseEntity(savedFormacaoAcademica, HttpStatus.CREATED)
    }

    @PutMapping("/upd/{id}")
    fun updateFormacaoAcademica(@PathVariable id : SearchFormacaoAcademica): ResponseEntity<FormacaoAcademica> {
        return if (formacaoAcademicaRepository.existsById(id.id)) {
            val updatedFormacaoAcademica = formacaoAcademicaRepository.save(id.formacaoAcademica)
            ResponseEntity(updatedFormacaoAcademica, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteFormacaoAcademica(@PathVariable id : SearchFormacaoAcademica): ResponseEntity<Any> {
        return if (formacaoAcademicaRepository.existsById(id.id)) {
            formacaoAcademicaRepository.deleteById(id.id)
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity("Formação acadêmica não encontrada", HttpStatus.NOT_FOUND)
        }
    }
}
