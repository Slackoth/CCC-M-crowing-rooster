package com.cccm.crowingrooster

//import android.app.Application
//import android.app.Instrumentation
//import androidx.room.Room
//import com.cccm.crowingrooster.database.CrowingRoosterDataBase
//import com.cccm.crowingrooster.database.daos.UserDao
//import org.junit.Test
//import androidx.test.platform.app.InstrumentationRegistry
//import com.cccm.crowingrooster.database.entities.User
//import org.junit.After
//import org.junit.Assert.*
//import org.junit.Before
//import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//class ExampleUnitTest {
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }
//}

//class DataBaseTest {
//    private lateinit var userDao: UserDao
//    private lateinit var db: CrowingRoosterDataBase
//
//    @Before
//    fun createDb() {
//        val context = InstrumentationRegistry.getInstrumentation().targetContext
//        db = Room.inMemoryDatabaseBuilder(context,CrowingRoosterDataBase::class.java)
//            .allowMainThreadQueries()
//            .build()
//        userDao = db.userDao
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetUser() {
//        val user = User("V-001","pipo","Luis","done","Vendedor")
//        userDao.insert(user)
//        val inserted = userDao.getUser(user.user_id)
//        assertEquals(inserted?.username, "pipo")
//    }
//
//}
