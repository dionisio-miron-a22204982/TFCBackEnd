package pt.ulusofona.expenses.request

// Search
data class SearchUtilizadorParticularByIdRequest(val id: Long)
data class SearchUtilizadorParticularByNameRequest(val name: String)
data class SearchUtilizadorParticularByUserNameRequest(val username: String)

// Create
data class CreateUtilizadorParticularRequest(val id: Long, val username: String, val password: String, val name: String, val email: String, val contacto: String)

// Edit
data class EditUtilizadorParticularNameRequest(val id: Long, val name: String)
data class EditUtilizadorParticularUserNameRequest(val id: Long, val username: String)
data class EditUtilizadorParticularPasswordRequest(val id: Long, val password: String)
data class EditUtilizadorParticularProfissaoRequest(val id: Long, val profissao: String)
data class EditUtilizadorParticularContactoRequest(val id: Long, val contacto: String)
data class EditUtilizadorParticularEmailRequest(val id: Long, val email: String)