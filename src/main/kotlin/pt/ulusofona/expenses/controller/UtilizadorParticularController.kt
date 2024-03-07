package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorParticular
import pt.ulusofona.expenses.repository.UtilizadorParticularRepository


@RestController
@RequestMapping("/api/users")
class UtilizadorParticularController(val utilizadorParticularRepository: UtilizadorParticularRepository) {

    @GetMapping("/search/{input}")
    fun getUserById(@PathVariable input: String): ResponseEntity<out Any> {
        if (input.all { it.isDigit() }){
            val utilizadorParticular: UtilizadorParticular =
                utilizadorParticularRepository.findByIdOrNull(input.toLong())
                    ?: throw IllegalArgumentException("Utilizador não existe")
            return ResponseEntity(utilizadorParticular, HttpStatus.OK)
        }
        else {
            val utilizadoresParticulares: List<UtilizadorParticular> = utilizadorParticularRepository.findAll()
                .filter { it.nome.contains(input, ignoreCase = true)}
            return ResponseEntity(utilizadoresParticulares, HttpStatus.OK)
        }
    }
}
