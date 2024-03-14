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
    fun getPropostaById(@RequestBody propostaRequest: SearchPropostasRequest): ResponseEntity<Any> {
        val proposta = propostasRepository.findById(propostaRequest.id)
        return if (proposta.isPresent) {
            ResponseEntity(proposta.get(), HttpStatus.OK)
        } else {
            ResponseEntity("Proposta not found", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/add")
    fun addProposta(@RequestBody propostaRequest: SearchPropostasRequest): ResponseEntity<Propostas> {
        val savedProposta = propostasRepository.save(propostaRequest.proposta)
        return ResponseEntity(savedProposta, HttpStatus.CREATED)
    }

    @PutMapping("/update/{id}")
    fun updateProposta(@RequestBody propostaRequest: SearchPropostasRequest): ResponseEntity<Propostas> {
        return if (propostasRepository.existsById(propostaRequest.id)) {
            val updatedProposta = propostasRepository.save(propostaRequest.proposta)
            ResponseEntity(updatedProposta, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun deleteProposta(@RequestBody propostaRequest: SearchPropostasRequest): ResponseEntity<Any> {
        return if (propostasRepository.existsById(propostaRequest.id)) {
            propostasRepository.deleteById(propostaRequest.id)
            ResponseEntity(HttpStatus.OK)
        } else {
            ResponseEntity("Proposta not found", HttpStatus.NOT_FOUND)
        }
    }
}
