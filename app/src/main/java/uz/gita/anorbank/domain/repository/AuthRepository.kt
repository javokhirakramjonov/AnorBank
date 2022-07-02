package uz.gita.anorbank.domain.repository

import uz.gita.anorbank.data.model.simple.RegisterModel

interface AuthRepository {
    suspend fun registerUser(user: RegisterModel) : Result<Unit>
}