fun main()
{
    print("Введіть кількість чисел (N < 15): ")
    val n = readln().toInt()

    if (n <= 0 || n >= 15)
    {
        println("Некоректне значення N. Введіть число від 1 до 14.")
        return
    }

    val numbers = IntArray(n)
    println("Введіть $n натуральних чисел:")
    for (i in numbers.indices)
    {
        numbers[i] = readln().toInt()
        if (numbers[i] <= 0)
        {
            println("Число повинно бути натуральним! Спробуйте ще раз.")
            return
        }
    }

    val lcmResult = numbers.reduce { acc, num -> lcm(acc, num) }

    println("Найменше спільне кратне: $lcmResult")
}


fun lcm(a: Int, b: Int): Int
{
    return a / gcd(a, b) * b
}


fun gcd(a: Int, b: Int): Int
{
    return if (b == 0) a else gcd(b, a % b)
}
