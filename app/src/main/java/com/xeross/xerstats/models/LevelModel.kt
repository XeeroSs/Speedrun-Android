package com.xeross.xerstats.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LevelModel(var bestLevel: String = "00:00:00",
                      var pbLevel: String = "00:00:00",
                      var timerLevel: String = "00:00:00",
                      @PrimaryKey var idLevel: Int = 0)