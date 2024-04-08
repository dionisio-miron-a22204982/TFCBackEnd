package pt.ulusofona.expenses.repository;
import org.springframework.data.jpa.repository.JpaRepository
import pt.ulusofona.expenses.dao.UtilizadorParticular

interface UtilizadorParticularRepository : JpaRepository<UtilizadorParticular, Long> {
    fun findUtilizadorParticularById(id: Long):UtilizadorParticular?
    fun findUtilizadorParticularByName(name: String):UtilizadorParticular?
    fun findUtilizadorParticularByUsername(username: String):UtilizadorParticular?
    abstract fun existsById(id: Long, function: () -> ResponseEntity<String>): Boolean
}

