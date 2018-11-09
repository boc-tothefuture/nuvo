package org.nuvo.message.transmit

import org.nuvo.event.Status

class SetSourceTrackStatus(source: Int,
                           duration: Int,
                           position: Int,
                           status: Status) : AbstractTransmittableMessage("*S${source}DISPINFO,$duration,${position},${status.code}")
