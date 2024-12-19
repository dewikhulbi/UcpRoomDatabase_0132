package com.example.ucp2.ui.viewmodelmatkul

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Matkul
import com.example.ucp2.repository.RepositoryMatkul
import kotlinx.coroutines.launch

class MatkulViewModel (private val repositoryMatkul: RepositoryMatkul) : ViewModel(){

    var uiState by mutableStateOf(MatkulUIState())

    fun updateState(matkulEvent: MatkulEvent) {
        uiState = uiState.copy(
            matkulEvent = matkulEvent,
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.matkulEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "KODE tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "SKS tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    //menyimpan data ke repository
    fun saveData(){
        val currentEvent = uiState.matkulEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMatkul.insertMatkul(currentEvent.toMatkulEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        matkulEvent = MatkulEvent(), // reset input form
                        isEntryValid = FormErrorState()//reset error state
                    )
                } catch (e:Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        } else{
            uiState = uiState.copy(
                snackBarMessage = "Data tidak valid. Periksa kembali data anda"
            )
        }
    }
    //reset pesan snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiState = uiState.copy(
            snackBarMessage = null )
    }
}

data class MatkulUIState(
    val matkulEvent: MatkulEvent = MatkulEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage:String? = null,
)

data class FormErrorState(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenPengampu: String? = null
){
    fun isValid(): Boolean{
        return kode == null && nama == null && sks == null && semester == null && jenis == null && dosenPengampu == null
    }
}

//menyimpan input form ke dalam entity
fun MatkulEvent.toMatkulEntity(): Matkul = Matkul(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenPengampu = dosenPengampu
)

//data class variabel yang menyimpan data input form
data class MatkulEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = ""
)


