package test.fragmentdemo

import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.TextView
import java.util.*

/**
 * Created by clw on 2017/10/23.
 */

object TestKotlin {
    @JvmStatic
    fun main(args: Array<String>) {
        //常量
        val a = lazy { 1 }   //延迟加载，默认线程安全
        val b = lazy(LazyThreadSafetyMode.NONE) { 1 } //延迟加载，线程不安全
        //变量
        var c = 1

        //条件 if else , when
        var x = 10
        if (x > 5) println("a")
        else println("b")

        when (x) {
            1 -> println("等于1")
            if (x > 0) 1 else -1 -> println("大于0并等于1,小于0等于-1")
            in 1..5 -> println()
            !in 1..5 -> println()
            is Int -> println()
//            x > 6 -> println()
            else -> println()
        }

        var y = 1
        // switch
//        when (y) {
//            y > 6 -> println()
//            y < 6 -> println()
//            else -> println()
//        }

        //自动转型
        var view: View? = null
        when (view) {
            is TextView -> println(view.text)
            is AdapterView<*> -> println("Item count = ${view.adapter.count}")
            is SearchView -> println("Current query: ${view.query}")
        }

        var arr = arrayOf(1, 2, 3)
        println(arr.toList())

        val asc = Array(5, { i -> (i * i).toString() })
        println("asc:" + asc.toList())


        val list = arrayListOf<String>("aa", "bb", "cc")
        //for(int i=0;i<list.length;i++)
        for (i in list.indices) {
            println(list[i])
        }
        //for(int i=2;i<list.length;i++)
        for (i in 2..list.size - 1) {
            println(list[i])
        }
        //for (int i=list.length-1;i>=0;i--)
        for (i in list.size downTo 0) {
            println(list[i])
        }
        //foreach
        for (str in list) {
            println(str)
        }
        for ((i, str) in list.withIndex()) {
            println(i)
            println(str)
        }
        list.forEach {
            println(it)
        }
        list.forEach { i -> println(i) }
        list.forEachIndexed { i, s ->
            println(i)
            println(s)
        }
        list.forEachIndexed(object : (Int, String) -> Unit {
            override fun invoke(i: Int, s: String) {
                print(list[i])
                print(s)
            }
        })

    }
}
