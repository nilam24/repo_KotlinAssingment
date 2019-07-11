package com.example.ktask.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(ModelPlaces::class), version = 8,exportSchema = false)
abstract class PlaceAppDatabase : RoomDatabase() {

    abstract fun placedao(): PlaceDao
//    val CREATE_PLACE_TABLE =
//        (" CREATE_TABLE " + " place " + " ( " + " COLUMN_ID " + " INTEGER PRIMARY KEY , " + " COLUMN_PLACE_ID " + " TEXT " + " COLUMN_NAME " + " TEXT " + " ) ")

    companion object {

        private var INSTANCE: PlaceAppDatabase? = null
        fun getDatabase(context: Context): PlaceAppDatabase? {
            if (INSTANCE == null) {
                synchronized(PlaceAppDatabase::class) {
                    INSTANCE = createInstance(context)

                }
            }
            return INSTANCE
        }
        private fun createInstance(context: Context): PlaceAppDatabase? {
            var room = Room.databaseBuilder(
                context.getApplicationContext(),
                PlaceAppDatabase::class.java, "chapter.db"
            )
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        Thread {

                            prepopulateDb(context, getDatabase(context))
                        }.start()
                    }

                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        Thread {

                            prepopulateDb(context, getDatabase(context))
                        }.start()
                    }
                }).build()
            return room
        }

        private fun prepopulateDb(context: Context, database: PlaceAppDatabase?) {
         var modelPlaces = ModelPlaces("4bcca12bb6c49c7422169491", "86th Floor Observation Deck",true)
         database!!.placedao().insert(modelPlaces)
        }
    }
}





