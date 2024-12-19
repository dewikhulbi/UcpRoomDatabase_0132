package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependenciesinjections.ContainerApp

class DomaApp: Application() {
    //fungsinya untuk menympan instance ContainerApp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //membuat instance containerapp
        containerApp = ContainerApp(this)
        //instance adalah object yang dibuat dari class
    }
}