package com.adobe.sample

import com.adobe.sample.model.RepositoryMetadata
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.function.Consumer

@SpringBootApplication
open class SampleBinderApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<SampleBinderApplication>(*args)
		}
	}

	@Bean
	open fun log(): Consumer<RepositoryMetadata> {
		return Consumer<RepositoryMetadata> { metadata: RepositoryMetadata -> println("Received: ${metadata.path}") }
	}
}