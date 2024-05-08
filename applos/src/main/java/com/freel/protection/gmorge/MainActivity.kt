package com.freel.protection.gmorge

import android.app.NativeActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.freel.protection.gmorge.databinding.ActivityMainBinding
import org.libsdl.app.SDLActivity

class MainActivity : SDLActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        // Example of a call to a native method
        //binding.sampleText.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'gmorge' native library,
     * which is packaged with this application.
     */
    //external fun stringFromJNI(): String


      override fun onResume() {
        super.onResume()
        hideSystemUI()
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }


       private fun hideSystemUI() {
         WindowCompat.setDecorFitsSystemWindows(window, false)
         WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }


    companion object {
        // Used to load the 'gmorge' library on application startup.
//        init {
//            System.loadLibrary("gmorge")
//        }
    }
}