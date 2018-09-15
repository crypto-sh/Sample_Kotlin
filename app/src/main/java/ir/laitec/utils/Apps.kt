package ir.laitec.utils

import android.app.Application


class Apps : Application() {

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String


    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }


    override fun onCreate() {
        super.onCreate()



    }
}