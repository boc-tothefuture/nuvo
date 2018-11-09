package org.nuvo.message.transmit

class PlayPause(zone: Int) : AbstractTransmittableMessage("*Z${zone}PLAYPAUSE")
class Next(zone: Int) : AbstractTransmittableMessage("*Z${zone}NEXT")
class Prev(zone: Int) : AbstractTransmittableMessage("*Z${zone}PREV")
