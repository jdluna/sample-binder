package com.adobe.sample.binder

import org.springframework.boot.context.properties.source.ConfigurationPropertyName
import org.springframework.cloud.stream.config.BindingHandlerAdvise.MappingsProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


@Configuration
class ExtendedBindingHandlerMappingsProviderConfiguration {
    @Bean
    open fun pipelineExtendedPropertiesDefaultMappingsProvider(): MappingsProvider? {
        return MappingsProvider {
            val mappings: MutableMap<ConfigurationPropertyName, ConfigurationPropertyName> =
                HashMap()
            mappings[ConfigurationPropertyName.of("spring.cloud.stream.pipeline.bindings")] =
                ConfigurationPropertyName.of("spring.cloud.stream.pipeline.default")
            mappings
        }
    }
}