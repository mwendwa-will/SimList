package example.com.simlist.data

import androidx.lifecycle.LiveData

class UserRespository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}