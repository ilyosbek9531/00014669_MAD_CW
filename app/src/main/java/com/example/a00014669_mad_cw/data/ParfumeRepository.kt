package com.example.a00014669_mad_cw.data

import android.util.Log
import com.example.a00014669_mad_cw.data.dataClasses.Parfume
import com.example.a00014669_mad_cw.data.network.MyItemResponse
import com.example.a00014669_mad_cw.data.network.MyListResponse
import com.example.a00014669_mad_cw.data.network.MyResponse
import com.example.a00014669_mad_cw.data.network.RetrofitInstance
import com.example.a00014669_mad_cw.data.network.parfume.ParfumeRequest
import com.example.a00014669_mad_cw.data.network.parfume.ParfumeResponse

class ParfumeRepository {
    suspend fun getParfumeList(): List<Parfume> {
        val parfumes = mutableListOf<Parfume>()

        try {
            val response: MyListResponse<ParfumeResponse> =
                RetrofitInstance.parfumeService.getAllParfumes("00014669")
            val parfumesFromResponse = response.data

            if (parfumesFromResponse != null) {

                for (parfumeFromResponse in parfumesFromResponse) {
                    if (parfumeFromResponse.description != null) {
                        parfumes.add(
                            Parfume(
                                id = parfumeFromResponse.id.toString(),
                                title = parfumeFromResponse.title,
                                description = parfumeFromResponse.description,
                                price = parfumeFromResponse.price,
                                image = parfumeFromResponse.image,
                                origin = parfumeFromResponse.origin,
                                volumes = parfumeFromResponse.volumes,
                                isItTrue = parfumeFromResponse.isItTrue
                            )
                        )
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return parfumes
    }

    suspend fun insertNewParfume(parfume: Parfume): MyResponse? {
        val response: MyResponse

        try {
            val parfumeRequest =
                ParfumeRequest(
                    title = parfume.title,
                    description = parfume.description,
                    price = parfume.price,
                    releaseDate = parfume.releaseDate,
                    image = parfume.image,
                    origin = parfume.origin,
                    volumes = parfume.volumes,
                    isItTrue = parfume.isItTrue
                )

            response = RetrofitInstance.parfumeService.insertNewParfume(
                "00014669",
                parfumeRequest
            )

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return response
    }

    suspend fun getParfumeById(parfumeId: String): Parfume? {
        try {
            val response: MyItemResponse<ParfumeResponse> =
                RetrofitInstance.parfumeService.getOneParfumeById(parfumeId, "00014669")
            val parfumeFromResponse = response.data

            if (parfumeFromResponse != null) {
                if (parfumeFromResponse.description != null
                ) {
                    return Parfume(
                        id = parfumeId,
                        title = parfumeFromResponse.title,
                        description = parfumeFromResponse.description,
                        price = parfumeFromResponse.price,
                        image = parfumeFromResponse.image,
                        origin = parfumeFromResponse.origin,
                        volumes = parfumeFromResponse.volumes,
                        releaseDate = formatReleaseDate(parfumeFromResponse.releaseDate),
                        isItTrue = parfumeFromResponse.isItTrue
                    )
                }
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    suspend fun deleteParfumeById(parfumeId: String): MyResponse? {
        val response: MyResponse
        try {
            response = RetrofitInstance.parfumeService.deleteOneParfumeById(parfumeId, "00014669")

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return response
    }


    private fun formatReleaseDate(releaseDate: String?): String {
        if (releaseDate.isNullOrEmpty()) return ""
        return releaseDate.dropLast(9)
    }
}

