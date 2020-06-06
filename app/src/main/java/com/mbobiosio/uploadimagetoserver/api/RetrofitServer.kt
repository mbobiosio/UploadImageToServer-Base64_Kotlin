package com.mbobiosio.uploadimagetoserver.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * UploadImageToServer-Base64
 * Created by Mbuodile Obiosio on Jun 06, 2020
 * https://twitter.com/cazewonder
 * Nigeria.
 */
object RetrofitServer {
    private const val BASE_URL = "http://your-url.com/uploads/"
    private var mRetrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .callTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return mRetrofit
    }

}