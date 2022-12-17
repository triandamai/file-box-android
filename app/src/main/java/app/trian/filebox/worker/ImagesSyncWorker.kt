package app.trian.filebox.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ImagesSyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParameters: WorkerParameters,
) :CoroutineWorker(
    appContext,
    workerParameters
) {
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}