package tech.funkyra.fiesta

import io.grpc.{Channel, ManagedChannelBuilder}
import net.voidstudio.fiesta.CollectorGrpc
import net.voidstudio.fiesta.CollectorGrpc.CollectorFutureStub

object Connection {
	private val channel: Channel = ManagedChannelBuilder.forTarget("host").usePlaintext.build
	val collector: CollectorFutureStub = CollectorGrpc.newFutureStub(channel)
//	val inf: InformationFutureStub = InformationGrpc.newFutureStub(channel)
}
