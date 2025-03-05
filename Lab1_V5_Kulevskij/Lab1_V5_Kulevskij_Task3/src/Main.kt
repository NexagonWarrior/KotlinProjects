fun main()
{
    for(i in 10..99)
    {
        if( i % 9 == 0 || i / 10 == 9 || i % 10 == 9)
        {
            print(i.toString() + " ")
        }
    }
}