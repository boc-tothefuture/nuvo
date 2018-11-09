package org.nuvo.message.transmit

class SetSourceDisplayLine(source: Int,
                           lineNumber: Int,
                           line: String) : AbstractTransmittableMessage("""*S${source}DISPLINE$lineNumber"$line"""")
