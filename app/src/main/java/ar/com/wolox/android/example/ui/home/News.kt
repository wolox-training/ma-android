package ar.com.wolox.android.example.ui.home

import org.joda.time.format.DateTimeFormat
import org.ocpsoft.prettytime.PrettyTime
import java.util.Locale

// class News(var author: String, var newsText: String, var time: String)

class News(var id: Int, var userId: Int, var createdAt: String, var title: String, var picture: String, var text: String) {
    /*var id: Int
    var userId : Int
    var createdAT : String
    var title : String
    var picture : String
    var text : String*/

    /*fun getTimeReference(): DateTime {
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ") // "dd/MM/yyyy HH:mm:ss"  "yyyy-MM-dd'T'HH:mm:ss.SSZZ"
        // val dateTime = formatter.parseDateTime(createdAt) as DateTime
        val dateTime = formatter.parseDateTime(createdAt)
        return dateTime
    }*/
    fun getTimeReference(): String {
        val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ") // "dd/MM/yyyy HH:mm:ss"  "yyyy-MM-dd'T'HH:mm:ss.SSZZ"
        // val dateTime = formatter.parseDateTime(createdAt) as DateTime
        val dateTime = formatter.parseDateTime(createdAt)
        val prettyTime = PrettyTime(Locale.getDefault())
        // val ago: String = prettyTime.format(DateTime(dateTime))
        val ago: String = prettyTime.format(dateTime.toDate())
        return ago
        // var now = Date()
        // val time : String = p.format(Date(System.currentTimeMillis() - dateTime))
        // return dateTime
    }
}
