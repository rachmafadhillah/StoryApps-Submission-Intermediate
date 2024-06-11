package com.dicoding.picodiploma.loginwithanimation.database

import androidx.paging.PagingSource
import androidx.room.*
import com.dicoding.picodiploma.loginwithanimation.data.response.ListStoryItem

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(quote: List<ListStoryItem>)

    @Query("SELECT * FROM story")
    fun getAllStory(): PagingSource<Int, ListStoryItem>

    @Query("DELETE FROM story")
    suspend fun deleteAll()
}