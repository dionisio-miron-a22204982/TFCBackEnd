package pt.ulusofona.expenses.controller
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorEmpresarial
import pt.ulusofona.expenses.repository.UtilizadorEmpresarialRepository


@RestController
@RequestMapping("/api/business-users")
class UtilizadorEmpresarialController(private val utilizadorEmpresarialRepository: UtilizadorEmpresarialRepository) {

    @GetMapping("/search/{input}")
    fun getUserById(@PathVariable input: String): ResponseEntity<out Any> {
        if (input.all { it.isDigit() }){
            val utilizadorEmpresarial: UtilizadorEmpresarial =
                    utilizadorEmpresarialRepository.findByIdOrNull(input.toLong())
                            ?: throw IllegalArgumentException("Utilizador não existe")
            return ResponseEntity(utilizadorEmpresarial, HttpStatus.OK)
        }
        else {
            val utilizadoresEmpresariais: List<UtilizadorEmpresarial> = utilizadorEmpresarialRepository.findAll()
                    .filter { it.nome.contains(input, ignoreCase = true)}
            return ResponseEntity(utilizadoresEmpresariais, HttpStatus.OK)
        }
    }
}
