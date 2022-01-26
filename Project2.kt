package machine

enum class Beverage(val visibleName: String, val water: Int, val milk: Int, val beans: Int, val price: Int) {
    ESPRESSO("espresso", 250, 0, 16, 4),
    LATTE("latte", 350, 75, 20, 7),
    CAPPUCCINO("cappuccino", 200, 100, 12, 6),
}

val BEVERAGE_STRING = Beverage.values().joinToString { "${it.ordinal + 1} - ${it.visibleName}" }

class CoffeeMachine(var water: Int, var milk: Int, var beans: Int, var cups: Int, var money: Int) {

    fun getStatus(): String {
        return "The coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$beans of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$money of money"
    }

    fun buy(beverage: Beverage) {
        when {
            water < beverage.water -> println("Sorry, not enough water!")
            milk < beverage.milk -> println("Sorry, not enough milk!")
            beans < beverage.beans -> println("Sorry, not enough coffee beans!")
            cups < 1 -> println("Sorry, not enough disposable cups!")
            else -> {
                println("I have enough resources, making you a coffee!")
                water -= beverage.water
                milk -= beverage.milk
                beans -= beverage.beans
                cups--
                money += beverage.price
            }
        }
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine(400, 540, 120, 9, 550)
    while (true) {
        print("\nWrite action (buy, fill, take, remaining, exit): ")
        when (readLine()) {
            "buy" -> {
                print("What do you want to buy? $BEVERAGE_STRING, back - to main menu: ")
                when (val beverage = readLine()) {
                    "back" -> continue
                    else -> coffeeMachine.buy(Beverage.values()[beverage!!.toInt() - 1])
                }
            }
            "fill" -> {
                print("Write how many ml of water do you want to add: ")
                coffeeMachine.water += readLine()!!.toInt()
                print("Write how many ml of milk do you want to add: ")
                coffeeMachine.milk += readLine()!!.toInt()
                print("Write how many grams of coffee beans do you want to add: ")
                coffeeMachine.beans += readLine()!!.toInt()
                print("Write how many disposable cups of coffee do you want to add: ")
                coffeeMachine.cups += readLine()!!.toInt()
            }
            "take" -> {
                println("I gave you $${coffeeMachine.money}")
                coffeeMachine.money = 0
            }
            "remaining" -> {
                println(coffeeMachine.getStatus())
            }
            "exit" -> break
        }
        println()
    }
}