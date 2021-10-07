package tech.thatgravyboat.reportplus.ui

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.percent
import gg.essential.elementa.dsl.pixels
import gg.essential.elementa.effects.OutlineEffect
import gg.essential.elementa.utils.withAlpha
import gg.essential.vigilance.gui.VigilancePalette
import tech.thatgravyboat.reportplus.ExternalConfiguration

class UIReportOption(val reportType: ExternalConfiguration.ReportType) : UIBlock(VigilancePalette.getHighlight().withAlpha(204)) {

    init {
        constrain {
            height = 30.pixels()
            width = 98.percent()
            y = SiblingConstraint(2f)
        }

        UIText(reportType.displayName).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf this
    }

    fun setSelected(selected: Boolean) {
        if (selected) {
            enableEffect(OutlineEffect(VigilancePalette.getAccent(), 1F, drawInsideChildren = true))
        } else {
            removeEffect<OutlineEffect>()
        }
    }

}
