package tech.funkyra.fiesta.handlers

import net.minecraft.entity.player.{EntityPlayer, EntityPlayerMP}
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraftforge.event.entity.item.ItemTossEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.voidstudio.fiesta.ItemData
import tech.funkyra.fiesta.Connection.collector
import tech.funkyra.fiesta.Helper.{formatPos, future, getItemLog, getTime}
import tech.funkyra.fiesta.Main.Server

@EventBusSubscriber
object ItemHandler {
	@SubscribeEvent(EventPriority.HIGHEST)
	def pickup(e: EntityItemPickupEvent): Unit = {
		print("ffffffffffffffffffffffff")
		save(e.getEntityPlayer, e.getItem.getItem, action = true)
	}

	@SubscribeEvent(EventPriority.HIGHEST)
	def drop(e: ItemTossEvent): Unit = {
		save(e.getPlayer, e.getEntityItem.getItem, action = false)
	}

	private def save(player: EntityPlayer, item: ItemStack, action: Boolean): Unit = {
		collector.saveItemLog(
			ItemData.newBuilder()
				.setPlayer(player.getName)
				.setItem(getItemLog(item))
				.setServer(Server)
				.setCords(formatPos(player.getPosition))
				.setAmount(item.getCount)
				.setAction(action)
				.setTime(getTime)
			.build()
		)
	}
}
