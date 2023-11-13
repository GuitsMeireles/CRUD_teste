package com.crud.repository

import androidx.lifecycle.LiveData
import com.crud.data.db.dao.SubscriberDAO
import com.crud.data.db.entity.SubscriberEntity

class DataBaseDataSource(
    private val subscriberDAO: SubscriberDAO
) : SubscriberRepository {
    override suspend fun insertSubscriber(
        name: String,
        birth: String,
        cpf: String,
        tel: String
    ): Long {
        val subscriber = SubscriberEntity(
            name = name,
            birth = birth,
            cpf = cpf,
            tel = tel
        )

        return subscriberDAO.insert(subscriber)
    }

    override suspend fun updateSubscriber(
        id: Long,
        name: String,
        birth: String,
        cpf: String,
        tel: String
    ) {
        val subscriber = SubscriberEntity(
            id = id,
            name = name,
            birth = birth,
            cpf = cpf,
            tel = tel
        )

        subscriberDAO.update(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscriberDAO.delete(id)
    }

    override suspend fun deleteAllSubscriber() {
        subscriberDAO.deleteAll()
    }

    override suspend fun getAllSubscriber(): LiveData<List<SubscriberEntity>> {
        return subscriberDAO.getAll()
    }
}