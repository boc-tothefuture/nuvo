package org.nuvo.message.transmit

class GetSourceConfig(source: Int) : AbstractTransmittableMessage("*SCFG${source}STATUS?")
