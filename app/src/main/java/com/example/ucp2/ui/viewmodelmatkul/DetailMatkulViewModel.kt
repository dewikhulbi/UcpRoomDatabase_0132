package com.example.ucp2.ui.viewmodelmatkul


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Matkul
import com.example.ucp2.repository.RepositoryMatkul
import com.example.ucp2.ui.navigation.DestinasiDetailMatkul
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMatkulViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMatkul: RepositoryMatkul,

    ) : ViewModel() {
    private val kode: String = checkNotNull(savedStateHandle[DestinasiDetailMatkul.KODE])

    val detailUiState: StateFlow<DetailUiState> = repositoryMatkul.getMatkul(kode)
        .filterNotNull()
        .map {
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true,
            ),
        )

    fun deleteMatkul() {
        detailUiState.value.detailUiEvent.toMatkulEntity().let {
            viewModelScope.launch {
                repositoryMatkul.deleteMatkul(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: MatkulEvent = MatkulEvent (),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == MatkulEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != MatkulEvent ()
}

/*
Data class untuk menampung data yang akan ditampilkan di UI
*/
//memindahkan data dari entity ke ui
fun Matkul.toDetailUiEvent(): MatkulEvent {
    return MatkulEvent(
        kode = kode,
        nama = nama,
        sks = sks,
        semester = semester,
        jenis = jenis,
        dosenPengampu = dosenPengampu,
    )
}

