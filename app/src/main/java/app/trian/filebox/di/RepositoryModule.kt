package app.trian.filebox.di

import app.trian.filebox.data.StorageRepository
import app.trian.filebox.data.StorageRepositoryImpl
import app.trian.filebox.data.UserRepository
import app.trian.filebox.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindUserRepository(impl:UserRepositoryImpl):UserRepository

    @Binds
    fun bindStorageRepository(impl:StorageRepositoryImpl):StorageRepository
}