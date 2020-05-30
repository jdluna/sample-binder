package com.adobe.sample.binder

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.stream.binder.AbstractExtendedBindingProperties
import org.springframework.cloud.stream.binder.BinderSpecificPropertiesProvider

@ConfigurationProperties(prefix = "spring.cloud.stream.pipeline")
class SampleExtendedBindingProperties :
    AbstractExtendedBindingProperties<SampleConsumerProperties, SampleProducerProperties, SampleBindingProperties>() {

    override fun getDefaultsPrefix(): String = "spring.cloud.stream.pipeline.default"

    override fun getExtendedPropertiesEntryClass(): Class<out BinderSpecificPropertiesProvider> = SampleBindingProperties::class.java
}