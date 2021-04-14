package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.dao

import androidx.room.*
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.room.RoomHouseRepository

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomHouseRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomHouseRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomHouseRepository>)

    @Update
    fun update(repository: RoomHouseRepository)

    @Update
    fun update(vararg repositories: RoomHouseRepository)

    @Update
    fun update(repositories: List<RoomHouseRepository>)

    @Delete
    fun delete(repository: RoomHouseRepository)

    @Delete
    fun delete(vararg repositories: RoomHouseRepository)

    @Delete
    fun delete(repositories: List<RoomHouseRepository>)

    @Query("SELECT * FROM RoomHouseRepository")
    fun getAll(): List<RoomHouseRepository>

    @Query("SELECT * FROM RoomHouseRepository WHERE name = :name")
    fun findForHouse(name: String): List<RoomHouseRepository>

}