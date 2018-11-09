package org.nuvo.message.transmit

class DisplayMessage(message: String) : AbstractTransmittableMessage("""*MSG"${message.take(50)}"""")


