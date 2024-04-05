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

    @GetMapping("/search/{id}")
    fun getCommentById(@PathVariable id : SearchComentariosRequest): ResponseEntity<out Any> {

        val idComentario = comentariosRepository.findByIdOrNull(id.id)

        return if (idComentario != null) {
            ResponseEntity(idComentario, HttpStatus.OK)
        }  else {
            ResponseEntity("Comentario não encontrado", HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/new/{id}")
    fun newComment(@PathVariable id : SearchComentariosRequest): ResponseEntity<String> {

        val utilizadorParticular = utilizadorParticularRepository.findByIdOrNull(id.id)
        val comentarioString = id.comentario

        if(utilizadorParticular==null){
            throw IllegalArgumentException("Utilizador não existe")
        }

        val comentario = Comentarios(author = utilizadorParticular, comentario = comentarioString)
        comentariosRepository.save(comentario)

        return ResponseEntity("ok, saved", HttpStatus.OK)
    }

    @PostMapping("/edit/{id}")
    fun editComment(@PathVariable id : SearchComentariosRequest): ResponseEntity<String> {

        val idComentario = comentariosRepository.findByIdOrNull(id.id)
                ?: throw IllegalArgumentException("Comentário não encontrado")

        idComentario.comentario to id.comentario

        return ResponseEntity("ok, saved", HttpStatus.OK)
    }

    @PostMapping("/remove/{id}")
    fun removeComment(@PathVariable id : SearchComentariosRequest): ResponseEntity<String> {

        val idComentario = comentariosRepository.findByIdOrNull(id.id)
                ?: throw IllegalArgumentException("Comentário não encontrado")

        comentariosRepository.delete(idComentario)

        return ResponseEntity("Deleted", HttpStatus.OK)
    }
}