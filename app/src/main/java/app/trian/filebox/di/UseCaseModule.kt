package app.trian.filebox.di

import app.trian.filebox.data.domain.GetAllFilesUseCase
import app.trian.filebox.data.repository.StorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllFilesUseCase(
        storageRepository: StorageRepository
    ) = GetAllFilesUseCase(storageRepository)
}