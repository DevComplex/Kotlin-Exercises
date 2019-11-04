import java.io.File
import java.lang.Exception
import kotlin.system.exitProcess

fun readWordsFromFile(filePath: String) : List<String> {
    val file = File(filePath)
    return file.readLines()
}

fun getLetterCounts(word: String): String {
    val letterCounts = IntArray(26)

    for(ch in word) {
        val letterIndex = ch.toInt() - 'a'.toInt()
        letterCounts[letterIndex] += 1
    }

    return letterCounts.joinToString(separator = ",", prefix = "[", postfix = "]")
}

fun groupAnagrams(words: List<String>) : HashMap<String, HashSet<String>> {
    val groupedAnagrams = HashMap<String, HashSet<String>>()

    for (word in words) {
        val letterCounts = getLetterCounts(word.toLowerCase())

        if (!groupedAnagrams.containsKey(letterCounts)) {
            groupedAnagrams[letterCounts] = HashSet<String>()
        }

        groupedAnagrams[letterCounts]?.add(word)
    }

    return groupedAnagrams
}

fun getAnagramsOfWord(word: String, groupedAnagrams: HashMap<String, HashSet<String>>) : String? {
    val letterCounts = getLetterCounts(word)
    val anagramsOfWord = groupedAnagrams[letterCounts]

    if (anagramsOfWord != null && anagramsOfWord.isNotEmpty()) {
        return word + ", " + anagramsOfWord.joinToString(separator = ", ")
    }

    return null
}

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Please specify a word and file path.")
        exitProcess(1)
    }

    val word = args[0]
    val filePath = args[1]

    var wordsFromFile: List<String>? = null

    try {
        wordsFromFile = readWordsFromFile(filePath)
    } catch (e: Exception) {
        println("Error reading file: $filePath")
        exitProcess(1)
    }

    if (wordsFromFile.isNotEmpty()) {
        val groupedAnagrams = groupAnagrams(wordsFromFile)
        val anagramsOfWord = getAnagramsOfWord(word, groupedAnagrams)
        println(anagramsOfWord)
    }
}