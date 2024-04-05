package pt.ulusofona.expenses.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.HashingAlgorithm
import pt.ulusofona.expenses.repository.HashingAlgorithmRepository
import pt.ulusofona.expenses.request.SearchHashingAlgorithmRequest

@RestController
@RequestMapping("/api/hashingalgorithms")
class HashingAlgorithmController(private val hashingAlgorithmRepository: HashingAlgorithmRepository) {

    @GetMapping("/search/{id}")
    fun getHashingAlgorithmById(@PathVariable id: SearchHashingAlgorithmRequest): ResponseEntity<Any> {
        val hashing = hashingAlgorithmRepository.findById(id.id)
        return if (hashing.isPresent) {
            ResponseEntity(hashing.get(), HttpStatus.OK)
        } else {
            ResponseEntity("Hashing algorithm not found", HttpStatus.NOT_FOUND)
        }
    }
}
