fun main()
{
    var sideA = readLine()!!.toInt()
    var sideB = readLine()!!.toInt()
    var sideC = readLine()!!.toInt()

    if (!isTriangleExists(sideA, sideB, sideC))
    {
        println("Трикутник не існує!")
        return
    }

    println(
        when
        {
            isTwoSidesEquality(sideA, sideB, sideC) -> "Трикутник є рівнобедреним"
            else -> "Трикутник не є рівнобедреним"
        }
    )
}


fun isTwoSidesEquality(a: Int, b: Int, c: Int): Boolean =
    a == b && a!=c || b == c && a != b || a == c && c != b

fun isTriangleExists(a: Int, b: Int, c: Int): Boolean =
    a > 0 && b > 0 && c > 0 &&
            a + b > c && b + c > a && a + c > b