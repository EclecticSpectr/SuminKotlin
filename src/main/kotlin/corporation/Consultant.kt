package corporation

import kotlin.random.Random

class Consultant(
    id: Int,
    name: String,
    age: Int = 0
) : Worker(id, name, age, Position.CONSULTANT) {

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