package org.nuvo.message.receive

import mu.KotlinLogging
import org.greenrobot.eventbus.EventBus
import org.nuvo.MessageQueue

class Transport(eventBus: EventBus, messageQueue: MessageQueue) : AbstractReceivableMessage("""#Z(?<zone>\d+)S(?<source>\d)(?<command>PLAYPAUSE|NEXT|PREV)""", eventBus, messageQueue) {

    private val logger = KotlinLogging.logger {}

    override fun process(message: String) {

        val groups = pattern.matchEntire(message)?.groups
        val zone = groups?.get("zone")?.value?.toInt()
        val source = groups?.get("zone")?.value?.toInt()
        val command = groups?.get("command")?.value

        if (zone != null && source != null && command != null) {
            when (command) {
                "PLAYPAUSE" -> eventBus.post(org.nuvo.event.PlayPause(zone, source))
                "NEXT" -> eventBus.post(org.nuvo.event.Next(zone, source))
                "PREV" -> eventBus.post(org.nuvo.event.Prev(zone, source))
                else -> logger.warn("Unrecognized transport command '$command'")
            }
        } else {
            logger.error("Error processing Transport message '$message'")
        }

    }

}