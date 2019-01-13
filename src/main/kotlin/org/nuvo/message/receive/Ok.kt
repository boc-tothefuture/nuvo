package org.nuvo.message.receive

import mu.KotlinLogging
import org.nuvo.MessageQueue
import org.nuvo.bus.EventBus

class Ok(eventBus: EventBus, messageQueue: MessageQueue) : AbstractReceivableMessage("""#OK""", eventBus, messageQueue) {

    private val logger = KotlinLogging.logger {}

    override fun process(message: String) {
        logger.debug("Received 'OK' from Nuvo")
    }

}