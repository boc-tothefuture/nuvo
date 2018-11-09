package org.nuvo.message.receive

import mu.KotlinLogging
import org.greenrobot.eventbus.EventBus
import org.nuvo.MessageQueue
import org.nuvo.event.SourceDisplay

class SourceDisplay(eventBus: EventBus, messageQueue: MessageQueue) : AbstractReceivableMessage("""#S\dDISPLINE\d,.*""", eventBus, messageQueue) {

    private val logger = KotlinLogging.logger {}

    override fun process(message: String) {
        var (source, line) = message.split(",")
        line = line.trim('"')
        val sourceNumber = source[2].toString().toInt()
        val lineNumber = source.takeLast(1).toInt()
        eventBus.post(SourceDisplay(sourceNumber, lineNumber, line))
    }

}