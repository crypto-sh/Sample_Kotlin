package ir.laitec.kotlinsample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import ir.laitec.adapter.listAdapter
import ir.laitec.kotlinsample.R.id.listview
import ir.laitec.model.webResult
import ir.laitec.utils.Responsehandler
import ir.laitec.utils.RestClient

import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.content_second.*

class SecondActivity : AppCompatActivity() {


    var onlineresult: Boolean = true

    val TAG = SecondActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.hasExtra("selected")) {
            var data = intent.getIntExtra("selected", 0).toString()
            Log.d(TAG, "data received : " + data)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", {
                        Log.d(TAG, "action fab icon")
                    }).show()
        }

        if (onlineresult) {
            RestClient.instance.post("http://172.20.0.57:8080/user/test", object : Responsehandler() {
                override fun onSuccess(result: String) {
                    setListViewData(result)
                }

                override fun onfailure(error: Int) {
                    showMessage("failure load data")
                }
            })
        }else{
            setListViewData(getJsondata())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                Log.d(TAG,"home button Click")
                onBackPressed()

            }
            else -> {
                Log.d(TAG,"did not recognized")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun setListViewData(jsonResult: String) {
        var result = Gson().fromJson(jsonResult, webResult::class.java).also(::println)
        var adapter = listAdapter(result.data, this)
        listview.adapter = adapter
    }

    private fun showMessage(message: String) {
        //Toast.makeText(this, message, Lon).show()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    fun getJsondata(): String {
        return "{\n" +
                "    \"status\": 1,\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"test message data read \",\n" +
                "    \"data\": [\n" +
                "                {\n" +
                "                  \"Id\": \"4000\",\n" +
                "                  \"title\": \"وسیله حمل و نقل\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"Id\": \"4001\",\n" +
                "                  \"title\": \"CATEGORY 1\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"Id\": \"4002\",\n" +
                "                  \"title\": \"CATEGORY 2\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"Id\": \"4003\",\n" +
                "                  \"title\": \"CATEGORY 3\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"Id\": \"4004\",\n" +
                "                  \"title\": \"CATEGORY 4\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"Id\": \"4005\",\n" +
                "                  \"title\": \"CATEGORY 5\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"Id\": \"4006\",\n" +
                "                  \"title\": \"CATEGORY 6\"\n" +
                "                },\n" +
                "                {\n" +
                "                  \"Id\": \"4007\",\n" +
                "                  \"title\": \"CATEGORY 7\"\n" +
                "                }\n" +
                "    ]\n" +
                "}"

    }
}
