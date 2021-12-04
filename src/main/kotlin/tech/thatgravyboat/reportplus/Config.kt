package tech.thatgravyboat.reportplus

import gg.essential.universal.UDesktop
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File
import java.net.URI

@Suppress("unused")
object Config : Vigilant(File("./config/reportplus.toml")) {

    @Property(type = PropertyType.SWITCH, "Enable", "General", description = "Enables the mod.")
    var enable = true

    @Property(type = PropertyType.BUTTON, "Discord", "General", "Self Promotion", placeholder = "Visit")
    fun discord() {
        UDesktop.browse(URI("https://discord.gg/jRhkYFmpCa"))
    }

    @Property(type = PropertyType.BUTTON, "Patreon", "General", "Self Promotion", placeholder = "Visit")
    fun patreon() {
        UDesktop.browse(URI("https://patreon.com/thatgravyboat"))
    }

    @Property(type = PropertyType.BUTTON, "Twitter", "General", "Self Promotion", placeholder = "Visit")
    fun twitter() {
        UDesktop.browse(URI("https://twitter.com/ThatGravyBoat"))
    }

    @Property(type = PropertyType.BUTTON, "Github", "General", "Self Promotion", placeholder = "Visit")
    fun github() {
        UDesktop.browse(URI("https://github.com/ThatGravyBoat/ReportPlus"))
    }

    @Property(type = PropertyType.BUTTON, "YouTube", "General", "Self Promotion", placeholder = "Visit")
    fun rickroll() {
        UDesktop.browse(URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
    }

    init {
        initialize()
    }
}
