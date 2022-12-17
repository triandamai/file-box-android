package app.trian.filebox.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.trian.filebox.domain.SyncImagesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ImagesSyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val syncImagesUseCase: SyncImagesUseCase
) : CoroutineWorker(
    appContext,
    workerParameters
) {
    override suspend fun doWork(): Result = try {
        syncImagesUseCase()
        Result.success()
    } catch (e: Exception) {
        Result.failure()
    }
}