class AutoCompleteSystem {
    private val root = Node()

    fun insertWord(word: String) {
        var node = root;

        for(ch in word) {
            if(!node.children.containsKey(ch)) {
                node.children[ch] = Node()
            }

            node = node.children[ch]!!
        }

        node.isWordComplete = true
    }

    fun hasWord(word: String): Boolean {
        var node = getNode(word)
        return node != null && node.isWordComplete
    }

    fun autoComplete(prefix: String): ArrayList<String> {
        val words = ArrayList<String>()
        val node = getNode(prefix)

        if (node != null) {
            autoCompleteHelper(node, prefix, words)
        }

        return words
    }

    private fun getNode(word: String) : Node? {
        var node = root

        for(ch in word) {
            if (!node.children.containsKey(ch)) {
                return null
            }

            node = node.children[ch]!!
        }

        return node
    }

    private fun autoCompleteHelper(node: Node, word: String, words: ArrayList<String>) {
        if (node.isWordComplete) {
            words.add(word)
        }

        for((key, value) in node.children) {
            autoCompleteHelper(value, word + key, words)
        }
    }


    class Node {
        var isWordComplete = false
        val children = HashMap<Char, Node>()
    }
}

fun main() {
    val words = listOf("Book", "Car", "Orange", "Pear", "Plane", "Pixel", "Pixy", "Organic")
    val system = AutoCompleteSystem()

    for (word in words) {
        system.insertWord(word)
    }

    println(system.autoComplete("")) // "Book", "Car", "Orange", "Pear", "Plane", "Pixel", "Pixy", "Organic"
    println(system.autoComplete("Or")) // "Orange", "Organic"
    println(system.autoComplete("Pix")) // "Pixel", "Pixy"
}