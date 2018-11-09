package org.nuvo.message.transmit

class GetSourceDisplayLines(source: Int) : AbstractTransmittableMessage("*S${source}DISPLINE?")
