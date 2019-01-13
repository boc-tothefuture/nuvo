package org.nuvo.message.receive

import mu.KotlinLogging
import org.nuvo.MessageQueue
import org.nuvo.bus.EventBus

class Ping(eventBus: EventBus, messageQueue: MessageQueue) : AbstractReceivableMessage("""#PING""", eventBus, messageQueue) {

    private val logger = KotlinLogging.logger {}

    override fun process(message: String) {
        logger.debug("Received Ping")
        messageQueue.put(org.nuvo.message.transmit.Ping)
        logger.debug("Sent Ping")
    }

}