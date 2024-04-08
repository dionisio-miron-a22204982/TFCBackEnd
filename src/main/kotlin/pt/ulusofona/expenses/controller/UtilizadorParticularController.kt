package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorParticular
import pt.ulusofona.expenses.repository.UtilizadorParticularRepository
import pt.ulusofona.expenses.request.*


@RestController
@RequestMapping("/api/userParticular")
class UtilizadorParticularController(val utilizadorParticularRepository: UtilizadorParticularRepository) {

    // Search by Id
    @GetMapping("/searchId/{id}")
    fun getUserById(@PathVariable id: SearchUtilizadorParticularByIdRequest): ResponseEntity<out Any> {

        // Getting the user
        val userId = utilizadorParticularRepository.findUtilizadorParticularById(id.id)

        // Conditions
        return if (userId != null) {
            ResponseEntity(userId, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Search By name
    @GetMapping("/searchName/{name}")
    fun getUserByName(@PathVariable name: SearchUtilizadorParticularByNameRequest): ResponseEntity<out Any> {
        val user = utilizadorParticularRepository.findUtilizadorParticularByName(name.name)

        return if (user != null) {
            ResponseEntity(user, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Search By username
    @GetMapping("/searchUserName/{username}")
    fun getUserByName(@PathVariable username: SearchUtilizadorParticularByUserNameRequest): ResponseEntity<out Any> {

        val user = utilizadorParticularRepository.findUtilizadorParticularByUsername(username.username)

        return if (user != null) {
            ResponseEntity(user, HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Adding User
    @PostMapping("/add")
    fun createUser(@RequestBody user: CreateUtilizadorParticularRequest): ResponseEntity<out Any> {
        // Extracting user data from the request
        val id = user.id
        val name = user.name
        val password = user.password
        val username = user.username
        val email = user.email
        val contact = user.contacto

        // Creating UtilizadorParticular object
        val utilizadorDB = UtilizadorParticular(id, email, name, username, password, contact)

        // Saving the UtilizadorParticular object
        val savedUtilizador = utilizadorParticularRepository.save(utilizadorDB)

        return ResponseEntity(savedUtilizador, HttpStatus.CREATED)
    }

    // Delete User by ID
    @DeleteMapping("/delete/{id}")
    fun deleteUserById(@PathVariable id: SearchUtilizadorParticularByIdRequest): ResponseEntity<Any> {

        return if (utilizadorParticularRepository.existsById(id.id)) {
            utilizadorParticularRepository.deleteById(id.id)
            ResponseEntity("Utilizador apagado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Delete User by username
    @DeleteMapping("/delete/{username}")
    fun deleteUserById(@PathVariable username: SearchUtilizadorParticularByUserNameRequest): ResponseEntity<Any> {

        val user = utilizadorParticularRepository.findUtilizadorParticularByUsername(username.username)

        return if (user != null) {
            utilizadorParticularRepository.delete(user)
            ResponseEntity("Utilizador apagado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User userName
    @PostMapping("/editUserName/{id}")
    fun editUserUsernameNameById(@RequestBody updateUser: EditUtilizadorParticularUserNameRequest): ResponseEntity<Any> {

        val existingUser = utilizadorParticularRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.username = updateUser.username

            // Save the updated user
            utilizadorParticularRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Name
    @PostMapping("/editName/{id}")
    fun editUserNameById(@RequestBody updateUser: EditUtilizadorParticularNameRequest): ResponseEntity<Any> {

        val existingUser = utilizadorParticularRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.name = updateUser.name

            // Save the updated user
            utilizadorParticularRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Password
    @PostMapping("/editPassword/{id}")
    fun editUserPasswordById(@RequestBody updateUser: EditUtilizadorParticularPasswordRequest): ResponseEntity<Any> {

        val existingUser = utilizadorParticularRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.password = updateUser.password

            // Save the updated user
            utilizadorParticularRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Job
    @PostMapping("/editJob/{id}")
    fun editUserJobById(@RequestBody updateUser: EditUtilizadorParticularProfissaoRequest): ResponseEntity<Any> {

        val existingUser = utilizadorParticularRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.profissao = updateUser.profissao

            // Save the updated user
            utilizadorParticularRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User Phone Number
    @PostMapping("/editContact/{id}")
    fun editUserContactById(@RequestBody updateUser: EditUtilizadorParticularContactoRequest): ResponseEntity<Any> {

        val existingUser = utilizadorParticularRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.contacto = updateUser.contacto

            // Save the updated user
            utilizadorParticularRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    // Edit User email
    @PostMapping("/editContact/{id}")
    fun editUserContactById(@RequestBody updateUser: EditUtilizadorParticularEmailRequest): ResponseEntity<Any> {

        val existingUser = utilizadorParticularRepository.findById(updateUser.id)

        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.email = updateUser.email

            // Save the updated user
            utilizadorParticularRepository.save(userToUpdate)
            ResponseEntity("Utilizador atualizado com sucesso", HttpStatus.OK)
        } else {
            ResponseEntity("Utilizador não encontrado", HttpStatus.NOT_FOUND)
        }
    }
}
