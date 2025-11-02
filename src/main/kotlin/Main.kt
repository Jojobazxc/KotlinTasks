package com.example

import com.example.ThirdTaskSolution.findInt


fun main() {
    val list = listOf<Any?>("string", 42, null, 3.14, 7)

    println(list.findInt())

}