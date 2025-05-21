package tech.funkyra.fiesta.handlers

import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraftforge.fml.common.gameevent.PlayerEvent.{PlayerLoggedInEvent, PlayerLoggedOutEvent}
import net.voidstudio.fiesta.LoggedData
import tech.funkyra.fiesta.Connection.collector
import tech.funkyra.fiesta.Helper.{formatPos, getTime}
import tech.funkyra.fiesta.Main.Server

@EventBusSubscriber
object LoggedHandler {
	@SubscribeEvent(EventPriority.HIGHEST)
	def joined(e: PlayerLoggedInEvent): Unit = {
		save(e.player, action = true)
	}

	@SubscribeEvent(EventPriority.HIGHEST)
	def leaved(e: PlayerLoggedOutEvent): Unit = {
		save(e.player, action = false)
	}

	private def save(player: EntityPlayer, action: Boolean): Unit = {
		collector.saveLoggedLog(
			LoggedData.newBuilder()
				.setPlayer(player.getName)
				.setServer(Server)
				.setAction(action)
				.setCords(formatPos(player.getPosition))
				.setTime(getTime)
				.build()
		)
	}
}
