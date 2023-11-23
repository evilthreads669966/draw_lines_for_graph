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

import kotlin.properties.Delegates

fun main() {
    println("Do you want to draw a lineor check whether a line is a function?")
    loop@while(true){
        println("Enter draw or function")
        val input = readlnOrNull()?.trim()
        if(input.isNullOrBlank()) continue
        if(input.lowercase() == "draw"){
            var v = false
            println("Do you want a sideways V line? No Yes")
            var input = readlnOrNull()?.trim()
            if(input.isNullOrBlank()) continue@loop
            if(input.lowercase() == "yes")
                v = true
            println("Enter the slope in rise/run format")
            input = readlnOrNull()
            if(input.isNullOrBlank()) continue
            var parts = input.split("/")
            val rise = parts[0].toIntOrNull()
            if(rise == null) continue
            val run = parts[1].toIntOrNull()
            if(run == null) continue
            val slope = Slope(rise,run)
            println("Enter the Y-intercept in x,y format")
            input = readlnOrNull()
            if(input.isNullOrBlank()) continue
            parts = input.split(",")
            val x = parts[0].toIntOrNull()
            if(x == null) continue
            val y = parts[1].toIntOrNull()
            if(y == null) continue
            val coordinate = Coordinate(x,y)
            var line: List<Coordinate> by Delegates.notNull()
            if(v)
                line = Graph.drawVLine(slope,coordinate)
            else
                line = Graph.drawLine(slope, coordinate)
            line.forEach {
                print("$it ")
            }
            println()
        }
        if(input.lowercase() == "function"){
            println("Enter a line in (x,y) (x,y) (x,y) format")
            val input = readlnOrNull()?.trim()
            if(input.isNullOrBlank()) continue
            val parts = input.split(" ")
            val line = mutableListOf<Coordinate>()
            for(part in parts) {
                val coordinateParts = part.split(",")
                val x = coordinateParts[0].replace("(", "").toIntOrNull()
                if(x == null){
                    println("Bad coordinates")
                    continue@loop
                }
                val y = coordinateParts[1].replace(")", "").toIntOrNull()
                if(y == null){
                    println("Bad coordinates")
                    continue
                }
                line.add(Coordinate(x,y))
            }
            val isFunction = Graph.isFunction(line)
            if(isFunction)
                println("Line is function")
            else
                println("Line is not a function")
        }
    }
}



