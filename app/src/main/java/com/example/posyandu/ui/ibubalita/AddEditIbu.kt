package com.example.posyandu.ui.ibubalita

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.data.local.entity.Balita
import com.example.posyandu.data.local.entity.IbuBalita
import com.example.posyandu.viewmodel.IbuBalitaViewModel
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditIbuScreen(
    ibuId: Int,
    onNavigateBack: () -> Unit,
    viewModel: IbuBalitaViewModel
) {
    var namaIbu by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var telepon by remember { mutableStateOf("") }
    var namaBalita by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("Laki-laki") }

    val isEditMode = ibuId != -1
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    LaunchedEffect(key1 = ibuId) {
        if (isEditMode) {
            // Perhatikan cara akses: it.ibu dan it.balita
            val dataExisting = viewModel.allIbu.value.find { it.ibu.id_ibu == ibuId }
            dataExisting?.let {
                namaIbu = it.ibu.nama_ibu
                alamat = it.ibu.alamat
                telepon = it.ibu.no_telp

                namaBalita = it.balita.nama_balita
                tglLahir = dateFormat.format(it.balita.tanggal_lahir)
                jenisKelamin = it.balita.jenis_kelamin
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditMode) "Edit Data Pasien" else "Tambah Pasien Baru", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Kembali", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF69B4))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("INFORMASI IBU", fontWeight = FontWeight.Bold, color = Color(0xFFFF69B4))
            OutlinedTextField(value = namaIbu, onValueChange = { namaIbu = it }, label = { Text("Nama Lengkap Ibu") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = alamat, onValueChange = { alamat = it }, label = { Text("Alamat") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = telepon, onValueChange = { telepon = it }, label = { Text("No. HP / WhatsApp") }, modifier = Modifier.fillMaxWidth())

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text("INFORMASI BALITA", fontWeight = FontWeight.Bold, color = Color(0xFFFF69B4))
            OutlinedTextField(value = namaBalita, onValueChange = { namaBalita = it }, label = { Text("Nama Lengkap Balita") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = tglLahir, onValueChange = { tglLahir = it }, label = { Text("Tanggal Lahir (dd-mm-yyyy)") }, modifier = Modifier.fillMaxWidth())

            Text("Jenis Kelamin Balita", fontSize = 14.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = jenisKelamin == "Laki-laki", onClick = { jenisKelamin = "Laki-laki" })
                Text("Laki-laki")
                Spacer(Modifier.width(16.dp))
                RadioButton(selected = jenisKelamin == "Perempuan", onClick = { jenisKelamin = "Perempuan" })
                Text("Perempuan")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (namaIbu.isNotBlank() && namaBalita.isNotBlank()) {
                        val dataIbu = IbuBalita(
                            id_ibu = if (isEditMode) ibuId else 0,
                            nama_ibu = namaIbu,
                            alamat = alamat,
                            no_telp = telepon
                        )

                        val dataBalita = Balita(
                            id_balita = 0,
                            id_ibu = if (isEditMode) ibuId else 0,
                            nama_balita = namaBalita,
                            tanggal_lahir = try { dateFormat.parse(tglLahir) ?: Date() } catch (e: Exception) { Date() },
                            jenis_kelamin = jenisKelamin
                        )

                        if (isEditMode) {
                            viewModel.updateIbu(dataIbu)
                        } else {
                            viewModel.tambahPasienBaru(dataIbu, dataBalita)
                        }
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF69B4)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Check, null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = if (isEditMode) "Simpan Perubahan" else "Simpan Ibu & Balita",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}