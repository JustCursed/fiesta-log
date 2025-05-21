package tech.funkyra.fiesta

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import tech.funkyra.fiesta.Main.{ModLang, ModName}
import tech.funkyra.fiesta.handlers.{DeathHandler, LoggedHandler, MessageHandler, ItemHandler}

@Mod(
	name = ModName,
	modid = ModName,
	modLanguage = ModLang,
	acceptableRemoteVersions = "*",
	serverSideOnly = true
)
object Main {
	final val ModName = "fiesta"
	final val ModLang = "scala"
	final val Server = "magic"

	@EventHandler
	def preInit(e: FMLPreInitializationEvent): Unit = {
//		MinecraftForge.EVENT_BUS.register(new MessageHandler)
//		MinecraftForge.EVENT_BUS.register(new ItemHandler)
//		MinecraftForge.EVENT_BUS.register(new DeathHandler)
//		MinecraftForge.EVENT_BUS.register(new LoggedHandler)
	}
}
