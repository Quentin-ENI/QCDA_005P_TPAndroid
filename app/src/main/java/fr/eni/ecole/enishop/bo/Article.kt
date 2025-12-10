package fr.eni.ecole.enishop.bo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Article")
data class Article(
    @PrimaryKey(autoGenerate = false)
   val id: Long = 0,
   val name: String,
   val description: String,
   val price: Double,
   val urlImage: String,
   val category: String,
   val date: Date = Date()
)
    