package org.nuvo.message.transmit

private fun Boolean.toOnOff() = if (this) "ON" else "OFF"

enum class MessageType(internal val intValue: Int) { INFO(0), WARNING(1), ERROR(2), FLASH(3) }
enum class DwellTime(internal val intValue: Int) { NORMAL(0), SHORT(1), LONG(2)}

//Transport Commands
class PlayPause(zone: Int) : AbstractTransmittableMessage("*Z${zone}PLAYPAUSE")
class Next(zone: Int) : AbstractTransmittableMessage("*Z${zone}NEXT")
class Prev(zone: Int) : AbstractTransmittableMessage("*Z${zone}PREV")

class Power(zone: Int, state: Boolean) : AbstractTransmittableMessage("*Z$zone${state.toOnOff()}")
class Source(zone: Int, source: Int) : AbstractTransmittableMessage("*Z${zone}SRC${source}")
class Volume(zone: Int, volume: Int) : AbstractTransmittableMessage("*Z${zone}VOL${volume}")
class Mute(zone: Int, state: Boolean) : AbstractTransmittableMessage("*Z${zone}MUTE${state.toOnOff()}")
class Message(zone: Int, message: String, type:MessageType = MessageType.INFO, dwell:DwellTime = DwellTime.NORMAL) :
    AbstractTransmittableMessage("""*Z${zone}MSG"${message.take(50)}",${type.intValue},${dwell.intValue}""")

