package pt.ulusofona.expenses.controller
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorEmpresarial
import pt.ulusofona.expenses.repository.UtilizadorEmpresarialRepository
import pt.ulusofona.expenses.request.SearchUtilizadorEmpresarialRequest



@RestController
@RequestMapping("/api/business-users")
class UtilizadorEmpresarialController(private val utilizadorEmpresarialRepository: UtilizadorEmpresarialRepository) {

    @GetMapping("/search/{input}")
    fun getUserById(@RequestBody request: SearchUtilizadorEmpresarialRequest): ResponseEntity<out Any> {

        val userId = utilizadorEmpresarialRepository.findByIdOrNull(request.id)
        val name = utilizadorEmpresarialRepository.findUtilizadorEmpresarialsByNome(request.nome)


        return if (userId != null) {
            ResponseEntity(userId, HttpStatus.OK)
        } else if (!name.equals(null)) {
            ResponseEntity(name, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }
}
