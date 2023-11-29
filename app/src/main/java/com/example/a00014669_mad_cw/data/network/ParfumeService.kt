package com.example.a00014669_mad_cw.data.network

import com.example.a00014669_mad_cw.data.network.parfume.ParfumeRequest
import com.example.a00014669_mad_cw.data.network.parfume.ParfumeResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ParfumeService {
    @GET("records/all")
    suspend fun getAllParfumes(
        @Query("student_id") student_id: String
    ): MyListResponse<ParfumeResponse>

    @POST("records")
    suspend fun insertNewParfume(
        @Query("student_id") student_id: String,
        @Body parfumeRequest: ParfumeRequest
    ): MyResponse

    @GET("records/{record_id}")
    suspend fun getOneParfumeById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<ParfumeResponse>

    @DELETE("records/{record_id}")
    suspend fun deleteOneParfumeById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<ParfumeResponse>
}