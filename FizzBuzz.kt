const val FIZZ_BUZZ = "Fizz Buzz"
const val FIZZ = "Fizz"
const val BUZZ = "Buzz"
const val NUMBER = "Number"

class FizzBuzzScenario(scenario: String, numbers: String, count: Int) {
    private val numbers = numbers
    private val count = count
    private val scenario = scenario

    
    fun getNumbers(): String {
        return numbers
    }

    fun getNumberCount(): Int {
        return count
    }

    fun getScenario(): String {
        return scenario
    }

    fun printInfo() {
        println("Scenario: $scenario, Count: $count, Numbers: $numbers")
    }
}

fun initializeFizzBuzzMap(): HashMap<String, ArrayList<Int>> {
    val fizzBuzzMap = HashMap<String, ArrayList<Int>>()
    fizzBuzzMap[FIZZ_BUZZ] = ArrayList<Int>()
    fizzBuzzMap[FIZZ] = ArrayList<Int>()
    fizzBuzzMap[BUZZ] = ArrayList<Int>()
    fizzBuzzMap[NUMBER] = ArrayList<Int>()
    return fizzBuzzMap
}

fun populateFizzBuzzMap(n: Int, fizzBuzzMap: HashMap<String, ArrayList<Int>>) {
    for (i in 1..n) {
        val scenario = if (i % 3 == 0 && i % 5 == 0) {
            FIZZ_BUZZ
        } else if (i % 3 == 0) {
            FIZZ
        } else if (i % 5 == 0) {
            BUZZ
        } else {
            NUMBER
        }

        val scenarioList: ArrayList<Int>? = fizzBuzzMap[scenario]
        scenarioList?.add(i)
    }
}

fun getFizzBuzzMap(n: Int): HashMap<String, ArrayList<Int>> {
    val fizzBuzzMap: HashMap<String, ArrayList<Int>> = initializeFizzBuzzMap()
    populateFizzBuzzMap(n, fizzBuzzMap)
    return fizzBuzzMap;
}

fun getFizzBuzzScenario(scenario: String, numbers: ArrayList<Int>) : FizzBuzzScenario {
    val joinedNumbers = numbers.joinToString(separator = ", ", prefix = "[", postfix = "]")
    val count = numbers.size
    return FizzBuzzScenario(scenario, joinedNumbers, count)
}

fun getFizzBuzzScenarios(n: Int = 100): ArrayList<FizzBuzzScenario> {
    val fizzBuzzMap = getFizzBuzzMap(n)
    val fizzBuzzScenarios = ArrayList<FizzBuzzScenario>()

    for ((key, value) in fizzBuzzMap) {
        val fizzBuzzScenario = getFizzBuzzScenario(scenario = key, numbers = value)
        fizzBuzzScenarios.add(fizzBuzzScenario)
    }

    return fizzBuzzScenarios
}

fun main(args: Array<String>) {
    val scenarios = getFizzBuzzScenarios()

    for (scenario in scenarios) {
        scenario.printInfo()
    }
}