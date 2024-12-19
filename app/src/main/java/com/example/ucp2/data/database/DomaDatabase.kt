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
    //abstract fun mataKuliahDao(): MatkulDao
    abstract fun matkulDao(): MatkulDao


}
