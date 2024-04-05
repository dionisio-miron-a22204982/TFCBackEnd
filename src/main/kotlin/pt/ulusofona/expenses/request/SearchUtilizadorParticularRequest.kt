package pt.ulusofona.expenses.request

data class SearchUtilizadorParticularRequest(val id: Long, val nome: String)

data class CreateUtilizadorParticularRequest(val id: Long, val nome: String, val email: String, val contacto: String)