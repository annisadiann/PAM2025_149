package com.example.posyandu.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.navigation.Screen
import com.example.posyandu.viewmodel.IbuBalitaViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: IbuBalitaViewModel,
    onMenuSelected: (String) -> Unit
) {
    // FIX: Menggunakan statsDashboard agar angka tetap muncul (tidak 0) saat login/logout
    val totalTerdaftar by viewModel.statsDashboard.collectAsState()

    // Format tanggal untuk menampilkan bulan sekarang
    val sdf = SimpleDateFormat("MMMM yyyy", Locale("id", "ID"))
    val periodeSekarang = sdf.format(Date())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Posyandu Digital", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFF69B4)),
                actions = {
                    IconButton(onClick = { onMenuSelected("logout") }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout", tint = Color.White)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFFFFAFA))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFFF69B4),
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .padding(24.dp)
            ) {
                Column {
                    Text("Halo, Admin!", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("Ringkasan Data Pemeriksaan", color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    // Card statistik yang sekarang stabil datanya
                    Surface(
                        color = Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CheckCircle, null, tint = Color.White, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "$totalTerdaftar Balita telah diperiksa ",
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Menu Utama",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    MenuCard(
                        title = "Data Ibu/Balita",
                        icon = Icons.Default.Face,
                        color = Color(0xFFFFD1DC),
                        onClick = { onMenuSelected(Screen.IbuBalita.route) }
                    )
                }
                item {
                    MenuCard(
                        title = "Pemeriksaan & Kontrol",
                        icon = Icons.Default.Favorite,
                        color = Color(0xFFF8BBD0),
                        onClick = { onMenuSelected(Screen.LayananKlinis.route) }
                    )
                }
                item {
                    MenuCard(
                        title = "Laporan",
                        icon = Icons.Default.List,
                        color = Color(0xFFF06292),
                        onClick = { onMenuSelected(Screen.Laporan.route) }
                    )
                }
            }
        }
    }
}

@Composable
fun MenuCard(title: String, icon: ImageVector, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF880E4F)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF880E4F),
                fontSize = 14.sp
            )
        }
    }
}