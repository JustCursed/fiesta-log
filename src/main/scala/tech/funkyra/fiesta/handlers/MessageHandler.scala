package tech.funkyra.fiesta.handlers

import net.minecraft.command.{CommandHandler, ServerCommandManager}
import net.minecraft.entity.player.{EntityPlayer, EntityPlayerMP}
import net.minecraftforge.event.{CommandEvent, ServerChatEvent}
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraftforge.server.permission.PermissionAPI
import net.voidstudio.fiesta.ChatData
import org.bukkit.ChatColor
import tech.funkyra.fiesta.Connection.collector
import tech.funkyra.fiesta.Helper.{formatPos, future, getTime, shouldHide}
import tech.funkyra.fiesta.Main.Server

@EventBusSubscriber
object MessageHandler {
	@SubscribeEvent(EventPriority.HIGHEST)
	def onMessage(e: ServerChatEvent): Unit = {
//		val msg = ChatColor.stripColor(e.getMessage.trim)
		save(e.getPlayer, ChatColor.stripColor(e.getMessage.trim))
	}

	@SubscribeEvent(EventPriority.HIGHEST)
	def onCommand(e: CommandEvent): Unit = {
//		print("command wdwdwd       " + e.getSender.getName)
//		print("----------------------------------------------")
		save(e.getSender.asInstanceOf[EntityPlayer], e.getCommand.getName)
	}

	private def save(player: EntityPlayer, msg: String): Unit = {
		collector.saveChatLog(
			ChatData.newBuilder()
				.setPlayer(player.getName)
				.setMessage(msg)
				.setServer(Server)
				.setPrivate(shouldHide(player))
				.setCords(formatPos(player.getPosition))
				.setTime(getTime)
				.build()
		)
	}
}
