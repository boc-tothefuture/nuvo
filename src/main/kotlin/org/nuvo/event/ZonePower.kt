package org.nuvo.event

enum class PowerState { ON, OFF }

data class ZonePower(val zone: Int, val powerState: PowerState)
