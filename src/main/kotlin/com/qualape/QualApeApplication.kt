package com.qualape

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
//@EnableJpaRepositories("com.qualape.api.repositories")
class QualApeApplication

fun main(args: Array<String>) {
	runApplication<QualApeApplication>(*args)
}
