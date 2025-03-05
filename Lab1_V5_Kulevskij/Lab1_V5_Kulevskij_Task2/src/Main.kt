fun main()
{
    var count = 0;

    for(i in 1..99)
    {
        if(i.toString().length == 2)
        {
            count++;
        }
        print(i.toString() + " ")
    }
    println()
    print("Кількість двозначних чисел = " + count.toString())
}