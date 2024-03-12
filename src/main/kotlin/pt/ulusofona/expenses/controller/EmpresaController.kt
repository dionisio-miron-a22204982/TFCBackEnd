package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.Empresa
import pt.ulusofona.expenses.repository.EmpresaRepository
import pt.ulusofona.expenses.request.SearchEmpresaRequest


@RestController
@RequestMapping("/api/comentarios")
class EmpresaController(private val empresaRepository: EmpresaRepository) {
    @GetMapping("/search/{input}")
    fun getEmpresaByIdOrName(@RequestBody request: SearchEmpresaRequest): ResponseEntity<out Any> {

        val empresaId = empresaRepository.findByIdOrNull(request.id)
        val nomeEmpresa = empresaRepository.findEmpresaByNome(request.nome)

        return if (empresaId != null) {
            ResponseEntity(empresaId, HttpStatus.OK)
        } else if (!nomeEmpresa.equals(null)) {
            ResponseEntity(nomeEmpresa, HttpStatus.OK)
        } else {
            ResponseEntity("Empresa não encontrada", HttpStatus.NOT_FOUND)
        }
    }
}