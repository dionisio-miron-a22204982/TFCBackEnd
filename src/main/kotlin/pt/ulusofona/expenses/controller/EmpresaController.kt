package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.Empresa
import pt.ulusofona.expenses.repository.EmpresaRepository

@RestController
@RequestMapping("/api/comentarios")
class EmpresaController(private val empresaRepository: EmpresaRepository) {
    @GetMapping("/search/{input}")
    fun getUserById(@PathVariable input: String): ResponseEntity<out Any> {
        if (input.all { it.isDigit() }){
            val empresa: Empresa =
                    empresaRepository.findByIdOrNull(input.toLong())
                            ?: throw IllegalArgumentException("Empresa não encontrada.")
            return ResponseEntity(empresa, HttpStatus.OK)
        } else {
            val nomeEmpresa: List<Empresa> = empresaRepository.findAll()
                    .filter { it.nome.contains(input, ignoreCase = true)}
            return ResponseEntity(nomeEmpresa, HttpStatus.OK)
        }
    }
}