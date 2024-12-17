package com.example.ucp2.repository

import com.example.ucp2.data.entity.Matkul
import kotlinx.coroutines.flow.Flow

interface RepositoryMatkul {
    suspend fun insertMatkul(matkul: Matkul)
    fun getAllMatkul(): Flow<List<Matkul>>
    fun getMatkul (kode: String): Flow<Matkul>
    suspend fun deleteMatkul(matkul: Matkul)
    suspend fun updateMatkul(matkul: Matkul)
}