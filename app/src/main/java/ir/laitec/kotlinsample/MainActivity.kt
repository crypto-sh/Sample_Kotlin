package ir.laitec.kotlinsample



import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import ir.laitec.adapter.AdapterList
import ir.laitec.model.webResult
import ir.laitec.utils.Responsehandler
import ir.laitec.utils.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor


class MainActivity : baseActivity() {

    override fun testAbstract(): Int {
        return R.layout.activity_main
    }

    override fun testOverride() {
        super.testOverride()

    }

    var TAG : String = "MainActivity"

    var requestCode : Int = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var items = ArrayList<String>()
        for (index in 1..300) {
            items.add("index : $index")
        }
        gridviewTest.adapter = AdapterList(items,this)
        gridviewTest.setOnItemClickListener { _, _, position, _ ->
            startActivity(intentFor<SecondActivity>("selected" to position))
        }
    }

    fun forSyntaxtest(){

        var x : IntArray = intArrayOf(1,2,3,4)

        for (index in 1..10) {
            Log.d(TAG, "index : $index")
        }

        for (index in 4 downTo 1){
            print("index : $index \n")
        }

        for (index in 1 until 10){
            print("index : $index \n")
        }

        for (index in 1..10 step 2){
            print("index : $index \n")
        }

        for (index in x) {
            print("index $index \n")
            when (index) {
                1 -> {
                    Log.d(TAG,"switch case change to when")
                }
                2 -> {
                    Log.d(TAG,"thats cool")
                }
                else -> {
                    print("message printing change from log to print")
                }
            }
        }
    }

    fun checkLogin(x : Int) : Boolean{
        return when(x){
            1       -> false
            else    -> true
        }
    }

    private fun preference() {
        var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.edit()
                .putString("string","value String test")
                .putBoolean("bool",true)
                .putInt("intvalue",12314123)
                .apply()

        Log.d(TAG,sharedPreferences.getString("string","test"))
    }


    fun startNewActivity(){
        startActivity(intentFor<SecondActivity>("id" to 5,"test" to "value"))
        //        var intenttest = Intent(this,SecondActivity::class.java)
//        intenttest.putExtra("test" ,"value")
//        intenttest.putExtra("test1","value1")
//        intenttest.putExtra("test2","value2")
//        startActivity(intenttest)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG,"request Code : " + requestCode + " result Code : " + resultCode)
    }
}
