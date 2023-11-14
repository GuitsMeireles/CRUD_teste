package com.crud.ui.subscriberlist

import androidx.lifecycle.ViewModel
import com.crud.repository.SubscriberRepository

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {
    val allSubscriberEvent = repository.getAllSubscriber()
}