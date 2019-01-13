package org.nuvo.network

import io.ktor.network.selector.ActorSelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.nuvo.MessageQueue
import org.nuvo.bus.EventBus
import java.net.InetSocketAddress

@KtorExperimentalAPI
class NetworkClient(private val host: String, private val port: Int, private val eventBus: EventBus,
                    private val messageQueue: MessageQueue) {
    private lateinit var networkReader: NetworkReader
    private lateinit var networkWriter: NetworkWriter

    init {
        runBlocking {
            val socket = aSocket(ActorSelectorManager(Dispatchers.IO)).tcp().connect(InetSocketAddress(host, port))
            val input = socket.openReadChannel()
            val output = socket.openWriteChannel(autoFlush = true)
            networkReader = NetworkReader(input, eventBus, messageQueue)
            networkWriter = NetworkWriter(output, messageQueue)
        }
    }
}