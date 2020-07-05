package com.dy.game

var width = 0
var height = 0
var num = 0
var pointList = arrayListOf<Pair<Int,Int>>()
lateinit var start: Pair<Int, Int>
lateinit var stop: Pair<Int, Int>

fun main(args: Array<String>) {
    io()
    process(
            p = start.copy(),
            last = null,
            noUsed = pointList.copy().also {
                it.remove(start)
            },
            path = arrayListOf()
    )
}

fun io() {
    do {
        print("请输入行数:")
        val tmp = readLine()?.toIntOrNull()
        if (tmp == null || tmp < 1) {
            println("[提示]请输入正确的行数(n>=1)!")
            continue
        }
        height = tmp
        break
    } while (true)
    do {
        print("请输入列数:")
        val tmp = readLine()?.toIntOrNull()
        if (tmp == null || tmp < 1) {
            println("[提示]请输入正确的列数(n>=1)!")
            continue
        }
        width = tmp
        break
    } while (true)
    do {
        print("请输入路径点数量:")
        val tmp = readLine()?.toIntOrNull()
        if (tmp == null || tmp < 2) {
            println("[提示]请输入正确的路径点数量(n>=2)!")
            continue
        }
        num = tmp
        break
    } while (true)
    for (i in 1..num) {
        do {
            print("请输入第${i}个路径点坐标:")
            val tmp = pairOf(readLine())
            if (tmp == null || tmp.first !in 1..width || tmp.second !in 1..height) {
                println("[提示]请输入正确的路径点坐标!")
                continue
            }
            pointList.add(tmp)
            break
        } while (true)
    }
    do {
        print("请输入起点坐标:")
        val tmp = pairOf(readLine())
        if (tmp == null || !pointList.contains(tmp)) {
            println("[提示]请输入正确的起点坐标!")
            continue
        }
        start = tmp
        break
    } while (true)
    do {
        print("请输入终点坐标:")
        val tmp = pairOf(readLine())
        if (tmp == null || !pointList.contains(tmp)) {
            println("[提示]请输入正确的终点坐标!")
            continue
        }
        stop = tmp
        break
    } while (true)
}

fun process(p: Pair<Int,Int>, last: Pair<Int, Int>?, noUsed: ArrayList<Pair<Int, Int>>, path: ArrayList<Pair<Int, Int>>) {
    if (noUsed.isEmpty() && p == stop) {
        //结束递归（发现）
        out(path.also { it.add(p) })
        return
    }
    var top: Pair<Int, Int>? = null
    var bottom: Pair<Int, Int>? = null
    var left: Pair<Int, Int>? = null
    var right: Pair<Int, Int>? = null
    for (it in noUsed) {
        if (it.first == p.first) {
            //x轴相同（同一列）
            if (it.second < p.second) {
                //y轴小（上方）
                if (top == null || top.second < it.second) {
                    //最近点
                    if (last == null || last.first != p.first || last.second !in it.second..p.second) {
                        //不是回头路
                        top = it.copy()
                    }
                }
            }
            else {
                //y轴大（下方）
                if (bottom == null || bottom.second > it.second) {
                    //最近点
                    if (last == null || last.first != p.first || last.second !in p.second..it.second) {
                        //不是回头路
                        bottom = it.copy()
                    }
                }
            }
        }
        else if (it.second == p.second) {
            //y轴相同（同一行）
            if (it.first < p.first) {
                //x轴小（左边）
                if (left == null || left.first < it.first) {
                    //最近点
                    if (last == null || last.second != p.second || last.first !in it.first..p.first) {
                        //不是回头路
                        left = it.copy()
                    }
                }
            }
            else {
                //x轴大（右边）
                if (right == null || right.first > it.first) {
                    //最近点
                    if (last == null || last.second != p.second || last.first !in p.first..it.first) {
                        //不是回头路
                        right = it.copy()
                    }
                }
            }
        }
    }
    if (top == null && bottom == null && left == null && right == null) {
        //结束递归（未发现）
        return
    }
    if (top != null) {
        process(
                p = top,
                last = p,
                noUsed = noUsed.copy().also {
                    it.remove(top)
                },
                path = path.copy().also {
                    it.add(p)
                }
        )
    }
    if (bottom != null) {
        process(
                p = bottom,
                last = p,
                noUsed = noUsed.copy().also {
                    it.remove(bottom)
                },
                path = path.copy().also {
                    it.add(p)
                }
        )
    }
    if (left != null) {
        process(
                p = left,
                last = p,
                noUsed = noUsed.copy().also {
                    it.remove(left)
                },
                path = path.copy().also {
                    it.add(p)
                }
        )
    }
    if (right != null) {
        process(
                p = right,
                last = p,
                noUsed = noUsed.copy().also {
                    it.remove(right)
                },
                path = path.copy().also {
                    it.add(p)
                }
        )
    }
}

fun out(answer: List<Pair<Int,Int>>) {
    var flag = false
    for (it in answer) {
        if (flag) {
            print(" -> ")
        }
        else {
            flag = true
        }
        print(it)
    }
    println()
}