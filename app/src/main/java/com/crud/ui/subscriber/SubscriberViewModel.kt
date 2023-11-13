package com.crud.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crud.R
import com.crud.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    private val _subscriberStateEvenData = MutableLiveData<SubscriberState>()
    val subcriberStateEvenData: LiveData<SubscriberState>
        get() = _subscriberStateEvenData

    private val _messageEvenData = MutableLiveData<Int>()
    val messageEvenData: LiveData<Int>
        get() = _messageEvenData

    fun addSubscriber(name: String, birth: String, cpf: String, tel: String) =
        viewModelScope.launch {
            try {
                val id = repository.insertSubscriber(name, birth, cpf, tel)
                if (id > 0) {
                    _subscriberStateEvenData.value = SubscriberState.Inserted
                    _messageEvenData.value = R.string.subscriber_insert_successfully
                }
            } catch (ex: Exception) {

                Log.e("", ex.toString())
            }

        }

    sealed class SubscriberState {
        object Inserted : SubscriberState()
    }

    companion object {
        private val TAG = SubscriberViewModel::class.java.simpleName
    }
}