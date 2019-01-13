package org.nuvo.message.receive

import org.nuvo.bus.EventBus


abstract class AbstractReceivableMessage(patternString: String,
                                         protected val eventBus: EventBus,
                                         protected val messageQueue: org.nuvo.MessageQueue) {
    val pattern: Regex = patternString.toRegex()

    abstract fun process(message: String)

}