package com.example.a00014669_mad_cw.data.network

import com.example.a00014669_mad_cw.data.network.parfume.ParfumeRequest
import com.example.a00014669_mad_cw.data.network.parfume.ParfumeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ParfumeService {
    @GET("records/all")
    suspend fun getAllMovies(
        @Query("student_id") student_id: String
    ): MyListResponse<ParfumeResponse>

    @POST("records")
    suspend fun insertNewMovie(
        @Query("student_id") student_id: String,
        @Body movieRequest: ParfumeRequest
    ): MyResponse

    @GET("records/{record_id}")
    suspend fun getOneMovieById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<ParfumeResponse>
}