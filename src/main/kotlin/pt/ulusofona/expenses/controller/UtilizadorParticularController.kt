package pt.ulusofona.expenses.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.UtilizadorParticular
import pt.ulusofona.expenses.service.UtilizadorParticularService
import java.util.*

@RestController
@RequestMapping("/api/users")
class UtilizadorParticularController(private val userService: UtilizadorParticularService) {

    @GetMapping("/search/id/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<Optional<UtilizadorParticular>> {
        val user = userService.getUserById(id)
        return ResponseEntity(user, HttpStatus.OK)
    }

    @GetMapping("/list")
    fun listUsers(): List<UtilizadorParticular> = userService.getAllUsers()

    @GetMapping("/search/email/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<UtilizadorParticular> {
        val user = userService.getUserByEmail(email)
        return ResponseEntity(user, HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createUser(@RequestBody user: UtilizadorParticular): UtilizadorParticular {
        return userService.createUser(user)
    }

    @PutMapping("/update/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: UtilizadorParticular): ResponseEntity<UtilizadorParticular> {
        val savedUser = userService.updateUser(id, updatedUser)
        return ResponseEntity(savedUser, HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
