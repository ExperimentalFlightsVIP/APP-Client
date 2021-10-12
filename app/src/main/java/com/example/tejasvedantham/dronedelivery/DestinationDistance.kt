package com.example.tejasvedantham.dronedelivery
import kotlin.math.*
internal object DestinationDistance {
    //distance between 2 points helper method
    private fun distance(cord1: DoubleArray, cord2: DoubleArray): Double {
        //init lat/lon var
        val lat1 = Math.toRadians(cord1[0])
        val lon1 = Math.toRadians(cord1[1])
        val lat2 = Math.toRadians(cord2[0])
        val lon2 = Math.toRadians(cord2[1])

        //Haversine formula
        val distLat = lat2 - lat1
        val distLon = lon2 - lon1
        val a = (sin(distLat / 2).pow(2.0) + cos(lat1) * cos(lat2) * (sin(distLon / 2).pow(2.0)))
        val c = 2 * asin(sqrt(a))
        //radius of earth in miles
        val r = 3958.8
        //calc
        return c * r
    }

    private fun totalDistance(AllCord: Array<DoubleArray>): Double {
        //init
        var totalDistance = 0.0
        //Iterates, calculates distance between 2 points, then adds to totalDistance
        for (i in 0 until AllCord.size - 1) {
            val distancePoints = distance(AllCord[i], AllCord[i + 1])
            totalDistance += distancePoints
        }
        return totalDistance
    }

    fun estimatedTimeWithDistance(velocity: Double, AllCord: Array<DoubleArray>): String {
        val distance = totalDistance(AllCord)
        val hours = floor(distance / velocity).toInt()
        val minutes = floor(distance % velocity * 60).toInt()
        return "$hours:$minutes Distance:$distance"
    }
}