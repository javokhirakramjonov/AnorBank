package uz.gita.anorbank.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.anorbank.domain.useCase.AuthUseCase
import uz.gita.anorbank.domain.useCase.impl.AuthUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @[Binds Singleton]
    fun getAuthUseCase(impl: AuthUseCaseImpl) : AuthUseCase

}