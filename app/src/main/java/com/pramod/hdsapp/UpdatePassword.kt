package com.pramod.hdsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pramod.hdsapp.ui.theme.HdsAppTheme
import com.pramod.hdsapp.viewmodel.ForgetViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdatePassword : ComponentActivity() {
    private val  forgetViewModel: ForgetViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HdsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color
                    = MaterialTheme.colorScheme.background
                ) {
                    passwordUpdate()
                }
            }
        }
    }


    @Composable
    fun passwordUpdate() {
        val password = remember { mutableStateOf("") }
        val passwordConfirm = remember { mutableStateOf("") }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Updated Password", fontSize = 20.sp, fontFamily = FontFamily.Monospace,
                color = Color.Blue, modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(100.dp))
            OutlinedTextField(value = password.value, onValueChange = {
                password.value = it
            }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        tint = Color.Black,
                        contentDescription = "password"
                    )

                }, visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                singleLine = true,
                label = {
                    Text(text = "Password", color = Color.Blue, textAlign = TextAlign.Center)
                })
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = passwordConfirm.value, onValueChange = {
                passwordConfirm.value = it
            }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info
                        ,
                        tint = Color.Black,
                        contentDescription = "passwordConfirm"
                    )

                }, visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                singleLine = true,
                label = {
                    Text(text = "PasswordConfirm", color = Color.Blue, textAlign = TextAlign.Center)
                })
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            OutlinedButton(onClick = {


                val password = password.value.toString().trim()
                val passwordConfirm = passwordConfirm.value.toString().trim()
                forgetViewModel.forgetPasswordChange(password,passwordConfirm)




                val i = Intent(applicationContext, PasswordComplate::class.java)
                startActivity(i)
                                     }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue) ) {
                androidx.compose.material.Text(text = "OK", fontSize = 20.sp, color = Color.White)

            }

        }
    }



    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HdsAppTheme {
            passwordUpdate()
        }
    }
}
