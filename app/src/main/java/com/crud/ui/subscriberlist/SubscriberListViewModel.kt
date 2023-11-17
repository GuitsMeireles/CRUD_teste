package com.crud.ui.subscriberlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crud.data.db.entity.SubscriberEntity
import com.crud.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    private val _allSubscriberEvent = MutableLiveData<List<SubscriberEntity>>()
    val allSubscriberEvent: LiveData<List<SubscriberEntity>>
        get() = _allSubscriberEvent

    fun getSubscriber() = viewModelScope.launch {
        _allSubscriberEvent.postValue(repository.getAllSubscriber())
    }

}