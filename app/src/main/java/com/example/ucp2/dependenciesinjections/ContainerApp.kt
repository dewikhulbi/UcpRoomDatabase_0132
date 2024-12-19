package com.example.ucp2.dependenciesinjections

import android.content.Context
import com.example.ucp2.data.database.DomaDatabase
import com.example.ucp2.repository.LocalRepositoryDosen
import com.example.ucp2.repository.LocalRepositoryMatkul
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMatkul

interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoryMatkul: RepositoryMatkul
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(DomaDatabase.getDatabase(context).dosenDao())
    }
   override val repositoryMatkul: RepositoryMatkul by lazy {
       LocalRepositoryMatkul(DomaDatabase.getDatabase(context).matkulDao())
    }
}

