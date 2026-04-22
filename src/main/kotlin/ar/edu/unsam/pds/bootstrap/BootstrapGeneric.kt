package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.ProjectApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

abstract class BootstrapGeneric(private val message: String) : InitializingBean {
    @Autowired
    private lateinit var transactionManager: PlatformTransactionManager
    private val log: Logger = LoggerFactory.getLogger(ProjectApplication::class.java)


    override fun afterPropertiesSet() {
      
        log.info("#".repeat(110))
        log.info(String.format("# %-106s #", "Loading $message ..."))
        log.info("#".repeat(110))

        transactionTemplate().execute { this.doAfterPropertiesSet(); "status" }
      
    }

    abstract fun doAfterPropertiesSet()

    private fun transactionTemplate(): TransactionTemplate {
        return TransactionTemplate(transactionManager)
    }
}