package corporation

class ApplianceCard(
    name: String,
    brand: String,
    price: Int,
    val wattage: Int
) : ProductCard(name, brand, price) {
    override fun printInfo() {
        super.printInfo()
        println("Power: $wattage")
    }
}