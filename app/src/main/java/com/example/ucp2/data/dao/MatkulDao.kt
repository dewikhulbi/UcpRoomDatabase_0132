package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.Matkul
import kotlinx.coroutines.flow.Flow

@Dao
interface MatkulDao {
    @Insert
    suspend fun insertMatkul(
        mataKuliah: Matkul
    )

    @Query("SELECT * FROM matkul ORDER BY nama ASC")
    fun getAllMatkul () : Flow<List<Matkul>>

    @Query ("SELECT * FROM matkul WHERE kode = :kode")
    fun getMatkul (kode: String) : Flow<Matkul>

    @Delete
    suspend fun deleteMatkul (matkul: Matkul)

    @Update
    suspend fun updateMatkul (matkul: Matkul)
}