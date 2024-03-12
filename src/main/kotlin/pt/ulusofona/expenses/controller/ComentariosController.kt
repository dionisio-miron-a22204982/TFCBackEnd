package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.Comentarios
import pt.ulusofona.expenses.repository.ComentariosRepository
import pt.ulusofona.expenses.repository.UtilizadorEmpresarialRepository
import pt.ulusofona.expenses.repository.UtilizadorParticularRepository
import pt.ulusofona.expenses.request.ComentarioRequest

@RestController
@RequestMapping("/api/comentarios")

//adicionei os repositorios de utilizadores particulares e empresariais
class ComentariosController(private val comentariosRepository: ComentariosRepository, val utilizadorParticularRepository: UtilizadorParticularRepository, val utilizadorEmpresarialRepository: UtilizadorEmpresarialRepository) {

    @GetMapping("/search/{input}")
    fun getCommentById(@PathVariable input: String): ResponseEntity<Comentarios> {
        if (input.all { it.isDigit() }){
            val comentarios: Comentarios =
                    comentariosRepository.findByIdOrNull(input.toLong())
                            ?: throw IllegalArgumentException("Comentario não existe.")
            return ResponseEntity(comentarios, HttpStatus.OK)
        } else {
            throw IllegalArgumentException("O Id deve ser do tipo numerico.")
        }
    }



    //exemplo: função para adicionar comentario
    @PostMapping
    fun new(@RequestBody comentarioRequest: ComentarioRequest): ResponseEntity<String> {

        val utilizadorParticular = utilizadorParticularRepository.findByIdOrNull(comentarioRequest.idUser)    //procura utilizadorParticular
        val comentarioString = comentarioRequest.comentario

        if(utilizadorParticular==null){
            throw IllegalArgumentException("utilizador não existe")
        }

        val comentario = Comentarios(author = utilizadorParticular, comentario = comentarioString)
        comentariosRepository.save(comentario)

        return ResponseEntity("ok", HttpStatus.OK)
    }

    @PostMapping
    fun edit(@PathVariable input: String): ResponseEntity<String> {    //todo alterar @PathVariable para @RequestBody searchComentariosRequest
        return ResponseEntity("ok", HttpStatus.OK)
    }

    @PostMapping
    fun remove(@PathVariable input: String): ResponseEntity<String> {
        return ResponseEntity("ok", HttpStatus.OK)
    }
}