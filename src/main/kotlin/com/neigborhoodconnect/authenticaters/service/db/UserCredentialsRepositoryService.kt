package com.neigborhoodconnect.authenticaters.service.db

import com.neigborhoodconnect.authenticaters.entities.UserCredentials
import com.neigborhoodconnect.authenticaters.repositories.IUserCredentialsRepository
import org.springframework.stereotype.Service

@Service
class UserCredentialsRepositoryService(private val userCredentialsRepository: IUserCredentialsRepository) {
    fun addUserCredentials(userCredentials: UserCredentials): UserCredentials {
        return userCredentialsRepository.save(userCredentials)
    }

    fun doesUserExist(username: String): UserCredentials? {
        return userCredentialsRepository.findByUsername(username)
    }
}