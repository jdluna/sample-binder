package com.adobe.sample.binder

import com.adobe.sample.model.RepositoryMetadata
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties
import org.springframework.cloud.stream.provisioning.ConsumerDestination
import org.springframework.integration.endpoint.MessageProducerSupport
import org.springframework.integration.support.MessageBuilder
import java.util.concurrent.Executors

class SampleMessageProducer(val destination: ConsumerDestination?, val properties: ExtendedConsumerProperties<SampleConsumerProperties>?): MessageProducerSupport() {
    override fun doStart() {
        receive()
    }

    private fun receive() {
        val executorService = Executors.newSingleThreadExecutor()
        executorService.submit {
            makeConnector(destination)
            while (true) {
                val metadata = RepositoryMetadata(path = "/files/foo")
                val msg = MessageBuilder.withPayload(metadata).build()
                sendMessage(msg)
                Thread.sleep(5000)
            }
        }
    }

    private fun makeConnector(destination: ConsumerDestination?): Unit {
        println("clienId = ${properties?.extension?.clientId}")
        println("secret = ${properties?.extension?.secret}")
        println("authCode = ${properties?.extension?.authCode}")
    }
}