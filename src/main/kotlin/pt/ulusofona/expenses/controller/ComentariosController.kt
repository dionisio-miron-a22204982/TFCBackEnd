package pt.ulusofona.expenses.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.ulusofona.expenses.dao.Comentarios
import pt.ulusofona.expenses.repository.ComentariosRepository
import pt.ulusofona.expenses.repository.UtilizadorEmpresarialRepository
import pt.ulusofona.expenses.repository.UtilizadorParticularRepository
import pt.ulusofona.expenses.request.SearchComentariosRequest

@RestController
@RequestMapping("/api/comentarios")

class ComentariosController(private val comentariosRepository: ComentariosRepository, val utilizadorParticularRepository: UtilizadorParticularRepository, val utilizadorEmpresarialRepository: UtilizadorEmpresarialRepository) {

    @GetMapping("/search/{input}")
    fun getCommentById(@RequestBody comentarioRequest: SearchComentariosRequest): ResponseEntity<out Any> {

        val idComentario = comentariosRepository.findByIdOrNull(comentarioRequest.id)

        return if (idComentario != null) {
            ResponseEntity(idComentario, HttpStatus.OK)
        }  else {
            ResponseEntity("Comentario não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun newComment(@RequestBody comentarioRequest: SearchComentariosRequest): ResponseEntity<String> {

        val utilizadorParticular = utilizadorParticularRepository.findByIdOrNull(comentarioRequest.id)
        val comentarioString = comentarioRequest.comentario

        if(utilizadorParticular==null){
            throw IllegalArgumentException("utilizador não existe")
        }

        val comentario = Comentarios(author = utilizadorParticular, comentario = comentarioString)
        comentariosRepository.save(comentario)

        return ResponseEntity("ok, saved", HttpStatus.OK)
    }

    @PostMapping
    fun editComment(@RequestBody comentarioRequest: SearchComentariosRequest): ResponseEntity<String> {

        val idComentario = comentariosRepository.findByIdOrNull(comentarioRequest.id)
                ?: throw IllegalArgumentException("Comentário não encontrado")

        idComentario.comentario to comentarioRequest.comentario

        return ResponseEntity("ok, saved", HttpStatus.OK)
    }

    @PostMapping
    fun removeComment(@RequestBody comentarioRequest: SearchComentariosRequest): ResponseEntity<String> {

        val idComentario = comentariosRepository.findByIdOrNull(comentarioRequest.id)
                ?: throw IllegalArgumentException("Comentário não encontrado")

        comentariosRepository.delete(idComentario)

        return ResponseEntity("Deleted", HttpStatus.OK)
    }
}