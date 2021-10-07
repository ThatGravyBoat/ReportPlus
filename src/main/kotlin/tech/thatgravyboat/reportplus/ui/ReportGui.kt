package tech.thatgravyboat.reportplus.ui

import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.*
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.RainbowColorConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import gg.essential.elementa.utils.withAlpha
import gg.essential.universal.ChatColor
import gg.essential.vigilance.gui.VigilancePalette
import tech.thatgravyboat.reportplus.ExternalConfiguration
import tech.thatgravyboat.reportplus.ReportCommand

class ReportGui(private val username: String) : WindowScreen() {

    private var reportReason: ExternalConfiguration.ReportType? = null

    private val background = UIBlock(VigilancePalette.getBackground().withAlpha(200)).constrain {
        width = 100.percent()
        height = 100.percent()
    } childOf this.window

    private val reportBackground = UIBlock(VigilancePalette.getBackground()).constrain {
        width = 30.percent()
        height = 80.percent()
        x = CenterConstraint()
        y = CenterConstraint()
    } childOf background

    init {
        UIWrappedText("${ChatColor.DARK_GRAY}Reporting: ${ChatColor.WHITE}$username", centered = true).constrain {
            x = CenterConstraint()
            y = 7.pixels() + 2.5.percent()
            width = 100.percent()
            if ("thatgravyboat".equals(username, true)) {
                color = RainbowColorConstraint(speed = 1f)
            }
        } childOf reportBackground

        UIContainer().let { container ->
            container.constrain {
                width = 90.percent()
                height = 80.percent() - 24.pixels()
                y = 5.percent() + 24.pixels()
                x = CenterConstraint()
            } childOf reportBackground
            ScrollComponent("Error No Report Options Found!").apply {
                constrain {
                    width = 100.percent() - 2.pixels()
                    height = 100.percent()
                } childOf container
                val scrollBar = UIBlock().constrain {
                    x = SiblingConstraint() - 2.pixels()
                    width = 3.pixels()
                    color = VigilancePalette.getScrollBar().constraint
                } childOf container

                setScrollBarComponent(scrollBar, true, false)
                Array(ExternalConfiguration.reportTypes.size) { UIReportOption(ExternalConfiguration.reportTypes[it]) childOf this }.also { array ->
                    for (option in array) {
                        option.onMouseClick {
                            if (this is UIReportOption) {
                                reportReason = this.reportType
                                for (opt in array) opt.setSelected(opt == this)
                            }
                        }
                    }
                }
            }
        }
        val cancelText = UIText("${ChatColor.BOLD}Cancel", shadow = false)
        val confirmText = UIText("${ChatColor.BOLD}Confirm", shadow = false)
        UIButton(null, confirmText.getWidth() + 10f, CenterConstraint() - 2.percent(), cancelText, Alignment.RIGHT, Type.RED).apply {
            onMouseClick { restorePreviousScreen() }
        } childOf reportBackground
        UIButton(null, confirmText.getWidth() + 10f, CenterConstraint() + 2.percent(), confirmText, Alignment.LEFT).apply {
            onMouseClick {
                reportReason?.let {
                    ReportCommand.finalizeReport(username, it.reasonId)
                    restorePreviousScreen()
                }
            }
        } childOf reportBackground
    }

    override fun onDrawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.window.draw()
    }
}
