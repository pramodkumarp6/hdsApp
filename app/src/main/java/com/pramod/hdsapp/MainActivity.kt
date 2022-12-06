package com.pramod.hdsapp

import android.content.Intent
import android.graphics.Color.blue
import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.pramod.hdsapp.ui.theme.HdsAppTheme
import com.pramod.hdsapp.utils.Resource
import com.pramod.hdsapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import toast

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HdsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    login()
                    loginViewResult()


                }
            }
        }
    }


    @Composable
    fun login() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

           Image(painter = painterResource(id = R.drawable.img1),
               contentDescription ="" , modifier = Modifier
                   .size(200.dp)
                   .clip(RectangleShape)
           )

            
            
        }

        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "CONSULTANCY",
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Blue,

                modifier = Modifier.fillMaxWidth(),

                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = email.value, onValueChange = {

                email.value = it
            }, modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        tint = Color.Black,
                        contentDescription = "email"
                    )

                }, singleLine = true,
                label = {
                    Text(text = "Email", color = Color.Blue)
                })
            Spacer(modifier = Modifier.height(8.dp))

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
                    Text(text = "Password", color = Color.Blue)
                })

               Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "ForgetPassWord",
                fontSize = 20.sp,

                color = colorResource(id = R.color.purple_700),
                modifier = Modifier.align(Alignment.End).clickable {
                    val i = Intent(applicationContext,ForGetUserActivity::class.java)
                    startActivity(i)
                },
                textAlign = TextAlign.Right
            )
            Spacer(modifier = Modifier.height(20.dp))



            OutlinedButton(onClick = {
                val email = email.value.toString().trim()
                val password = password.value.toString().trim()
                Log.e("login: ", email)
                Log.e("login: ", password)
                loginViewModel.userLogin(email, password)



            }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue) ) {
                Text(text = "Login", fontSize = 20.sp, color = Color.White)

            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Account Register click here",
                fontSize = 20.sp,

                color = colorResource(id = R.color.purple_700),
                modifier = Modifier.clickable {
                     val i = Intent(applicationContext,RegisterActivity::class.java)
                    startActivity(i)
                },
                textAlign = TextAlign.Center
            )

        }


    }

    @Composable
    private fun loginViewResult() {
        loginViewModel.userResponseLiveData.observe(this, Observer {
            //  loginBinding.progresBar.isVisible = false
            when (it) {
                is Resource.Success -> {
                    // val i = Intent(this,ProfileActivity::class.java)
                    //startActivity(i)
                    toast(it.data.toString())

                    Log.e("noida: ", it.data.toString())
                }
                is Resource.Error -> {
                    toast(it.message.toString())
                }
                is Resource.Loading -> {
                    //loginBinding.progresBar.isVisible = true

                }
            }
        })
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HdsAppTheme {
            login()
        }
    }
}