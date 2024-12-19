package com.example.ucp2.repository

import com.example.ucp2.data.dao.MatkulDao
import com.example.ucp2.data.entity.Matkul
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMatkul (
    private val matkulDao: MatkulDao) : RepositoryMatkul {

        override suspend fun insertMatkul(matkul: Matkul) {
            matkulDao.insertMatkul(matkul)
        }

        override fun getAllMatkul(): Flow<List<Matkul>>{
            return matkulDao.getAllMatkul()
        }

        override fun getMatkul(kode: String): Flow<Matkul> {
            return matkulDao.getMatkul(kode)
        }

        override suspend fun deleteMatkul(matkul: Matkul) {
            matkulDao.deleteMatkul(matkul)
        }

        override suspend fun updateMatkul(matkul: Matkul) {
            matkulDao.updateMatkul(matkul)
        }
}