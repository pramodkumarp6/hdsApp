package com.pramod.hdsapp

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
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
import androidx.lifecycle.Observer
import com.pramod.hdsapp.ui.theme.HdsAppTheme
import com.pramod.hdsapp.utils.Resource
import com.pramod.hdsapp.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import toast
@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    private val registerViewModel:RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HdsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Register()
                    registerMessage()
                }
            }
        }
    }


    @Composable
    fun Register() {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val name = remember { mutableStateOf("") }
        val school = remember { mutableStateOf("") }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "UserRegister", fontSize = 20.sp, fontFamily = FontFamily.Monospace,
                color = Color.Blue, modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(value = email.value, onValueChange = {
                email.value = it
            }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        tint = Color.Blue,
                        contentDescription = " "
                    )

                }, singleLine = true,
                label = {
                    Text(text = "Email", color = Color.Blue)

                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = password.value, onValueChange = {
                password.value = it
            }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        tint = Color.Blue,
                        contentDescription = " "
                    )

                }, singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),

                label = {
                    Text(text = "Password", color = Color.Blue)

                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = name.value , onValueChange = {
                name.value =it
            }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person,
                     tint = Color.Blue,
                    contentDescription = "name")
                },singleLine = true,
                label = {
                    Text(text = "Name", color = Color.Blue)

                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = school.value, onValueChange ={
                school.value = it
            },modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountBox, tint = Color.Blue, contentDescription ="" )
                }, singleLine = true,
                label = {
                    Text(text = "School",color = Color.Blue)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(onClick = {
                val email = email.value.toString().trim()
                val password = password.value.toString().trim()
                val name = name.value.toString().trim()
                val school = school.value.toString().trim()

               registerViewModel.userRegister(email,password,name,school)


            }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue) ) {
               Text(text = "Register", fontSize = 20.sp, color = Color.White)

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

    @Composable
    private fun registerMessage() {
        registerViewModel.registerdata.observe(this, Observer {


            when (it) {
                is Resource.Success -> {
                    toast(it.data!!.message.toString())
                }
                is Resource.Error -> {
                    toast(it.message.toString())
                }
                is Resource.Loading -> {}
            }
        })
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HdsAppTheme {
            Register()
            registerMessage()
        }
    }
}