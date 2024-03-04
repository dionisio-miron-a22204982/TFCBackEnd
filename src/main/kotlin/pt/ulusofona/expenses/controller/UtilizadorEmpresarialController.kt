package pt.ulusofona.expenses.controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorEmpresarial
import pt.ulusofona.expenses.repository.UtilizadorEmpresarialRepository
import java.util.*

@RestController
@RequestMapping("/api/business-users")
class UtilizadorEmpresarialController {

    @Autowired
    lateinit var utilizadorEmpresarialRepository: UtilizadorEmpresarialRepository

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UtilizadorEmpresarial {
        return utilizadorEmpresarialRepository.findById(id)
                .orElseThrow { NoSuchElementException("User with id $id not found") }
    }

    @PostMapping("/create")
    fun createUser(@RequestBody user: UtilizadorEmpresarial): UtilizadorEmpresarial {
        return utilizadorEmpresarialRepository.save(user)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: UtilizadorEmpresarial): UtilizadorEmpresarial {
        val existingUser = utilizadorEmpresarialRepository.findById(id)
                .orElseThrow { NoSuchElementException("User with id $id not found") }

        existingUser.email = updatedUser.email
        existingUser.nome = updatedUser.nome
        existingUser.contactoTelefonico = updatedUser.contactoTelefonico
        existingUser.utilizador = updatedUser.utilizador
        existingUser.password = updatedUser.password
        existingUser.dataDeNascimento = updatedUser.dataDeNascimento
        existingUser.empresa = updatedUser.empresa
        existingUser.hashAlgorithm = updatedUser.hashAlgorithm
        existingUser.permissoes = updatedUser.permissoes

        return utilizadorEmpresarialRepository.save(existingUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        utilizadorEmpresarialRepository.deleteById(id)
    }
}
