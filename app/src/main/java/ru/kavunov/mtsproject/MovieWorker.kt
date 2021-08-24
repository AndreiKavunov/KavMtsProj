package ru.kavunov.mtsproject

import android.content.Context
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.mvvm.changeBd
import java.util.concurrent.TimeUnit

class MovieWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        CoroutineScope(Dispatchers.Main).launch() {
            changeBd(context = applicationContext)
            Log.d("tag11", "Сработал Work manager")
        }
        return Result.success()
    }
}

fun runWorker(context: Context){

    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(true)
        .build()

    val workRequest: PeriodicWorkRequest = PeriodicWorkRequestBuilder<MovieWorker>(
        1, TimeUnit.DAYS,
        1, TimeUnit.HOURS)
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context)
        .enqueue(workRequest)
}