package app.trian.filebox.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.trian.filebox.domain.SyncDocumentsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DocumentsSyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val syncDocumentsUseCase: SyncDocumentsUseCase
) : CoroutineWorker(
    appContext,
    workerParameters
) {
    override suspend fun doWork(): Result = try {
        syncDocumentsUseCase()
        Result.success()
    } catch (e: Exception) {
        Result.failure()
    }
}