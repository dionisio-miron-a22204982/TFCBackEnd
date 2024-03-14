package pt.ulusofona.expenses.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.HashingAlgorithm
import pt.ulusofona.expenses.repository.HashingAlgorithmRepository
import pt.ulusofona.expenses.request.SearchHashingAlgorithmRequest

@RestController
@RequestMapping("/api/hashing-algorithms")
class HashingAlgorithmController(private val hashingAlgorithmRepository: HashingAlgorithmRepository) {

    @GetMapping("/search/{id}")
    fun getHashingAlgorithmById(@RequestBody hashingAlgorithm: SearchHashingAlgorithmRequest): ResponseEntity<Any> {
        val hashing = hashingAlgorithmRepository.findById(hashingAlgorithm.id)
        return if (hashing.isPresent) {
            ResponseEntity(hashing.get(), HttpStatus.OK)
        } else {
            ResponseEntity("Hashing algorithm not found", HttpStatus.NOT_FOUND)
        }
    }
}
