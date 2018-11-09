package org.nuvo.message.receive

import mu.KotlinLogging
import org.greenrobot.eventbus.EventBus
import org.nuvo.MessageQueue

class Ping(eventBus: EventBus, messageQueue: MessageQueue) : AbstractReceivableMessage("""#PING""", eventBus, messageQueue) {

    private val logger = KotlinLogging.logger {}

    override fun process(message: String) {
        logger.debug("Received Ping")
        messageQueue.put(org.nuvo.message.transmit.Ping)
        logger.debug("Sent Ping")
    }

}