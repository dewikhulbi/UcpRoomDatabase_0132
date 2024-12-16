package com.example.ucp2.repository

import com.example.ucp2.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

interface RepositoryMatkul {
    suspend fun insertMatkul(mataKuliah: MataKuliah)
    fun getAllMatkul(): Flow<List<MataKuliah>>
    fun getMatkul (nim: String): Flow<MataKuliah>
    suspend fun deleteMatkul(mahasiswa: MataKuliah)
    suspend fun updateMatkul(mahasiswa: MataKuliah)
}