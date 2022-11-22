package com.example.thelibrary.data.model

import android.content.Context
import com.example.thelibrary.network.GoogleBookApi
import com.example.thelibrary.network.TheLibraryApi
import com.example.thelibrary.persistance.LibraryDatabase
import com.example.thelibrary.utils.BASE_URL
import com.example.thelibrary.utils.BASE_URL_GOOGLE_BOOK
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

abstract class BaseModel {

    protected var mLibraryApi: TheLibraryApi
    protected var mGoogleBookApi: GoogleBookApi
    protected var mLibraryDatabase: LibraryDatabase? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).build()

        val mRetrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val mGoogleBookRetrofit = Retrofit.Builder().baseUrl(BASE_URL_GOOGLE_BOOK)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        mLibraryApi = mRetrofit.create(TheLibraryApi::class.java)
        mGoogleBookApi = mGoogleBookRetrofit.create(GoogleBookApi::class.java)
    }


    fun initDatabase(context: Context) {
        mLibraryDatabase = LibraryDatabase.getDBInstance(context)
    }
}