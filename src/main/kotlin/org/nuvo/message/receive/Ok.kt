package org.nuvo.message.receive

import mu.KotlinLogging
import org.greenrobot.eventbus.EventBus
import org.nuvo.MessageQueue

class Ok(eventBus: EventBus, messageQueue: MessageQueue) : AbstractReceivableMessage("""#OK""", eventBus, messageQueue) {

    private val logger = KotlinLogging.logger {}

    override fun process(message: String) {
        logger.debug("Received 'OK' from Nuvo")
    }

}