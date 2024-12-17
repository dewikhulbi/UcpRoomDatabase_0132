package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.dao.MatkulDao
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.data.entity.Matkul

@Database(entities = [Dosen::class, Matkul::class], version = 2, exportSchema = false)
abstract class DomaDatabase : RoomDatabase() {

    // Mendefinisikan fungsi untuk mengakses DAO masing-masing entitas
    abstract fun dosenDao(): DosenDao
    abstract fun mataKuliahDao(): MatkulDao

    companion object {
        @Volatile // Memastikan bahwa nilai variabel instance selalu sama di semua thread
        private var Instance: DomaDatabase? = null
        fun getDatabase(context: Context): DomaDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DomaDatabase::class.java, // class database
                    "DomaDatabase" // nama database
                )
                    .fallbackToDestructiveMigration() // Menangani perubahan skema jika diperlukan
                    .build().also { Instance = it }
            })
        }
    }
}
