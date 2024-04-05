package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorParticular
import pt.ulusofona.expenses.repository.UtilizadorParticularRepository
import pt.ulusofona.expenses.request.CreateUtilizadorParticularRequest
import pt.ulusofona.expenses.request.SearchUtilizadorParticularRequest


@RestController
@RequestMapping("/api/users")
class UtilizadorParticularController(val utilizadorParticularRepository: UtilizadorParticularRepository) {

    @GetMapping("/search/{id}")
    fun getUserById(@PathVariable id: SearchUtilizadorParticularRequest): ResponseEntity<out Any> {

        val userId = utilizadorParticularRepository.findByIdOrNull(id.id)
        val name = utilizadorParticularRepository.findUtilizadorParticularByNome(id.nome)

        return if (userId != null) {
            ResponseEntity(userId, HttpStatus.OK)
        } else if (!name.equals(null)) {
            ResponseEntity(name, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/add/{id}")
    fun createUser(@PathVariable id: CreateUtilizadorParticularRequest): ResponseEntity<out Any> {

        val idUtilizador = id.id
        val nomeUtilizador = id.nome
        val emailUtilizador = id.email
        val contactoUtilizador = id.contacto

        if (utilizadorParticularRepository.findUtilizadorParticularByNome(nomeUtilizador)) {

        }

        return if (userId != null) {
            ResponseEntity(userId, HttpStatus.OK)
        } else if (!name.equals(null)) {
            ResponseEntity(name, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/search/{id}")
    fun getUserBsssyId(@PathVariable id: SearchUtilizadorParticularRequest): ResponseEntity<out Any> {

        val userId = utilizadorParticularRepository.findByIdOrNull(id.id)
        val name = utilizadorParticularRepository.findUtilizadorParticularByNome(id.nome)

        return if (userId != null) {
            ResponseEntity(userId, HttpStatus.OK)
        } else if (!name.equals(null)) {
            ResponseEntity(name, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }
}
