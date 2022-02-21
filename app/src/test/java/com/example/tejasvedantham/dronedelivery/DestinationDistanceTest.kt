package com.example.tejasvedantham.dronedelivery

import com.example.tejasvedantham.dronedelivery.DestinationDistance.estimatedTimeWithDistance
import org.junit.Assert.*
import org.junit.Test

class DestinationDistanceTest {
    @Test
    fun testEstimatedTimeWithDistance() {
        val cord1 : DoubleArray = doubleArrayOf(123.45, 67.89)
        val cord2 : DoubleArray = doubleArrayOf(98.76, 543.21)
        assertEquals("120:1087 Distance:2620.9319644158313", estimatedTimeWithDistance(21.69, arrayOf(cord1, cord2)))
    }
}