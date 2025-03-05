fun main()
{
    val array = List(20) { (1..200).random() }
    println(array.joinToString(", "))

    val multiplesOf11 = array.filter { it % 11 == 0 }.sortedDescending()

    if (multiplesOf11.isNotEmpty()) {
        println("Числа, кратні 11 (в порядку спадання): ${multiplesOf11.joinToString(", ")}")
    } else {
        println("У масиві немає чисел, кратних 11.")
    }
}
