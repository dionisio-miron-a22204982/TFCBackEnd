package pt.ulusofona.expenses.request

import pt.ulusofona.expenses.dao.ExperienciaLaboral

data class SearchExperienciaLaboralRequest(val id: Long, val infomacao: String, val experiencia: ExperienciaLaboral)