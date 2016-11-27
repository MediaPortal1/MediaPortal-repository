package ua.com.mediaportal.modules

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RestObj private constructor() {

    private object Holder {
        val INSTANCE = RestObj().getRestClient().create(GithubRequest::class.java)
    }

    companion object {
        val instance =  Holder.INSTANCE
    }

    val API_URL = "http://api.github.com/"

    fun getRestClient(): Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(getGsonFactory())
            .addCallAdapterFactory(getCallRxFactory())
            .client(getOkHttp())
            .build()

    private fun getGsonFactory() = GsonConverterFactory.create(getGson())

    private fun getGson() = GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

    private fun getOkHttp() = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .build()

    private fun getCallRxFactory() = RxJava2CallAdapterFactory.create()


}