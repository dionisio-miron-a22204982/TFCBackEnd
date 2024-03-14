package pt.ulusofona.expenses.request

import pt.ulusofona.expenses.dao.FormacaoAcademica

data class SearchFormacaoAcademica(val id: Long, val formacaoAcademica: FormacaoAcademica) {
}