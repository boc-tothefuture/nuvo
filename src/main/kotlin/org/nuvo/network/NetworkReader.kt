package org.nuvo.network

import kotlinx.coroutines.io.ByteReadChannel
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.greenrobot.eventbus.EventBus
import org.nuvo.message.receive.*
import org.nuvo.message.transmit.TransmittableMessage
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

class NetworkReader(private val input: ByteReadChannel, eventBus: EventBus, messageQueue: LinkedBlockingQueue<TransmittableMessage>) {

    private val logger = KotlinLogging.logger {}
    private val ping = Ping(eventBus, messageQueue)
    private val zone = Zone(eventBus, messageQueue)
    private val sourceDisplay = SourceDisplay(eventBus, messageQueue)
    private val trackStatus = TrackStatus(eventBus, messageQueue)
    private val playPause = Transport(eventBus, messageQueue)
    private val ok = org.nuvo.message.receive.Ok(eventBus, messageQueue)

    init {
        start()
    }

    private fun start() {
        thread(start = true, name = "Read Messages") {
            while (true) {
                runBlocking {
                    val message = input.readUTF8Line(10000)?.trim()
                    if (message != null) {
                        logger.debug("Server said: '$message'")
                        when {
                            ping.pattern.matches(message) -> ping.process(message)
                            zone.pattern.matches(message) -> zone.process(message)
                            sourceDisplay.pattern.matches(message) -> sourceDisplay.process(message)
                            trackStatus.pattern.matches(message) -> trackStatus.process(message)
                            playPause.pattern.matches(message) -> playPause.process(message)
                            ok.pattern.matches(message) -> ok.process(message)
                            else -> unhandledMessaged(message)
                        }
                    }
                }
            }

        }

    }


    private fun unhandledMessaged(message: String) {
        logger.debug("Received message '$message' from Nuvo that has no corresponding handler. AbstractReceivableMessage ignored")
    }


}