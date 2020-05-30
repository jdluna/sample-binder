package com.adobe.sample.config

import com.adobe.sample.binder.SampleExtendedBindingProperties
import com.adobe.sample.binder.SampleMessageBinder
import com.adobe.sample.binder.SampleMessageBinderProvisioner
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(SampleExtendedBindingProperties::class)
class SampleMessageBinderConfiguration {
    @Bean
    @ConditionalOnMissingBean
    fun pipelineMessageBinderProvisioner(): SampleMessageBinderProvisioner {
        return SampleMessageBinderProvisioner()
    }

    @Bean
    @ConditionalOnMissingBean
    fun pipelineMessageBinder(sampleMessageBinderProvisioner: SampleMessageBinderProvisioner): SampleMessageBinder {
        return SampleMessageBinder(arrayOf(), sampleMessageBinderProvisioner)
    }
}