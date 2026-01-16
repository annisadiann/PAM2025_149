package com.example.posyandu.ui.ibubalita

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.data.local.entity.IbuBalita
import com.example.posyandu.data.model.BalitaWithIbu
import com.example.posyandu.viewmodel.IbuBalitaViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IbuBalitaScreen(
    viewModel: IbuBalitaViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToAddIbu: () -> Unit,
    onNavigateToEditIbu: (Int) -> Unit
) {
    // Sekarang berisi StateFlow<List<BalitaWithIbu>>
    val listIbu by viewModel.allIbu.collectAsState()

    // State untuk Dialog Konfirmasi (Ganti tipe data ke BalitaWithIbu)
    var showDeleteDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<BalitaWithIbu?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Hapus Data", fontWeight = FontWeight.Bold) },
            text = {
                // Akses data melalui itemToDelete?.ibu atau itemToDelete?.balita
                Text("Apakah Anda yakin ingin menghapus data Ibu ${itemToDelete?.ibu?.nama_ibu} dan Balita ${itemToDelete?.balita?.nama_balita}?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        itemToDelete?.let { data ->
                            // Hapus data ibu (balita akan ikut terhapus otomatis karena CASCADE)
                            viewModel.deleteIbu(data.ibu)
                            scope.launch {
                                snackbarHostState.showSnackbar("Data berhasil dihapus")
                            }
                        }
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                ) {
                    Text("Ya, Hapus", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Data Ibu & Balita", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Kembali", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF69B4))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddIbu,
                containerColor = Color(0xFFFF69B4),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, "Tambah")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFFFAFA))
        ) {
            if (listIbu.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.Info, null, Modifier.size(80.dp), Color.LightGray)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Belum ada data ibu & balita", color = Color.Gray)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(listIbu) { data ->
                        IbuBalitaItemRow(
                            data = data,
                            // Gunakan data.ibu.id_ibu
                            onEditClick = { onNavigateToEditIbu(data.ibu.id_ibu) },
                            onDeleteClick = {
                                itemToDelete = data
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IbuBalitaItemRow(
    data: BalitaWithIbu, // Ganti tipe data parameter
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Akses melalui data.ibu atau data.balita
                Text(text = "Ibu: ${data.ibu.nama_ibu}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    text = "Anak: ${data.balita.nama_balita}",
                    color = Color(0xFFFF69B4),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "${data.balita.jenis_kelamin} | Lahir: ${dateFormat.format(data.balita.tanggal_lahir)}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(text = "Alamat: ${data.ibu.alamat}", fontSize = 12.sp)
                Text(text = "Telp: ${data.ibu.no_telp}", fontSize = 12.sp, color = Color.DarkGray)
            }
            Row {
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, "Edit", tint = Color(0xFF1976D2))
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Default.Delete, "Hapus", tint = Color(0xFFD32F2F))
                }
            }
        }
    }
}