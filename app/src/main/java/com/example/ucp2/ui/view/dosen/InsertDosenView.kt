package com.example.ucp2.ui.view.dosen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import com.example.ucp2.ui.navigation.AlamatNavigasiDosen
import com.example.ucp2.ui.viewmodeldosen.DosenEvent
import com.example.ucp2.ui.viewmodeldosen.DosenUIState
import com.example.ucp2.ui.viewmodeldosen.DosenViewModel
import com.example.ucp2.ui.viewmodeldosen.FormErrorState
import com.example.ucp2.ui.viewmodeldosen.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertDosen : AlamatNavigasiDosen{
    override val route: String = "insert_dosen"
}//object dikenal sebagai nama halamnan di insertmhsview

@Composable
fun InsertDosenView(
    onBack:() -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState=viewModel.uiState //ambil ui state dari viewmodel
    val snackbarHostState = remember { SnackbarHostState() } //snackbar state
    val coroutineScope = rememberCoroutineScope()

    //observasi perubahan snackbarmassage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message) //tampilansnackbar
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ){ padding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Dosen",
                modifier = Modifier

            )
            //isi body
            InsertBodyDosen(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent) //update state di viewmodel
                },
                onClick = {
                    viewModel.saveData() //simpan data
                    onNavigate()
                }
            )
        }
    }
}
@Composable
fun InsertBodyDosen(
    modifier: Modifier = Modifier,
    onValueChange: (DosenEvent) -> Unit,
    uiState: DosenUIState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormDosen(
            dosenEvent = uiState.dosenEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick=onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormDosen(
    dosenEvent: DosenEvent = DosenEvent(),
    onValueChange : (DosenEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
){
    val jenisKelamin = listOf("Laki-laki","Perempuan")

    Column (modifier = modifier.fillMaxWidth())
    {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nama,
            onValueChange = {
                onValueChange(dosenEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukan nama") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = dosenEvent.nidn,
            onValueChange = {
                onValueChange(dosenEvent.copy(nidn = it))
            },
            label = { Text("NIDN") },
            isError = errorState.nidn != null,
            placeholder = { Text("Masukan NIDN") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Kelamin")
        Row (modifier = Modifier.fillMaxWidth()
        ){
            jenisKelamin.forEach{jk ->
                Row  (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = dosenEvent.jenisKelamin == jk,
                        onClick = {
                            onValueChange(dosenEvent.copy(jenisKelamin = jk))
                        },
                    )
                    Text(
                        text = jk,
                    )
                }
            }
        }
    }
}