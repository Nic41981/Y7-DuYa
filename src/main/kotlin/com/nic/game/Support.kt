package com.dy.game

/**
 * 对列表进行深拷贝
 */
fun <E> ArrayList<E>.copy(): ArrayList<E> {
    val copy = ArrayList<E>(this.size)
    for (it in this) {
        copy.add(it)
    }
    return copy
}

/**
 * 将字符串转化为坐标信息
 * @param str 坐标的字符串信息
 * @return 坐标信息
 */
fun pairOf(str: String?): Pair<Int,Int>? {
    if (str.isNullOrBlank()) {
        return null
    }
    str.replace(Regex(pattern = "[{}<>()\\[\\]]"),replacement = "")
    val tmp = str.split(Regex(pattern = "[,;: _/\\\\-]"))
    if (tmp.size < 2) {
        return null
    }
    val x = tmp[0].toIntOrNull() ?: return null
    val y = tmp[1].toIntOrNull() ?: return null
    return Pair(x, y)
}