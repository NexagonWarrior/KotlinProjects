fun main()
{
    print("Введіть текст: ")
    val text = readln().trim()
    val sentences = mutableListOf<String>()
    var sentence = StringBuilder()

    for (char in text)
    {
        sentence.append(char)
        if (char == '.')
        {
            sentences.add(sentence.toString().trim())
            sentence = StringBuilder()
        }
    }

    for (sentence in sentences)
    {
        val words = sentence.split(' ')
            .filter { it.isNotEmpty() }
        println("Речення: \"$sentence\"")
        println("Кількість слів: ${words.size}")
        println()
    }
}
