package pt.ulusofona.expenses.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.Propostas
import pt.ulusofona.expenses.repository.PropostasRepository
import pt.ulusofona.expenses.request.SearchPropostasRequest

@RestController
@RequestMapping("/api/propostas")
class PropostasController(private val propostasRepository: PropostasRepository) {

    @GetMapping("/search/{id}")
    fun getPropostaById(@PathVariable id: SearchPropostasRequest): ResponseEntity<Any> {
        val proposta = propostasRepository.findById(id.id)
        return if (proposta.isPresent) {
            ResponseEntity(proposta.get(), HttpStatus.OK)
        } else {
            ResponseEntity("Proposta not found", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/add/{id}")
    fun addProposta(@PathVariable id: SearchPropostasRequest): ResponseEntity<Propostas> {
        val savedProposta = propostasRepository.save(id.proposta)
        return ResponseEntity(savedProposta, HttpStatus.CREATED)
    }

    @PutMapping("/update/{id}")
    fun updateProposta(@PathVariable id: SearchPropostasRequest): ResponseEntity<Propostas> {
        return if (propostasRepository.existsById(id.id)) {
            val updatedProposta = propostasRepository.save(id.proposta)
            ResponseEntity(updatedProposta, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteProposta(@PathVariable id: SearchPropostasRequest): ResponseEntity<Any> {
        return if (propostasRepository.existsById(id.id)) {
            propostasRepository.deleteById(id.id)
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity("Proposta not found", HttpStatus.NOT_FOUND)
        }
    }
}
