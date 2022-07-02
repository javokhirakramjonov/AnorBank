package uz.gita.anorbank.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.anorbank.data.model.simple.RegisterModel

interface AuthUseCase {
    fun registerUser(user: RegisterModel) : Flow<Result<Unit>>
}