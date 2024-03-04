package pt.ulusofona.expenses

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Component
import pt.ulusofona.expenses.dao.ExpenseType
import pt.ulusofona.expenses.dao.Role
import pt.ulusofona.expenses.dao.User
import pt.ulusofona.expenses.dao.UtilizadorParticular
import pt.ulusofona.expenses.repository.ExpenseTypeRepository
import pt.ulusofona.expenses.repository.UserRepository
import pt.ulusofona.expenses.repository.UtilizadorParticularRepository
import java.util.*


@Component
@Profile("!test")
class DataLoader(
    val expenseTypeRepository: ExpenseTypeRepository,
    val userRepository: UserRepository,
    val userDetailsManager: UserDetailsManager,
    val utilizadorParticularRepository: UtilizadorParticularRepository


) : ApplicationRunner {

    var logger: Logger = LoggerFactory.getLogger(DataLoader::class.java)

    override fun run(args: ApplicationArguments?) {

        logger.info("Environment variables:")
        for ((key, value) in System.getenv()) {
            logger.info("\t$key : $value")
        }

        val countUsers = userRepository.count()

        if (countUsers == 0L) {
            logger.info("No users yet, let's create an admin user")
            // let's create a super user
            userDetailsManager.createUser(
                User(firstName = "admin", email = "admin", pass = "123",
                    rolesAsList = listOf(Role.ROLE_USER, Role.ROLE_ADMIN),
                    emailConfirmed = true)
            )
        } else {
            logger.info("Users already created, nothing to do here...")
        }


            logger.info("No users yet, let's create an admin user")

            // Let's create a super user
            val adminUser = UtilizadorParticular(
                    id = 1,
                    email = "admin@example.com",
                    nome = "Admin",
                    profissao = "Administrator",
                    contacto = 123456789,
                    password = "admin123",
                    dataNascimento = Date()
            )

            utilizadorParticularRepository.save(adminUser)
    }
}