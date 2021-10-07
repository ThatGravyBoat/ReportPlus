package tech.thatgravyboat.reportplus

import gg.essential.api.EssentialAPI
import net.minecraft.client.gui.GuiScreenBook
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import tech.thatgravyboat.reportplus.ui.ReportGui

@Mod(
    name = "Report+",
    modid = "reportplus",
    version = "1.0.0",
    modLanguageAdapter = "gg.essential.api.utils.KotlinAdapter"
)
object RewardClaim {

    var userReportedTime: Long = 0

    @Mod.EventHandler
    fun onFMLInitialization(event: FMLInitializationEvent?) {
        MinecraftForge.EVENT_BUS.register(this)
        ClientCommandHandler.instance.registerCommand(ReportCommand())
    }

    @Mod.EventHandler
    fun onPreInit(event: FMLPreInitializationEvent?) {
        ExternalConfiguration.loadData()
    }

    @SubscribeEvent
    fun onScreen(event: GuiOpenEvent) {
        if (
            EssentialAPI.getGuiUtil().openedScreen() == null &&
            event.gui is GuiScreenBook &&
            System.currentTimeMillis() - userReportedTime <= 2500
        ) {
            event.isCanceled = true
            userReportedTime = 0
        }
    }
}
