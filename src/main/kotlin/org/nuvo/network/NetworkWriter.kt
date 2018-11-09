package org.nuvo.network

import kotlinx.coroutines.delay
import kotlinx.coroutines.io.ByteWriteChannel
import kotlinx.coroutines.io.writeStringUtf8
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.nuvo.MessageQueue
import kotlin.concurrent.thread

class NetworkWriter(private val output: ByteWriteChannel, private val messageQueue: MessageQueue) {

    private val logger = KotlinLogging.logger {}

    init {
        start()
    }

    private fun start() {
        thread(start = true, name = "Transmit") {
            runBlocking {
                delay(1000)
                while (true) {
                    val transmittableMessage = messageQueue.take()
                    logger.debug("Sending Message: '${transmittableMessage.message}'")
                    output.writeStringUtf8("${transmittableMessage.message}\r\n")
                    delay(2)
                }
            }
        }
    }

}