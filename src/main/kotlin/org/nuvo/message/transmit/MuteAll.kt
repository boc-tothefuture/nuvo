package org.nuvo.message.transmit

private fun Boolean.toInt() = if (this) 1 else 0

class MuteAll(on: Boolean) : AbstractTransmittableMessage("*MUTE${on.toInt()}")


