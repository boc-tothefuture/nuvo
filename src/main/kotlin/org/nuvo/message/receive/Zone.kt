package org.nuvo.message.receive

import mu.KotlinLogging
import org.nuvo.MessageQueue
import org.nuvo.bus.EventBus
import org.nuvo.event.*

class Zone(eventBus: EventBus, messageQueue: MessageQueue) : AbstractReceivableMessage("""#Z\d+,.*""", eventBus, messageQueue) {

    private val logger = KotlinLogging.logger {}

    override fun process(message: String) {
        val (zone, power) = message.split(",")
        val zoneNumber = zone.takeLast(1).toInt()
        val powerState = PowerState.valueOf(power)
        eventBus.post(ZonePower(zoneNumber, powerState))
        if (powerState == PowerState.ON) {
            val (_, _, source, volume) = message.split(",")
            val zoneSource = source.takeLast(1).toInt()
            eventBus.post(ZoneSource(zoneNumber, zoneSource))
            if (volume == "MUTE") {
                eventBus.post(ZoneMute(zoneNumber, MuteState.ON))
            } else {
                eventBus.post(ZoneMute(zoneNumber, MuteState.OFF))
                """VOL(?<volume>\d+)""".toRegex().matchEntire(volume)?.groups?.get("volume")?.value?.toInt()?.let {
                    eventBus.post(ZoneVolume(zoneNumber, it))
                }
            }
        }
    }

}