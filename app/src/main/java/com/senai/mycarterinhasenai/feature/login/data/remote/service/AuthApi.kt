package com.senai.mycarterinhasenai.feature.login.data.remote.service

import com.senai.mycarterinhasenai.feature.login.data.remote.dto.LoginRequestDto
import com.senai.mycarterinhasenai.feature.login.data.remote.dto.LoginResponseDto
import retrofit2.http.POST
import retrofit2.http.Body


interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto
}