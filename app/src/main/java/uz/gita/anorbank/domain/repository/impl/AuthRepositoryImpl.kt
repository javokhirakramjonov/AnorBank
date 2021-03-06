package uz.gita.anorbank.domain.repository.impl

import uz.gita.anorbank.data.local.LocalData
import uz.gita.anorbank.data.model.request.VerificationRequest
import uz.gita.anorbank.data.model.simple.RegisterModel
import uz.gita.anorbank.data.remote.api.AuthApi
import uz.gita.anorbank.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val local: LocalData
) : AuthRepository {
    override suspend fun registerUser(user: RegisterModel): Result<Unit> {
        val response = api.register(user.toRequest())
        return when (response.code()) {
            200 -> {
                local.accessToken = response.body()!!.token
                Result.success(Unit)
            }
            403 -> {
                Result.failure(Exception(response.message()))
            }
            else -> {
                Result.failure(Exception("Something went wrong"))
            }
        }
    }

    override suspend fun verifyUser(code: String): Result<Unit> {
        val response = api.verify("Bearer ${local.accessToken}", VerificationRequest(code))
        return when (response.code()) {
            200 -> {
                local.accessToken = response.body()!!.accessToken
                local.refreshToken = response.body()!!.refreshToken
                Result.success(Unit)
            }
            else -> {
                Result.failure(Exception("Something went wrong"))
            }
        }
    }
}