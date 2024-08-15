// IBaseNumber.kt
interface IBaseNumber {
    val value: Int
    fun printValue()
}

// PrimeNumber.kt
class PrimeNumber(override val value: Int) : IBaseNumber {
    override fun printValue() {
        println("numeros primos: $value")
    }
}

// OddNumber.kt
class OddNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = calculateDivisors(value)

    override fun printValue() {
        println("numero impar: $value, Divisores: $divisors")
    }

    private fun calculateDivisors(number: Int): List<Int> {
        return (1..number).filter { number % it == 0 }
    }
}

// EvenNumber.kt
class EvenNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = calculateDivisors(value)

    override fun printValue() {
        println("numero par: $value, Divisores: $divisors")
    }

    private fun calculateDivisors(number: Int): List<Int> {
        return (1..number).filter { number % it == 0 }
    }
}

// PrimeNumberProcessor.kt
class PrimeNumberProcessor(private val range: IntRange) {
    private val primes = mutableListOf<PrimeNumber>()
    private val odds = mutableListOf<OddNumber>()
    private val evens = mutableListOf<EvenNumber>()

    fun process() {
        for (number in range) {
            when (validateNumber(number)) {
                NumberType.PRIME -> primes.add(PrimeNumber(number))
                NumberType.ODD -> odds.add(OddNumber(number))
                NumberType.EVEN -> evens.add(EvenNumber(number))
            }
        }
        printResults()
    }

    private fun validateNumber(number: Int): NumberType {
        return when {
            isPrime(number) -> NumberType.PRIME
            number % 2 == 0 -> NumberType.EVEN
            else -> NumberType.ODD
        }
    }

    private fun isPrime(number: Int): Boolean {
        if (number < 2) return false
        for (i in 2..Math.sqrt(number.toDouble()).toInt()) {
            if (number % i == 0) return false
        }
        return true
    }

    private fun printResults() {
        println("Numeros Primos:")
        primes.forEach { it.printValue() }
        
        println("Numeros Impares:")
        odds.forEach { it.printValue() }
        
        println("Numeros Pares:")
        evens.forEach { it.printValue() }
    }
}

// NumberType.kt
enum class NumberType {
    PRIME, ODD, EVEN
}

// Main.kt
fun main() {
    val processor = PrimeNumberProcessor(1..100)
    processor.process()
}
