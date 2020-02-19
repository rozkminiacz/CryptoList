package tech.michalik.cryptolist.utilities

interface Mapper<From, To> {
    fun map(from: From): To
}

fun <From, To> Mapper<From, To>.mapAll(list: List<From>): List<To> = list.map(this::map)