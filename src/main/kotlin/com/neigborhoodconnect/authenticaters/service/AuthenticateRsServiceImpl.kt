package com.neigborhoodconnect.authenticaters.service

import com.neigborhoodconnect.authenticaters.entities.UserCredentials
import com.neigborhoodconnect.authenticaters.models.SignUpRequest
import com.neigborhoodconnect.authenticaters.models.SignUpResponse
import com.neigborhoodconnect.authenticaters.service.db.UserCredentialsRepositoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticateRsServiceImpl(private val userCredentialsRepositoryService: UserCredentialsRepositoryService): IAuthenticateRsService {
    override fun createUserCredentials(signUpRequest: SignUpRequest): ResponseEntity<Any> {
        //check whether user already exists or not
        val doesUserExist = userCredentialsRepositoryService.doesUserExist(signUpRequest.username)
        return if(doesUserExist != null) {
            ResponseEntity("Account already exists with the username", HttpStatus.CONFLICT)
        } else {
            val encryptedPassword = BCryptPasswordEncoder().encode(signUpRequest.password)
            val userCredentials = UserCredentials(username = signUpRequest.username, password = encryptedPassword)
            kotlin.runCatching {
                val userCredentialsAddedResponse = userCredentialsRepositoryService.addUserCredentials(userCredentials)
                val signUpResponse = userCredentialsAddedResponse.username?.let { SignUpResponse(it) }
                return ResponseEntity.ok(signUpResponse)
            }.getOrElse {
                return ResponseEntity("Error while creating account", HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
    }
}