package com.example.posyandu.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.posyandu.viewmodel.AdminViewModel
import com.example.posyandu.data.local.entity.Admin
import com.example.posyandu.utils.ValidationHelper


@Composable
fun RegisterScreen(
    viewModel: AdminViewModel ,
    onRegisterSuccess: () -> Unit
) {
    var namaAdmin by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFD1DC), Color(0xFFFF69B4))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Daftar Akun",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF1493)
                )
                Text(text = "Lengkapi data admin baru", color = Color.Gray)

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = namaAdmin,
                    onValueChange = { namaAdmin = it },
                    label = { Text("Nama Lengkap") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                // 🔴 Pesan error (Poin d)
                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                val scope = rememberCoroutineScope()

                Button(
                    onClick = {
                        if (ValidationHelper.isRegisterValid(
                                username,
                                password,
                                namaAdmin
                            )
                        ) {
                            val newAdmin = Admin(
                                username = username,
                                password = password,
                                nama = namaAdmin
                            )

                            viewModel.register(newAdmin) // Simpan ke DB
                            onRegisterSuccess() // Kembali ke login
                            viewModel.register(newAdmin)
                            onRegisterSuccess()
                        } else {
                            errorMessage = "Semua field wajib diisi"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF69B4)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("DAFTAR SEKARANG", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

