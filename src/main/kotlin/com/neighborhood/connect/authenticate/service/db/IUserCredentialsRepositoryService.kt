package com.neighborhood.connect.authenticate.service.db

import com.neighborhood.connect.authenticate.entities.UserCredentials

interface IUserCredentialsRepositoryService {
    fun addUserCredentials(userCredentials: UserCredentials): UserCredentials?
    fun doesUserExist(username: String): UserCredentials?
}