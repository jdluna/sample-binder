package com.adobe.sample.binder

import org.springframework.cloud.stream.binder.*
import org.springframework.cloud.stream.provisioning.ConsumerDestination
import org.springframework.cloud.stream.provisioning.ProducerDestination
import org.springframework.integration.core.MessageProducer
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler

class SampleMessageBinder(headersToEmbed: Array<String>, provisioningProvider: SampleMessageBinderProvisioner) :
    AbstractMessageChannelBinder<ExtendedConsumerProperties<SampleConsumerProperties>,
            ExtendedProducerProperties<SampleProducerProperties>, SampleMessageBinderProvisioner>(headersToEmbed, provisioningProvider),
    ExtendedPropertiesBinder<MessageChannel, SampleConsumerProperties, SampleProducerProperties> {

    val pipelineExtendedBindingProperties: SampleExtendedBindingProperties = SampleExtendedBindingProperties()

    override fun createConsumerEndpoint(
        destination: ConsumerDestination?,
        group: String?,
        properties: ExtendedConsumerProperties<SampleConsumerProperties>?
    ): MessageProducer {
        return SampleMessageProducer(destination, properties)
    }

    override fun createProducerMessageHandler(
            destination: ProducerDestination?,
            producerProperties: ExtendedProducerProperties<SampleProducerProperties>?,
            errorChannel: MessageChannel?
    ): MessageHandler {
        return MessageHandler {  }
    }

    override fun getExtendedConsumerProperties(channelName: String?): SampleConsumerProperties {
        return pipelineExtendedBindingProperties.getExtendedConsumerProperties(channelName)
    }

    override fun getExtendedProducerProperties(channelName: String?): SampleProducerProperties {
        return pipelineExtendedBindingProperties.getExtendedProducerProperties(channelName)
    }

    override fun getDefaultsPrefix(): String {
        return pipelineExtendedBindingProperties.defaultsPrefix
    }

    override fun getExtendedPropertiesEntryClass(): Class<out BinderSpecificPropertiesProvider> {
        return pipelineExtendedBindingProperties.extendedPropertiesEntryClass
    }
}