package com.example

import java.util.Collections.swap

object FourthTaskSolution {
    fun shakeSort(list: List<Int?>): List<Int?> {
        val listForSwap = list.toMutableList()
        var leftBound = 0
        var rightBound = list.size - 1
        var isSwapped = true

        while (isSwapped) {
            isSwapped = false
            for (i in leftBound until rightBound) {
                val left = listForSwap[i]
                val right = listForSwap[i + 1]
                val shouldSwap = when {
                    left != null && right != null && left > right -> true
                    left != null && right == null -> false
                    left == null && right != null -> true
                    else -> false
                }
                if (shouldSwap) {
                    swap(listForSwap, i, i + 1)
                    isSwapped = true
                }
            }
            rightBound--

            for (i in rightBound downTo leftBound + 1) {
                val right = listForSwap[i]
                val left = listForSwap[i - 1]
                val shouldSwap = when {
                    right == null && left != null -> false
                    right != null && left == null -> true
                    right != null && left != null && right < left -> true
                    else -> false
                }
                if (shouldSwap) {
                    swap(listForSwap, i - 1, i)
                    isSwapped = true
                }
            }
            leftBound++

            if (!isSwapped) {
                break
            }
        }
        return listForSwap.toList()
    }
}