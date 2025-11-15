package com.example.kotlin

object ThirdTaskSolution {
    fun List<Any?>.findInt(): Int? {
        return firstOrNull { it is Int } as Int?
    }
}