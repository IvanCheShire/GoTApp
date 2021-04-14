package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomHouse::class,
        parentColumns = ["name"],
        childColumns = ["url"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomHouseRepository(
    @PrimaryKey val name: String,
    val forksCount: String,
    val htmlUrl: String,
)