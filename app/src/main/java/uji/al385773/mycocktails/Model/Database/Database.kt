package uji.al385773.mycocktails.Model.Database

import android.content.Context
import androidx.room.Room

class Database private constructor(context: Context) {

    companion object: SingletonHolder<Database, Context>(::Database)

    val dao: DAO

    init {
        val db = Room.databaseBuilder(context,
            AbstractDatabase::class.java,
            "database").build()
        dao = db.getDAO()
    }

}

