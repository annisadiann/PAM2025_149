package com.example.posyandu.ui.klinis

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayananKlinisScreen(
    onNavigateToPemeriksaan: () -> Unit,
    onNavigateToJadwal: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pemeriksaan & Kontrol", color = Color.White, fontWeight = FontWeight.Bold) },
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
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Pilih Kategori Layanan", color = Color.Gray, fontWeight = FontWeight.Medium)

            // Tombol ke Pemeriksaan
            Card(
                onClick = onNavigateToPemeriksaan,
                modifier = Modifier.fillMaxWidth().height(110.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE4E1))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.AddCircle, null, Modifier.size(40.dp), Color(0xFFFF69B4))
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text("Pemeriksaan Balita", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Input BB, TB, dan Umur Balita", fontSize = 14.sp)
                    }
                }
            }

            // Tombol ke Jadwal Kontrol
            Card(
                onClick = onNavigateToJadwal,
                modifier = Modifier.fillMaxWidth().height(110.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.DateRange, null, Modifier.size(40.dp), Color(0xFF00ACC1))
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text("Jadwal Kontrol", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Lihat agenda kunjungan rutin", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}