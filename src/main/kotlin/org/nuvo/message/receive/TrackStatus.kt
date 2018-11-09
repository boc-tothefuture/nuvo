package org.nuvo.message.receive

import mu.KotlinLogging
import org.greenrobot.eventbus.EventBus
import org.nuvo.MessageQueue
import org.nuvo.event.Status
import org.nuvo.event.TrackStatus

class TrackStatus(eventBus: EventBus, messageQueue: MessageQueue) : AbstractReceivableMessage("""#S\dDISPINFO,.*""", eventBus, messageQueue) {

    private val logger = KotlinLogging.logger {}

    override fun process(message: String) {
        val (sourceText, durationText, positionText, statusText) = message.split(",")
        val source = sourceText[2].toString().toInt()
        val duration = durationText.removePrefix("DUR").toInt()
        val position = positionText.removePrefix("POS").toInt()
        val statusInt = statusText.removePrefix("STATUS").toInt()

        logger.debug("Source '$source' Duration '$duration' Position '$position' Status '$statusInt'")

        eventBus.post(TrackStatus(source, duration, position, Status.fromCode(statusInt)))
    }

}