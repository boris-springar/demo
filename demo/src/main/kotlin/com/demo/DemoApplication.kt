package com.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
    println("Server starting. Waiting for input until end...")
    println("Press enter to exit...")
    readln()
    println("Exiting")
}
