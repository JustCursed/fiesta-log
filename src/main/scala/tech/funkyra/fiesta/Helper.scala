package tech.funkyra.fiesta

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.math.BlockPos
import net.minecraftforge.server.permission.PermissionAPI

import java.time.{ZoneId, ZonedDateTime}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Helper {
	def future(func: => Unit): Future[Unit] = {
		Future {
			func
		}
	}

	def getTime: Long = ZonedDateTime.now(ZoneId.of("Europe/Moscow")).toEpochSecond

	def formatPos(pos: BlockPos): String = s"${pos.getX} ${pos.getY} ${pos.getZ}"

	def shouldHide(player: EntityPlayer): Boolean = PermissionAPI.hasPermission(player, "logs.bypass")

	def getItemLog(itemstack: ItemStack): String = {
		val itemName = itemstack.getItem.getRegistryName.toString
		val metadata = if (itemstack.getItemDamage == 0) s":${itemstack.getItemDamage}" else ""

		s"$itemName$metadata x${itemstack.getCount}"
	}
}
