class Car(
    val make: String,
    val model: String,
    val year: Int,
    val vin: String,
    val color: String,
)

fun serialize(car: Car): String {
    return "${car.make}%${car.model}%${car.year}%${car.vin}%${car.color}"
}

fun deserialize(carAsString: String): Car {
    val splitString = carAsString.trim().split("%")
    return Car(
        splitString[0],
        splitString[1],
        splitString[2].toInt(),
        splitString[3],
        splitString[4]
    )
}

fun main() {
    val testCar = Car("Burga", "BMW", 2025, "ITE69G", "Grey")
    val stringCar = serialize(testCar)
    val returnedCar = deserialize(stringCar)
    println("${returnedCar.make}${returnedCar.model}${returnedCar.year}${returnedCar.vin}${returnedCar.color}")
}