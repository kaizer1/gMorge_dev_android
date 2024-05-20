package com.freel.protection.gmorge

import android.app.NativeActivity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.freel.protection.gmorge.databinding.ActivityMainBinding
import java.util.concurrent.LinkedBlockingQueue

import org.libsdl.app.SDLActivity

class MainActivity : SDLActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        if(allPermissionsGranted()){

        }else{
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, 100)
        }

//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO))
//     }
        // check permissions
        // RECORD_AUDIO
        // Example of a call to a native method
        //binding.sampleText.text = stringFromJNI()
    }


        private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED

    }
    /**
     * A native method that is implemented by the 'gmorge' native library,
     * which is packaged with this application.
     */
    //external fun stringFromJNI(): String



     fun showSoftInput() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this.window.decorView, 0)
    }

    fun hideSoftInput() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
    }

      override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    //pollUnicodeChar())
    private var unicodeCharacterQueue: LinkedBlockingQueue<Int> = LinkedBlockingQueue()


     override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            unicodeCharacterQueue.offer(event.getUnicodeChar(event.metaState))
        }
        return super.dispatchKeyEvent(event)
    }

    fun pollUnicodeChar(): Int {
        return unicodeCharacterQueue.poll() ?: 0
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
//       //  Used to load the 'gmorge' library on application startup.
//        init {
//            System.loadLibrary("gmorge")
//        }


              private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                  android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.RECORD_AUDIO)


    }

}