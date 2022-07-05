package uz.gita.anorbank.data.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import uz.gita.anorbank.data.model.request.RegisterRequest
import uz.gita.anorbank.data.model.request.VerificationRequest
import uz.gita.anorbank.data.model.response.RegisterResponse
import uz.gita.anorbank.data.model.response.VerificationResponse

interface AuthApi {
    @POST("auth/sign-up")
    suspend fun register(@Body userData: RegisterRequest): Response<RegisterResponse>

    @POST("auth/sign-up/verify")
    suspend fun verify(@Header("Authorization") token: String, @Body code: VerificationRequest):
            Response<VerificationResponse>
}