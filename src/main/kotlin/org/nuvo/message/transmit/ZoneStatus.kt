package org.nuvo.message.transmit

class ZoneStatus(zoneNumber: Int) : AbstractTransmittableMessage("*Z${zoneNumber}STATUS?")
