package com.example.locationupdatesexample

fun hasAnagram(strings: List<String>): Boolean {
    val seen = mutableMapOf<String, Boolean>()

    for (str in strings) {
        val sortedStr = str.toCharArray().sorted().joinToString("")

        if (seen.containsKey(sortedStr)) {
            return true
        } else {
            seen[sortedStr] = true
        }
    }
    return false
}