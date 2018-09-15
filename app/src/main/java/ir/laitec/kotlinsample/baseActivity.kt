package ir.laitec.kotlinsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class baseActivity : AppCompatActivity() {


    open fun testOverride() {
    }


    abstract fun testAbstract() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(testAbstract())
    }
}