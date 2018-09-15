package ir.laitec.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.support.annotation.MainThread

import okhttp3.*
import org.jetbrains.anko.doAsync
import java.io.IOException
import java.util.concurrent.TimeUnit



public class RestClient private constructor() {

    private val client = OkHttpClient()
            .newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    private object Holder {
        val INSTANCE = RestClient()
    }

    companion object {
        val instance: RestClient by lazy { Holder.INSTANCE }
    }

    internal fun post(url: String,
                      responsehandler: Responsehandler) {
        val body = FormBody.Builder()
                .addEncoded("appid","0e8f8fd2-1acb-11e7-8ab0-ac162d7938f0")
                .build()

        val request = Request.Builder()
                .url(url)
                .addHeader("accept-language", "fa")
                .addHeader("Content-type"   , "application/x-www-form-urlencoded")
                .addHeader("cache-control"  , "no-cache")
                .addHeader("Accept"         , "application/json")
                .post(body)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Handler(Looper.getMainLooper()).post {
                    responsehandler.onfailure(0)
                }
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body  = (response?.body() as ResponseBody).source().readByteString().apply(::println)
                Handler(Looper.getMainLooper()).post {
                    responsehandler.onSuccess(body.utf8())
                }
            }
        })
    }
}


