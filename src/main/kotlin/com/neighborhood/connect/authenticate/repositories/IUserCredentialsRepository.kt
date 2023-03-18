package com.neighborhood.connect.authenticate.repositories

import com.neighborhood.connect.authenticate.entities.UserCredentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IUserCredentialsRepository : JpaRepository<UserCredentials, Long> {
    fun findByUsername(username: String): UserCredentials?
}