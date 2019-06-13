package ar.com.wolox.android.example.model

import com.google.gson.annotations.SerializedName
import org.joda.time.format.DateTimeFormat
import org.ocpsoft.prettytime.PrettyTime
import java.util.Locale

// class News(var author: String, var newsText: String, var time: String)

data class News(
    var id: Int,
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("createdAt")
    var createdAt: String,
    var title: String,
    @SerializedName("picture")
    var picture: String,
    var text: String
) {

    fun getTimeReference(): String {
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        val dateTime = formatter.parseDateTime(createdAt)
        val prettyTime = PrettyTime(Locale.getDefault())
        val ago: String = prettyTime.format(dateTime.toDate())
        return ago
    }
}
