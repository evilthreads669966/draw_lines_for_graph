/*
Copyright 2023 Chris Basinger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import kotlin.math.abs
import kotlin.math.sign

object Graph{

    @JvmStatic
    fun isFunction(coordinates: List<Coordinate>): Boolean{
        val yCoordinates = coordinates.map { it.y }.sorted()
        val xCoordinates = coordinates.map { it.x }.sorted()
        xCoordinates.forEach { x ->
            var timesIntersected = 0
            yCoordinates.forEach{y ->
                coordinates.forEach { coordinate ->
                    if(Coordinate(x, y) == coordinate){
                        timesIntersected++
                    }
                }
            }
            if(timesIntersected > 1)
                return false
        }
        return true
    }

    @JvmStatic
    fun drawLine(slope: Slope, yIntercept: Coordinate): List<Coordinate>{
        var currentCoordinate = yIntercept
        val coordinates = mutableListOf<Coordinate>()
        repeat(5){
            val rise = if(slope.rise.sign == -1) abs(slope.rise) else -abs(slope.rise)
            val run = if(slope.rise.sign == -1) abs(slope.run) else -abs(slope.run)
            val coordinate = Coordinate(currentCoordinate.x + run, currentCoordinate.y + rise)
            coordinates.add(coordinate)
            currentCoordinate = coordinate
        }
        coordinates.reverse()
        coordinates.add(yIntercept)
        currentCoordinate = yIntercept
        repeat(5){
            val coordinate = Coordinate(currentCoordinate.x + slope.run, currentCoordinate.y + slope.rise)
            coordinates.add(coordinate)
            currentCoordinate = coordinate
        }

        return coordinates
    }

    @JvmStatic
    fun drawVLine(slope: Slope, yIntercept: Coordinate): List<Coordinate>{
        var currentCoordinate = yIntercept
        var coordinates = mutableListOf<Coordinate>()
        repeat(5){
            val rise = if(slope.rise.sign == -1) abs(slope.rise) else -abs(slope.rise)
            val coordinate = Coordinate(currentCoordinate.x + slope.run, currentCoordinate.y + rise)
            coordinates.add(coordinate)
            currentCoordinate = coordinate
        }

        coordinates.reverse()
        coordinates.add(yIntercept)
        currentCoordinate = yIntercept
        repeat(5){
            val coordinate = Coordinate(currentCoordinate.x + slope.run, currentCoordinate.y + slope.rise)
            coordinates.add(coordinate)
            currentCoordinate = coordinate
        }

        return coordinates
    }
}