package corporation

class Assistant(
    id: Int,
    name: String,
    age: Int = 0
) : Worker(id, name, age, Position.ASSISTANT) {

    fun bringCoffee(drinkName: String = "Cappuccino", count: Int = 1): String {
        repeat(count) {
            println("Get up")
            println("Go to the coffee machine")
            println("Press the \"$drinkName\" button")
            println("Wait for the $drinkName to be prepared")
            println("Take coffee")
            println("Bring coffee to director")
            println("Put coffee on the table")
            println("Return to the workplace")
        }
        return "Espresso"
    }

    override fun work() {
        println("Готовлю кофе...")
    }
}