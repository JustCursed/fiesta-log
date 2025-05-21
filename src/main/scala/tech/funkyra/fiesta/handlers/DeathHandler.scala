package tech.funkyra.fiesta.handlers

import com.google.common.base.Joiner
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.{EventPriority, SubscribeEvent}
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.server.FMLServerHandler
import net.voidstudio.fiesta.DeathData
import tech.funkyra.fiesta.Connection.collector
import tech.funkyra.fiesta.Helper.{formatPos, future, getItemLog, getTime}
import tech.funkyra.fiesta.Main.{Server, preInit}

import java.util.stream.Collectors

@EventBusSubscriber
object DeathHandler {
	@SubscribeEvent(EventPriority.HIGHEST)
	def onDeath(e: LivingDeathEvent): Unit = {
		e.getEntity match {
			case player: EntityPlayerMP =>
				collector.saveDeathLog(
					DeathData.newBuilder()
						.setTime(getTime)
						.setServer(Server)
						.setCords(formatPos(player.getPosition))
						.setPlayer(player.getName)
						.setInventory(formatDroppedItems(player))
						.setKiller(e.getSource.getTrueSource.getName)
						.build()
				)
			case _ =>
		}
	}

	private def formatDroppedItems(player: EntityPlayerMP): String = {
		player.inventory.mainInventory.toArray.filterNot(_.equals(ItemStack.EMPTY)).map(obj =>
			getItemLog(obj.asInstanceOf[ItemStack])
		).mkString(",")
	}
}
