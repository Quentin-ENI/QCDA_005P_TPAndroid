package fr.eni.ecole.enishop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.dao.ArticleDao
import fr.eni.ecole.enishop.utils.DateConverter

@Database(entities = [Article::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "enishop_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
