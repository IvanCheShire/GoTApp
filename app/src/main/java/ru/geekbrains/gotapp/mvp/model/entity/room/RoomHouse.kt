package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
class RoomHouse(
    @PrimaryKey var name: String,
    val region: String,
    val words: String,
    val currentLord: String,
    val url: String
)