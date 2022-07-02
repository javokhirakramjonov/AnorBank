package uz.gita.anorbank.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.anorbank.domain.repository.AuthRepository
import uz.gita.anorbank.domain.repository.impl.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun getAuthRepository(impl: AuthRepositoryImpl) : AuthRepository

}