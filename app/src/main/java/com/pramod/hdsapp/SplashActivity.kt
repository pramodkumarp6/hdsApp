package com.pramod.hdsapp
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pramod.hdsapp.ui.theme.HdsAppTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HdsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Splash()
                }
            }
        }
    }


    @Composable
    fun Splash() {
       Column(modifier = Modifier.fillMaxSize().padding(20.dp),
       horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center)

       {
          // Text(text="Splash", fontSize = 25.sp,color= Color.Red, fontWeight = FontWeight.Bold)
           Image(painter = painterResource(id = R.drawable.img1),
               contentDescription ="" , modifier = Modifier.size(200.dp).clip(RectangleShape)
           )
       }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HdsAppTheme {
            Splash()
        }
    }

}