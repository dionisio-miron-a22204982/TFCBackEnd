package pt.ulusofona.expenses.service

import org.springframework.stereotype.Service
import pt.ulusofona.expenses.dao.UtilizadorParticular
import pt.ulusofona.expenses.repository.UtilizadorParticularRepository
import java.util.*

@Service
class UtilizadorParticularService(private val utilizadorParticularRepository: UtilizadorParticularRepository) {

    fun getUserById(id: Long): Optional<UtilizadorParticular> {
        return utilizadorParticularRepository.findById(id)
    }

    fun getAllUsers(): List<UtilizadorParticular> {
        return utilizadorParticularRepository.findAll()
    }

    fun getUserByEmail(email: String): UtilizadorParticular? {
        return utilizadorParticularRepository.findByEmail(email)
    }

    fun createUser(user: UtilizadorParticular): UtilizadorParticular {
        return utilizadorParticularRepository.save(user)
    }

    fun updateUser(id: Long, updatedUser: UtilizadorParticular): UtilizadorParticular {
        val existingUser = utilizadorParticularRepository.findById(id)
                .orElseThrow { NoSuchElementException("User with id $id not found") }

        existingUser.apply {
            email = updatedUser.email
            nome = updatedUser.nome
            profissao = updatedUser.profissao
            contacto = updatedUser.contacto
            dataNascimento = updatedUser.dataNascimento
            password = updatedUser.password
            formacaoAcademica = updatedUser.formacaoAcademica
            competencias = updatedUser.competencias
            experiencias = updatedUser.experiencias
            proposta = updatedUser.proposta
            hashAlgorithm = updatedUser.hashAlgorithm
        }

        return utilizadorParticularRepository.save(existingUser)
    }

    fun deleteUser(id: Long) {
        utilizadorParticularRepository.deleteById(id)
    }
}
