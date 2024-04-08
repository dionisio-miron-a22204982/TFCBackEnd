package pt.ulusofona.expenses.request

import pt.ulusofona.expenses.dao.Empresa

// Search
data class SearchUtilizadorEmpresarialByIdRequest(val id: Long, val nome: String)
data class SearchUtilizadorEmpresarialByNameRequest(val name: String)
data class SearchUtilizadorEmpresarialByUserNameRequest(val username: String)

// Create
data class CreateUtilizadorEmpresarialRequest(
    val id: Long,
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val contacto: String,
    val empresa: String
)

// Edit
data class EditUtilizadorEmpresarialNameRequest(val id: Long, val name: String)
data class EditUtilizadorEmpresarialUserNameRequest(val id: Long, val username: String)
data class EditUtilizadorEmpresarialPasswordRequest(val id: Long, val password: String)
data class EditUtilizadorEmpresarialProfissaoRequest(val id: Long, val profissao: String)
data class EditUtilizadorEmpresarialContactoRequest(val id: Long, val contacto: String)
data class EditUtilizadorEmpresarialEmailRequest(val id: Long, val email: String)
data class EditUtilizadorEmpresarialEmpresaRequest(val id: Long, val empresa: String)