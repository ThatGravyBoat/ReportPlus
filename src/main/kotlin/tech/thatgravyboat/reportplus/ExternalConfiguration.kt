package tech.thatgravyboat.reportplus

import com.google.gson.Gson
import gg.essential.api.utils.WebUtil

private val GSON = Gson()

object ExternalConfiguration {
    lateinit var reportTypes: List<ReportType>

    fun loadData() {
        WebUtil.fetchString("https://gist.githubusercontent.com/ThatGravyBoat/ea033f7cb668eeef2a2342f92f2c309b/raw/7082570886c274b764e8bdc07f3326d28b3941f3/reportTypes.json")?.let {
            val config = GSON.fromJson(it, JsonConfig::class.java)
            reportTypes = config.reportTypes
        }
    }

    private data class JsonConfig(
        val reportTypes: List<ReportType> = listOf()
    )

    data class ReportType(
        val reasonId: String,
        val displayName: String
    )
}
