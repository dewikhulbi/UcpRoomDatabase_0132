package com.example.ucp2.ui.viewmodeldosen

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.DomaApp
import com.example.ucp2.ui.viewmodelmatkul.DetailMatkulViewModel
import com.example.ucp2.ui.viewmodelmatkul.HomeMatkulViewModel
import com.example.ucp2.ui.viewmodelmatkul.MatkulViewModel
import com.example.ucp2.ui.viewmodelmatkul.UpdateMatkulViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            MatkulViewModel(
                domaApp().containerApp.repositoryMatkul // Lewatkan repositoryMatkul ke MatkulViewModel
            )
        }
        initializer {
            HomeMatkulViewModel(
                domaApp().containerApp.repositoryMatkul // Lewatkan repositoryMatkul ke HomeMatkulViewModel
            )
        }

        initializer {
            DetailMatkulViewModel(
                createSavedStateHandle(), // Handle untuk state
                domaApp().containerApp.repositoryMatkul // Lewatkan repositoryMatkul ke DetailMatkulViewModel
            )
        }

        initializer {
            UpdateMatkulViewModel(
                createSavedStateHandle(), // Handle untuk state
                domaApp().containerApp.repositoryMatkul // Lewatkan repositoryMatkul ke UpdateMatkulViewModel
            )
        }

        initializer {
            DosenViewModel(
                domaApp().containerApp.repositoryDosen // Lewatkan repositoryDosen ke DosenViewModel
            )
        }

        initializer {
            HomeDosenViewModel(
                domaApp().containerApp.repositoryDosen // Lewatkan repositoryDosen ke HomeDosenViewModel
            )
        }

        initializer {
            DetailDosenViewModel(
                createSavedStateHandle(), // Handle untuk state
                domaApp().containerApp.repositoryDosen // Lewatkan repositoryDosen ke DetailDosenViewModel
            )
        }
    }
}

fun CreationExtras.domaApp(): DomaApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DomaApp)