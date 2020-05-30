package com.adobe.sample.binder

import org.springframework.cloud.stream.binder.BinderSpecificPropertiesProvider

class SampleBindingProperties : BinderSpecificPropertiesProvider {
    private val consumer = SampleConsumerProperties()
    private val producer = SampleProducerProperties()

    override fun getConsumer(): Any {
        return consumer
    }

    override fun getProducer(): Any {
        return producer
    }
}