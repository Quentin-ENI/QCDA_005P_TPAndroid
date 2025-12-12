package fr.eni.ecole.enishop.bo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.Date

@Entity(tableName = "Article")
data class Article(
    @PrimaryKey(autoGenerate = false)
   val id: Long = 0,
    @Json(name = "title")
   val name: String = "",
   val description: String = "",
   val price: Double = 0.0,
    @Json(name = "image")
   val urlImage: String = "",
   val category: String = "",
    @Json(ignore = true)
   val date: Date = Date()
)
