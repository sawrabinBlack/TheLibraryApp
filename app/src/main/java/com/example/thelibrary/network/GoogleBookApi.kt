package com.example.thelibrary.network

import com.example.thelibrary.network.responses.GetBookListFromGoogleResponse
import com.example.thelibrary.utils.GET_GOOGLE_BOOK_VOLUME
import com.example.thelibrary.utils.PARAMS_Q
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBookApi {
    @GET(GET_GOOGLE_BOOK_VOLUME)
    fun searchGoogleBooks(
        @Query(PARAMS_Q) q: String
    ):Observable<GetBookListFromGoogleResponse>


}