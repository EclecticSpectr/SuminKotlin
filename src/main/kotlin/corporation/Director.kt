package corporation

class Director(
    name: String,
    age: Int = 0
) : Worker(name, age) {

    override fun work() {
        println("Пью кофе...")
    }

    fun takeCoffee(assistant: Assistant) {
        val drinkName: String = assistant.bringCoffee()
        println("Спасибо, ${assistant.name}! $drinkName вкусный")
    }

    fun getConsultantToWork(consultant: Consultant) {
        val count = consultant.serveCustomers()
        println("Консультант ${consultant.name} обслужил $count клиентов")
    }
}