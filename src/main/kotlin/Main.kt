package com.example

import com.example.coroutines.throttleFirst
import com.example.coroutines.throttleLatest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach


suspend fun main() {

    val scope = CoroutineScope(Job())
    println("ThrottleFirst")
    scope.launch {
        List(20) {
            it + 1
        }.asFlow()
            .onEach {
                delay(200)
            }
            .throttleFirst(1000)
            .collect {
                println(it)
            }

    }.join()
    println("ThrottleLatest")
    scope.launch {
        List(20) {
            it + 1
        }.asFlow()
            .onEach {
                delay(200)
            }
            .throttleLatest(1000)
            .collect {
                println(it)
            }

    }.join()


}