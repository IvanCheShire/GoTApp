package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomHouse

@Dao
interface HouseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(house: RoomHouse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg houses: RoomHouse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(house: List<RoomHouse>)

    @Update
    fun update(house: RoomHouse)

    @Update
    fun update(vararg houses: RoomHouse)

    @Update
    fun update(houses: List<RoomHouse>)

    @Delete
    fun delete(house: RoomHouse)

    @Delete
    fun delete(vararg houses: RoomHouse)

    @Delete
    fun delete(houses: List<RoomHouse>)

    @Query("SELECT * FROM RoomHouse")
    fun getAll(): List<RoomHouse>

    @Query("SELECT * FROM RoomHouse WHERE name = :name LIMIT 1")
    fun findByName(name: String): RoomHouse?

}