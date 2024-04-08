package pt.ulusofona.expenses.controller
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.repository.UtilizadorEmpresarialRepository



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
    fun getBusinessUserByName(@PathVariable name: String): ResponseEntity<out Any> {
        val user = utilizadorEmpresarialRepository.findUtilizadorEmpresarialsByName(name)

        return if (user != null) {
            ResponseEntity(user, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Search By username
    @GetMapping("/searchUserName/{username}")
    fun getBusinessUserByUserName(@PathVariable username: String): ResponseEntity<out Any> {

        val user = utilizadorEmpresarialRepository.findUtilizadorEmpresarialsByUsername(username)

        return if (user != null) {
            ResponseEntity(user, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }


}
