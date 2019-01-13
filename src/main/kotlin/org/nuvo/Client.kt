package org.nuvo

import mu.KotlinLogging
import org.nuvo.bus.EventBus
import org.nuvo.event.Status
import org.nuvo.message.transmit.*
import org.nuvo.network.NetworkClient
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.concurrent.LinkedBlockingQueue

typealias MessageQueue = LinkedBlockingQueue<TransmittableMessage>


fun main(args: Array<String>) {
    val logger = KotlinLogging.logger {}
    logger.debug("Starting Test")
    val client = Client("10.0.0.70")
    client.connect()

    Thread.sleep(1000)
    // sendMessage(GetSourceConfig(5))
    client.sendMessage(SetSourceDisplayLine(5, 1, "Spotify"))
    client.sendMessage(SetSourceDisplayLine(5, 2, ""))
    client.sendMessage(SetSourceDisplayLine(5, 3, ""))
    client.sendMessage(SetSourceDisplayLine(5, 4, ""))

    val duration = 10 * Duration.of(2, ChronoUnit.MINUTES).seconds.toInt()
    for (pos in 0..duration step 10) {
        logger.debug("Position is now $pos of duration $duration")
        client.sendMessage(SetSourceTrackStatus(5, duration, pos, Status.PLAYING))
        Thread.sleep(1000)
    }
    client.refreshNuvoStatus()
}


class Client(private val host: String, private val port: Int = 5006) {

    private val logger = KotlinLogging.logger {}
    //val eventBus:EventBus = EventBus.builder().build()
    val eventBus:EventBus = EventBus
    private lateinit var networkClient: NetworkClient
    private val messageQueue = MessageQueue()

    fun connect() {
        logger.debug("Connecting to Nuvo at $host")

        networkClient = NetworkClient(host, port, eventBus, messageQueue)

    }

    fun refreshNuvoStatus() {
        for (zone in 1..16) {
            sendMessage(ZoneStatus(zone))
        }
        for (source in 1..6) {
            sendMessage(GetSourceDisplayLines(source))
            sendMessage(GetSourceTrackStatus(source))
        }
    }


    fun sendMessage(message: TransmittableMessage) {
        messageQueue.put(message)
    }


}