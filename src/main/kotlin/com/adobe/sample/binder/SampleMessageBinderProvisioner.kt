package com.adobe.sample.binder

import org.springframework.cloud.stream.binder.ExtendedConsumerProperties
import org.springframework.cloud.stream.binder.ExtendedProducerProperties
import org.springframework.cloud.stream.provisioning.ConsumerDestination
import org.springframework.cloud.stream.provisioning.ProducerDestination
import org.springframework.cloud.stream.provisioning.ProvisioningProvider

class SampleMessageBinderProvisioner
    : ProvisioningProvider<ExtendedConsumerProperties<SampleConsumerProperties>, ExtendedProducerProperties<SampleProducerProperties>> {

    override fun provisionConsumerDestination(
        name: String?,
        group: String?,
        properties: ExtendedConsumerProperties<SampleConsumerProperties>?
    ): ConsumerDestination {
        return PipelineMessaageDestination(name)
    }

    override fun provisionProducerDestination(
        name: String?,
        properties: ExtendedProducerProperties<SampleProducerProperties>?
    ): ProducerDestination {
        return PipelineMessaageDestination(name)
    }

    private class PipelineMessaageDestination(val destination: String?) : ConsumerDestination, ProducerDestination {
        override fun getName(): String? {
            return destination?.trim();
        }

        override fun getNameForPartition(partition: Int): String {
            throw UnsupportedOperationException("Partitioning is not implemented for file messaging.");
        }
    }
}