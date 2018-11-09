package org.nuvo.event

enum class MuteState { ON, OFF }

data class ZoneMute(val zone: Int, val muteState: MuteState)
