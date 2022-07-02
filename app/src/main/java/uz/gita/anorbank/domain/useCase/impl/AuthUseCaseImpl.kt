package uz.gita.anorbank.domain.useCase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.anorbank.data.model.simple.RegisterModel
import uz.gita.anorbank.domain.repository.AuthRepository
import uz.gita.anorbank.domain.useCase.AuthUseCase
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : AuthUseCase {
    override fun registerUser(user: RegisterModel): Flow<Result<Unit>> = flow<Result<Unit>> {
        emit(authRepository.registerUser(user))
    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)
}