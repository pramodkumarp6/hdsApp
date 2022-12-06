package com.pramod.hdsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pramod.hdsapp.ui.theme.HdsAppTheme
import com.pramod.hdsapp.viewmodel.ForgetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerifyActivity : ComponentActivity() {
    private val forgetViewModel:ForgetViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HdsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    otpVerify()
                }
            }
        }
    }


    @Composable
    fun otpVerify() {
        val otp = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "OtpVerifyPassword", fontSize = 20.sp, fontFamily = FontFamily.Monospace,
                color = Color.Blue, modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(100.dp))

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            OutlinedButton(onClick = {
                 val otp = otp.value.toString().trim()

                forgetViewModel.forgetUserOtp(otp)

                val i = Intent(applicationContext, UpdatePassword::class.java)
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
            otpVerify()
        }
    }
}