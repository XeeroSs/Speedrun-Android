package com.xeross.xerstats

import com.xeross.xerstats.models.LevelModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val time = "30:20:10"
        val second = time.substring(6, 8)
        val minute = time.substring(3, 5)
        val hour = time.substring(0, 2)
        assertEquals("10", second)
        assertEquals("20", minute)
        assertEquals("30", hour)
    }

    @Test
    fun efsef() {
        for (i in 1..7) {
            println(i)
        }
    }

    @Test
    fun test() {
        var secondBest: Long = 0
        val levels = ArrayList<LevelModel>()
        levels.add(
            LevelModel(
                "00:01:54",
                "00:00:00", "00:02:19", 0
            )
        )
        levels.add(
            LevelModel(
                "00:02:19",
                "00:00:00", "00:00:00", 0
            )
        )
        levels.add(
            LevelModel(
                "00:16:90",
                "00:00:00", "00:00:00", 0
            )
        )
        levels.add(
            LevelModel(
                "00:00:34",
                "00:00:00", "00:00:00", 0
            )
        )
        levels.add(
            LevelModel(
                "00:00:56",
                "00:00:00", "00:00:00", 0
            )
        )
        levels.add(
            LevelModel(
                "00:05:40",
                "00:00:00", "00:00:00", 0
            )
        )
        levels.add(
            LevelModel(
                "00:04:21",
                "00:00:00", "00:00:00", 0
            )
        )
        levels.forEach { level ->
            val second =
                if (level.bestLevel.length >= 8) level.bestLevel.substring(6, 8).toInt() else 0
            val minute =
                if (level.bestLevel.length >= 5) level.bestLevel.substring(3, 5).toInt() else 0
            val hour =
                if (level.bestLevel.length >= 2) level.bestLevel.substring(0, 2).toInt() else 0
            if (hour != 0) secondBest += hour * 60 * 60
            if (minute != 0) secondBest += minute * 60
            if (second != 0) secondBest += second
        }
        val secondF = secondBest % 60
        var hourF = secondBest / 60
        val minuteF = hourF % 60
        hourF /= 60
        val bestTime = "${if (hourF < 10) "0" else ""}$hourF:" +
                "${if (minuteF < 10) "0" else ""}$minuteF:" +
                "${if (secondF < 10) "0" else ""}$secondF"
        println(bestTime)
    }
}
