package tech.thatgravyboat.reportplus

import gg.essential.api.EssentialAPI
import gg.essential.universal.ChatColor
import gg.essential.universal.UChat
import net.minecraft.client.Minecraft
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import org.lwjgl.Sys
import tech.thatgravyboat.reportplus.ui.ReportGui

class ReportCommand : CommandBase() {

    override fun getCommandName(): String = "report"
    override fun getCommandUsage(sender: ICommandSender?): String = "/$commandName <username>"
    override fun canCommandSenderUseCommand(sender: ICommandSender?): Boolean = true

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        if (args == null || args.isEmpty()) {
            return UChat.chat("${ChatColor.RED}Please specify a player you would like to report (${getCommandUsage(sender)})")
        }
        if (args.size > 1) {
            UChat.say("/report ${args.joinToString(" ")}")
        } else {
            if (Minecraft.getMinecraft().session.profile.name.equals(args[0], true)) {
                return UChat.chat("${ChatColor.RED}You can not report yourself!")
            }
            EssentialAPI.getGuiUtil().openScreen(ReportGui(args[0]))
        }
    }

    companion object {
        fun finalizeReport(username: String, id: String) {
            UChat.say("/report $username -b $id -C")
            RewardClaim.userReportedTime = System.currentTimeMillis()
        }
    }
}
