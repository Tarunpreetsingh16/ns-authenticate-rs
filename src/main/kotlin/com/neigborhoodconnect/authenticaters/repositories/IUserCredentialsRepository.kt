package com.neigborhoodconnect.authenticaters.repositories

import com.neigborhoodconnect.authenticaters.entities.UserCredentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IUserCredentialsRepository : JpaRepository<UserCredentials, Long> {
    fun findByUsername(username: String): UserCredentials?
}