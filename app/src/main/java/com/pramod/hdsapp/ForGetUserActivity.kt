package com.pramod.hdsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
class ForGetUserActivity : ComponentActivity() {
    private val  forgetViewModel:ForgetViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HdsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    forgetUser()
                }
            }
        }
    }


@Composable
fun forgetUser() {

    val email = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        androidx.compose.material.Text(
            text = "ForgetUser",
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            color = Color.Blue,

            modifier = Modifier.fillMaxWidth(),

            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(value = email.value, onValueChange = {
            it
            email.value = it
        }, modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    tint = Color.Blue,
                    contentDescription = "email"
                )

            }, singleLine = true,
            label = {
                androidx.compose.material.Text(text = "Email", color = Color.Blue)
            })
        Spacer(modifier = Modifier.height(10.dp))






        OutlinedButton(
            onClick = {
                val email = email.value.toString().trim()
                Log.e("login: ", email)

                forgetViewModel.forgetUserEmail(email)
                val i = Intent(applicationContext, OtpVerifyActivity::class.java)
                startActivity(i)

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
        ) {
          Text(text = "Submit", fontSize = 20.sp, color = Color.White)

        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Account Login click here",
            fontSize = 20.sp,

            color = colorResource(id = R.color.purple_700),
            modifier = Modifier.clickable {
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            },
            textAlign = TextAlign.Center
        )


    }
}
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HdsAppTheme {
            forgetUser()
        }
    }
}
