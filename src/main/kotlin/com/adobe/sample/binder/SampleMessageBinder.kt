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

    var sampleExtendedBindingProperties: SampleExtendedBindingProperties? = null

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
        return sampleExtendedBindingProperties!!.getExtendedConsumerProperties(channelName)
    }

    override fun getExtendedProducerProperties(channelName: String?): SampleProducerProperties {
        return sampleExtendedBindingProperties!!.getExtendedProducerProperties(channelName)
    }

    override fun getDefaultsPrefix(): String {
        return sampleExtendedBindingProperties!!.defaultsPrefix
    }

    override fun getExtendedPropertiesEntryClass(): Class<out BinderSpecificPropertiesProvider> {
        return sampleExtendedBindingProperties!!.extendedPropertiesEntryClass
    }
}