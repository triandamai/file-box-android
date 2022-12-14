package app.trian.filebox.di

import app.trian.filebox.data.repository.StorageRepository
import app.trian.filebox.data.repository.StorageRepositoryImpl
import app.trian.filebox.data.repository.UserRepository
import app.trian.filebox.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindStorageRepository(impl: StorageRepositoryImpl): StorageRepository
}