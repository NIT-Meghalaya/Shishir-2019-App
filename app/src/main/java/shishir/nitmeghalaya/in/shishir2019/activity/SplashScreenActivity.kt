package shishir.nitmeghalaya.`in`.shishir2019.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import shishir.nitmeghalaya.`in`.shishir2019.R

class SplashScreenActivity : AppCompatActivity() {

    val SPLASH_TIME_OUT = 6000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)

            //Close this activity
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}
