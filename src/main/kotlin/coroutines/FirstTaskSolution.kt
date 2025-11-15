package com.example.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion

fun <T> Flow<T>.throttleFirst(duration: Long): Flow<T> {
    var job: Job = Job().apply { complete() }

    return onCompletion { job.cancel() }
        .run {
            flow {
                coroutineScope {
                    collect { value ->
                        if (!job.isActive) {
                            emit(value)
                            job = launch {
                                delay(duration)
                            }
                        }
                    }
                }
            }
        }
}

fun <T> Flow<T>.throttleLatest(duration: Long): Flow<T> {
    var job: Job = Job().apply { complete() }

    return onCompletion { job.cancel() }
        .run {
            channelFlow {
                var cachedValue: T? = null
                coroutineScope {
                    collect { value ->
                        if (!job.isActive) {
                            send(value)
                            job = launch {
                                delay(duration)
                                cachedValue?.let { send(it) }
                            }
                        } else {
                            cachedValue = value
                        }
                    }
                    job.join()
                }
            }
        }

}
