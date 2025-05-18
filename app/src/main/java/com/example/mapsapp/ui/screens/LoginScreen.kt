package com.example.mapsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mapsapp.R
import com.example.mapsapp.viewmodels.AuthViewModel

@Composable
fun LoginScreen(authViewModel: AuthViewModel, navigateMap: () -> Unit) {
    val email by authViewModel.email.observeAsState("")
    val password by authViewModel.password.observeAsState("")
    var passwordVisibility by remember { mutableStateOf(false) }
    val image = if (passwordVisibility) {
        Icons.Outlined.Lock
    } else {
        Icons.Default.Lock
    }


    Column(
        modifier = Modifier.fillMaxSize().padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { authViewModel.editEmail(it) },
                label = { Text("Email") }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 48.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            OutlinedTextField(
                value = password,
                onValueChange = { authViewModel.editPassword(it) },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "Password visibility")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = { authViewModel.signIn(); navigateMap() }
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(6.dp))

        Button(
            onClick = { authViewModel.signUp() }
        ) {
            Text("Sign Up")
        }
    }
}