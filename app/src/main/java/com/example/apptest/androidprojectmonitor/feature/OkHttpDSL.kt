package com.example.apptest.androidprojectmonitor.feature

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException

class OkHttpDSL {

    companion object {
        val gson: Gson = Gson();
    }

    private val okHttpClient = OkHttpClient()
    val requestDescription = RequestDescription()
    lateinit var request: Request
    operator fun invoke(block: OkHttpDSL.() -> Unit) {
        block();
    }

    private fun parsed() {
        val mediaType: MediaType? = MediaType.parse(requestDescription.mimeType.values())!!
        val body: RequestBody = RequestBody.create(mediaType, requestDescription.body)
        val builder: Request.Builder = Request.Builder()


        when (requestDescription.method) {
            Method.GET ->
                builder.get().url(requestDescription.uri + "?${requestDescription.body}")

            Method.POST ->
                builder.post(body).url(requestDescription.uri)
        }

        builder.addHeader("content-type", requestDescription.mimeType.values())
        for (i in requestDescription.headers)
            builder.addHeader(i.key, i.value)
        request = builder.build()

    }

    fun callString(function: suspend (String) -> Unit, exception: (Throwable) -> Unit) {
        parsed()
        request.let {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = okHttpClient.newCall(it).execute()
                    response.body()?.let { body ->
                        withContext(Dispatchers.Main){
                            function(body.string())
                        }
                    }

                } catch (e: IOException) {
                    exception(e)
                    e.printStackTrace()
                }

            }
        }
    }

    fun <T> callType(type: Class<T>, function: suspend (T) -> Unit, exception: (Throwable) -> Unit) {
        parsed()
        request.let {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = okHttpClient.newCall(it).execute()
                    response.body()?.let { body ->
                        val string = body.string()
                        Log.e("CallType:${requestDescription.uri}",string)
                        val obj = gson.fromJson<T>(string, type)
                        withContext(Dispatchers.Main) {
                            function(obj)
                        }
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main){
                        exception(e)
                    }
                    e.printStackTrace()
                }

            }
        }
    }

    fun callBytes(function: suspend (ByteArray) -> Unit, exception: (Throwable) -> Unit) {
        parsed()
        request.let {
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = okHttpClient.newCall(it).execute()
                    response.body()?.let { body ->
                        withContext(Dispatchers.Main){
                            function(body.bytes())
                        }
                    }
                } catch (e: IOException) {
                    exception(e)
                    e.printStackTrace()
                }

            }

        }
    }

}

class RequestDescription {
    lateinit var uri: String
    lateinit var method: Method
    var mimeType: MimeType = MimeType.APPLICATION_JSON
    var headers = ArrayList<Header>()
    var body: String = ""
    operator fun invoke(block: RequestDescription.() -> Unit) {
        block()
    }
}

enum class MimeType(private val value: String) {
    APPLICATION_X_FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_JSON("application/json"),
    APPLICATION_JAVASCRIPT("application/javascript"),
    APPLICATION_XML("application/xml"),
    TEXT_XML("text/xml"),
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain");

    fun values(): String {
        return value
    }
}

data class Header(var key: String, var value: String)

enum class Method(private val methodValue: String) {
    GET("GET"),
    POST("POST");

    fun getValue(): String {
        return methodValue
    }

}
