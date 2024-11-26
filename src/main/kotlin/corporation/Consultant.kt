package corporation

import kotlin.random.Random

class Consultant(
    name: String,
    age: Int = 0
) : Worker(name, age) {

    fun serveCustomers(): Int {
        val count = Random.nextInt(0, 100)
        repeat(count) {
            println("The customer is served...")
        }
        println()
        return count
    }

    fun sayHello() {
        println("Hello! My name is $name.")
        if (age > 0) {
            println("I`m $age years old.")
        }
    }

    override fun work() {
        println("Обслуживаю клиентов...")
    }
}