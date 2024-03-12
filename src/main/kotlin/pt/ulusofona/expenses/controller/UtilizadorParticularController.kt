package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorParticular
import pt.ulusofona.expenses.repository.UtilizadorParticularRepository
import pt.ulusofona.expenses.request.SearchUtilizadorParticularRequest


@RestController
@RequestMapping("/api/users")
class UtilizadorParticularController(val utilizadorParticularRepository: UtilizadorParticularRepository) {

    @GetMapping("/search/{input}")
    fun getUserById(@RequestBody request: SearchUtilizadorParticularRequest): ResponseEntity<out Any> {

        val userId = utilizadorParticularRepository.findByIdOrNull(request.id)
        val name = utilizadorParticularRepository.findUtilizadorParticularByNome(request.nome)

        return if (userId != null) {
            ResponseEntity(userId, HttpStatus.OK)
        } else if (name != null) {
            ResponseEntity(name, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }
}
