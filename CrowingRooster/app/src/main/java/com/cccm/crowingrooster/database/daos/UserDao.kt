package com.cccm.crowingrooster.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cccm.crowingrooster.database.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE user.user_id = :id")
    fun getUser(id: String): User?

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<MutableList<User>>
}