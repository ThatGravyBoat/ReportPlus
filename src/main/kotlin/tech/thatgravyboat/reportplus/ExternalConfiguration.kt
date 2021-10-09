package tech.thatgravyboat.reportplus

import com.google.gson.Gson
import gg.essential.api.utils.WebUtil

private val GSON = Gson()

object ExternalConfiguration {
    lateinit var reportTypes: List<ReportType>

    fun loadData() {
        WebUtil.fetchString("https://raw.githubusercontent.com/ThatGravyBoat/ReportPlus/master/data.json")?.let {
            reportTypes = try { GSON.fromJson(it, JsonConfig::class.java).reportTypes } catch (e: Exception) { listOf() }
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
