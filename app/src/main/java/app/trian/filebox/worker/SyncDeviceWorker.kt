package app.trian.filebox.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.trian.filebox.domain.SyncDeviceFromCloudUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncDeviceWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val syncDeviceFromCloudUseCase: SyncDeviceFromCloudUseCase
) : CoroutineWorker(appContext,workerParams) {
    override suspend fun doWork(): Result {
        syncDeviceFromCloudUseCase()
        return Result.success()
    }
}