package pt.ulusofona.expenses.request

import pt.ulusofona.expenses.dao.Propostas

data class SearchPropostasRequest(val id: Long, val proposta: Propostas) {
}