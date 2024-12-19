package com.example.ucp2.ui.view.dosen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeMenuView(
    onDosenClick: () -> Unit,
    onMatkulClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Mengatur elemen di tengah secara vertikal
        horizontalAlignment = Alignment.CenterHorizontally // Mengatur elemen di tengah secara horizontal
    ) {
        Button(
            onClick = onDosenClick,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f) // Mengatur lebar tombol menjadi 80% dari layar
        ) {
            Text(
                text = "Menu Dosen",
                fontSize = 20.sp, // Memperbesar ukuran teks
                fontWeight = FontWeight.Bold // Menambah ketebalan teks
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Jarak antar tombol

        Button(
            onClick = onMatkulClick,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.8f) // Mengatur lebar tombol menjadi 80% dari layar
        ) {
            Text(
                text = "Menu Matkul",
                fontSize = 20.sp, // Memperbesar ukuran teks
                fontWeight = FontWeight.Bold // Menambah ketebalan teks
            )
        }
    }
}
