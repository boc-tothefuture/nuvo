package org.nuvo.message.transmit

class GetSourceTrackStatus(source: Int) : AbstractTransmittableMessage("*S${source}DISPINFO?")
