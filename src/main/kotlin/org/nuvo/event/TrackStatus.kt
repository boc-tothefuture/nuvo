package org.nuvo.event

enum class Status(val code: Int) {

    NORMAL(0),
    IDLE(1),
    PLAYING(2),
    PAUSED(3),
    FAST_FORWARD(4),
    REWIND(5),
    PLAY_SHUFFLE(6),
    PLAY_REPEAT(7),
    PLAY_SHUFFLE_REPEAT(8);

    companion object {
        private val map = values().associateBy(Status::code)
        fun fromCode(code: Int) = map.getValue(code)
    }


}

data class TrackStatus(val source: Int, val duration: Int, val position: Int, val status: Status)
