package com.dicoding.picodiploma.loginwithanimation.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.picodiploma.loginwithanimation.data.repository.StoryRepository
import com.dicoding.picodiploma.loginwithanimation.data.response.ListStoryItem

class StoryViewModel(repository: StoryRepository) : ViewModel() {

        val storyList: LiveData<PagingData<ListStoryItem>> =
                repository.getStories().cachedIn(viewModelScope)
}