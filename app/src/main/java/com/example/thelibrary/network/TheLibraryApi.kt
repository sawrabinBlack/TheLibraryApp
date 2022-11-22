package com.example.thelibrary.network

import com.example.thelibrary.network.responses.GetBookListResponse
import com.example.thelibrary.network.responses.GetMoreBookListResponse
import com.example.thelibrary.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface TheLibraryApi {

    @GET(GET_OVERVIEW_LIST)
    fun getBookList(
        @Query(PARAMS_API_KEY) apiKey: String = LIBRARY_API_KEY,
    ): Observable<GetBookListResponse>

    @GET(GET_LISTS)
    fun getBookListWithGenre(
        @Query(PARAMS_API_KEY) apiKey: String = LIBRARY_API_KEY,
        @Query(PARAMS_list) list: String
    ): Observable<GetMoreBookListResponse>

}