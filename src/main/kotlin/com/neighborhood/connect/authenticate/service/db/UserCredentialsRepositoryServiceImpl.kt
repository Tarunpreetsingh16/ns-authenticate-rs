package com.neighborhood.connect.authenticate.service.db

import com.neighborhood.connect.authenticate.entities.UserCredentials
import com.neighborhood.connect.authenticate.repositories.IUserCredentialsRepository
import org.springframework.stereotype.Service

@Service
class UserCredentialsRepositoryServiceImpl(private val userCredentialsRepository: IUserCredentialsRepository)
    : IUserCredentialsRepositoryService {

    override fun addUserCredentials(userCredentials: UserCredentials): UserCredentials {
        return userCredentialsRepository.save(userCredentials)
    }

    override fun doesUserExist(username: String): UserCredentials? {
        return userCredentialsRepository.findByUsername(username)
    }
}