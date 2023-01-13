package example.com.simlist.data

import android.provider.LiveFolders
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM USER_TABLE ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}