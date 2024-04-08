package pt.ulusofona.expenses.controller
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.repository.UtilizadorEmpresarialRepository
import pt.ulusofona.expenses.request.SearchUtilizadorEmpresarialByIdRequest
import pt.ulusofona.expenses.request.SearchUtilizadorParticularByNameRequest
import pt.ulusofona.expenses.request.SearchUtilizadorParticularByUserNameRequest


@RestController
@RequestMapping("/api/businessUsers")
class UtilizadorEmpresarialController(private val utilizadorEmpresarialRepository: UtilizadorEmpresarialRepository) {

    // Search by Id
    @GetMapping("/searchId/{id}")
    fun getBusinessUserById(@PathVariable id: Long): ResponseEntity<out Any> {

        // Getting the user
        val userId = utilizadorEmpresarialRepository.findUtilizadorEmpresarialsById(id)

        // Conditions
        return if (userId != null) {
            ResponseEntity(userId, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Search By name
    @GetMapping("/searchName/{name}")
    fun getBusinessUserByName(@PathVariable name: SearchUtilizadorParticularByNameRequest): ResponseEntity<out Any> {
        val user = utilizadorEmpresarialRepository.findUtilizadorEmpresarialsByName(name.name)

        return if (user != null) {
            ResponseEntity(user, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Search By username
    @GetMapping("/searchUserName/{username}")
    fun getBusinessUserByUserName(@PathVariable username: SearchUtilizadorParticularByUserNameRequest): ResponseEntity<out Any> {

        val user = utilizadorParticularRepository.findUtilizadorParticularByUsername(username.username)

        return if (user != null) {
            ResponseEntity(user, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }
}
