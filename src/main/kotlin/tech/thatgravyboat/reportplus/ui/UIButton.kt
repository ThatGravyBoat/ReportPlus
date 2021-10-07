package tech.thatgravyboat.reportplus.ui

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.XConstraint
import gg.essential.elementa.dsl.*
import gg.essential.vigilance.gui.VigilancePalette
import java.awt.Color

private val BUTTON_HOVER = Color(0, 212, 105)
private val RED_BUTTON = Color(255, 65, 65)
private val RED_BUTTON_HOVER = Color(255, 95, 95)

class UIButton(
    private val image: UIImage?,
    private val widthIn: Float,
    private var xConstraint: XConstraint,
    private val text: UIText,
    alignment: Alignment,
    type: Type = Type.GREEN
) : UIBlock(if (type == Type.GREEN) VigilancePalette.getAccent() else RED_BUTTON) {

    init {
        if (alignment == Alignment.LEFT) {
            xConstraint += (widthIn / 2f + 9f).pixel()
        } else if (alignment == Alignment.RIGHT) {
            xConstraint -= (widthIn / 2f + 9f).pixel()
        }

        constrain {
            width = widthIn.pixel()
            height = 17.pixel()
            x = xConstraint
            y = 95.percent() - 17.pixel()
        }

        text.constrain {
            x = CenterConstraint() - (if (image != null) 9 else 0).pixel()
            y = CenterConstraint()
        } childOf this

        image?.let {
            it.constrain {
                width = 9.pixel()
                height = 9.pixel()
                x = 100.percent() + 9.pixel()
                y = CenterConstraint()
            } childOf text
        }
        this.onMouseEnter { this.setColor(if (type == Type.GREEN) BUTTON_HOVER else RED_BUTTON_HOVER) }
        this.onMouseLeave { this.setColor(if (type == Type.GREEN) VigilancePalette.getAccent() else RED_BUTTON) }
    }
}

enum class Alignment {
    LEFT, RIGHT, MIDDLE
}

enum class Type {
    GREEN,
    RED
}
