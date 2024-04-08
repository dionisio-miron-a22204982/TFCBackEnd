package pt.ulusofona.expenses.controller
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorEmpresarial
import pt.ulusofona.expenses.dao.UtilizadorParticular
import pt.ulusofona.expenses.repository.UtilizadorEmpresarialRepository
import pt.ulusofona.expenses.request.*


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

    // Adding User
    @PostMapping("/add")
    fun createUser(@RequestBody user: CreateUtilizadorEmpresarialRequest): ResponseEntity<out Any> {
        // Extracting user data from the request
        val idEmp = user.id
        val nameEmp = user.name
        val passwordEmp = user.password
        val usernameEmp = user.username
        val emailEmp = user.email
        val contactEmp = user.contacto
        val empresaEmp = user.empresa

        // Creating UtilizadorParticular object
        val utilizadorDB = UtilizadorEmpresarial(idEmp, emailEmp, nameEmp, usernameEmp, passwordEmp, contactEmp, empresaEmp)

        // Saving the UtilizadorParticular object
        val savedUtilizador = utilizadorEmpresarialRepository.save(utilizadorDB)

        return ResponseEntity(savedUtilizador, HttpStatus.CREATED)
    }

    // Delete User
    @DeleteMapping("/delete/{id}")
    fun deleteBusinessUserById(@PathVariable id: Long): ResponseEntity<Any> {
        return if (utilizadorEmpresarialRepository.existsById(id)) {
            utilizadorEmpresarialRepository.deleteById(id)
            ResponseEntity("Utilizador apagado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Delete User by username
    @DeleteMapping("/delete/{username}")
    fun deleteBusinessUserByUsername(@PathVariable username: String): ResponseEntity<Any> {

        val user = utilizadorEmpresarialRepository.findUtilizadorEmpresarialsByUsername(username)

        return if (user != null) {
            utilizadorEmpresarialRepository.delete(user)
            ResponseEntity("Utilizador apagado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User userName
    @PostMapping("/editUserName")
    fun editBusinessUserUsernameNameById(@RequestBody updateUser: EditUtilizadorEmpresarialUserNameRequest): ResponseEntity<Any> {

        val existingUser = utilizadorEmpresarialRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.username = updateUser.username

            // Save the updated user
            utilizadorEmpresarialRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Name
    @PostMapping("/editName")
    fun editBusinessUserNameById(@RequestBody updateUser: EditUtilizadorEmpresarialNameRequest): ResponseEntity<Any> {

        val existingUser = utilizadorEmpresarialRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.name = updateUser.name

            // Save the updated user
            utilizadorEmpresarialRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Password
    @PostMapping("/editPassword")
    fun editBusinessUserPasswordById(@RequestBody updateUser: EditUtilizadorEmpresarialPasswordRequest): ResponseEntity<Any> {

        val existingUser = utilizadorEmpresarialRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.password = updateUser.password

            // Save the updated user
            utilizadorEmpresarialRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Job
    @PostMapping("/editJob")
    fun editBusinessUserJobById(@RequestBody updateUser: EditUtilizadorEmpresarialProfissaoRequest): ResponseEntity<Any> {

        val existingUser = utilizadorEmpresarialRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.profissao = updateUser.profissao

            // Save the updated user
            utilizadorEmpresarialRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Phone Number
    @PostMapping("/editContact")
    fun editBusinessUserContactById(@RequestBody updateUser: EditUtilizadorEmpresarialContactoRequest): ResponseEntity<Any> {

        val existingUser = utilizadorEmpresarialRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.contacto = updateUser.contacto

            // Save the updated user
            utilizadorEmpresarialRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User email
    @PostMapping("/editEmail")
    fun editBusinessUserContactById(@RequestBody updateUser: EditUtilizadorEmpresarialEmailRequest): ResponseEntity<Any> {

        val existingUser = utilizadorEmpresarialRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.email = updateUser.email

            // Save the updated user
            utilizadorEmpresarialRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Empresa
    @PostMapping("/editEmpresa")
    fun editBusinessUserEmpresaById(@RequestBody updateUser: EditUtilizadorEmpresarialEmpresaRequest): ResponseEntity<Any> {

        val existingUser = utilizadorEmpresarialRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.empresa = updateUser.empresa

            // Save the updated user
            utilizadorEmpresarialRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }
}
