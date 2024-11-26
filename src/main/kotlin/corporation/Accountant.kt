package corporation

import java.io.File

class Accountant(
    name: String,
    age: Int = 0
) : Worker(name, age) {

    val items = mutableListOf<ProductCard>()
    val file = File("product_cards.txt")

    override fun work() {

        while (true) {
            val operationCodes = OperationCode.entries
            print("Enter the operation code. ")
            for ((index, code) in operationCodes.withIndex()) {
                print("$index - ${code.title}")
                if (index < operationCodes.size - 1) {
                    print(", ")
                } else {
                    print(": ")
                }
            }
            val operationIndex = readln().toInt()
            val operationCode = operationCodes[operationIndex]
            when (operationCode) {
                OperationCode.EXIT -> break
                OperationCode.REGISTER_NEW_ITEM -> registerNewItem()
                OperationCode.SHOW_ALL_ITEMS -> showAllItems()
                OperationCode.REMOVE_PRODUCT_CARD -> removeProductCard()
            }
        }
    }

    fun removeProductCard() {
        val cards = loadAllCards()
    }

    fun loadAllCards(): MutableList<ProductCard> {
        val cards = mutableListOf<ProductCard>()
        val products = file.readText().trim().split('\n')

        for (product in products) {
            val properties = product.split('%')
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()
            val type = properties.last()
            val productType = ProductType.valueOf(type)

            val productCard =
                when (productType) {
                    ProductType.FOOD -> {
                        val caloric = properties[3].toInt()
                        FoodCard(name, brand, price, caloric)
                    }

                    ProductType.APPLIANCE -> {
                        val wattage = properties[3].toInt()
                        ApplianceCard(name, brand, price, wattage)
                    }

                    ProductType.SHOE -> {
                        val size = properties[3].toFloat()
                        ShoeCard(name, brand, price, size)
                    }
                }
            cards.add(productCard)
        }
        return cards
    }

    fun showAllItems() {
        val cards = mutableListOf<ProductCard>()
        val products = file.readText().trim().split('\n')

        for (product in products) {
            val properties = product.split('%')
            val name = properties[0]
            val brand = properties[1]
            val price = properties[2].toInt()
            val type = properties.last()
            val productType = ProductType.valueOf(type)

            val productCard =
                when (productType) {
                    ProductType.FOOD -> {
                        val caloric = properties[3].toInt()
                        FoodCard(name, brand, price, caloric)
                    }

                    ProductType.APPLIANCE -> {
                        val wattage = properties[3].toInt()
                        ApplianceCard(name, brand, price, wattage)
                    }

                    ProductType.SHOE -> {
                        val size = properties[3].toFloat()
                        ShoeCard(name, brand, price, size)
                    }
                }
            productCard.printInfo()
            cards.add(productCard)
        }
    }

    fun registerNewItem() {
        val productTypes = ProductType.entries
        print("Enter the product type. ")
        for ((index, type) in productTypes.withIndex()) {
            print("$index - ${type.title}")
            if (index < productTypes.size - 1) {
                print(", ")
            } else {
                print(": ")
            }
        }
        val productTypeIndex = readln().toInt()
        val productType = productTypes[productTypeIndex]
        print("Enter the product name: ")
        val productName = readln()
        file.appendText("$productName%")
        print("Enter the product brand: ")
        val productBrand = readln()
        file.appendText("$productBrand%")
        print("Enter the product price: ")
        val productPrice = readln().toInt()
        file.appendText("$productPrice%")
        when (productType) {
            ProductType.FOOD -> {
                print("Enter the product caloric: ")
                val caloric = readln().toInt()
                file.appendText("$caloric%")
            }

            ProductType.APPLIANCE -> {
                print("Enter the product wattage: ")
                val wattage = readln().toInt()
                file.appendText("$wattage%")
            }

            ProductType.SHOE -> {
                print("Enter the product size: ")
                val size = readln().toFloat()
                file.appendText("$size%")
            }
        }
        file.appendText("$productType\n")
    }
}