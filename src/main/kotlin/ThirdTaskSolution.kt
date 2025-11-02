package com.example

object ThirdTaskSolution {
    fun List<Any?>.findInt(): Int? {
        return firstOrNull { it is Int } as Int?
    }
}