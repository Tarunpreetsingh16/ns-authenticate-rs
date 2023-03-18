package com.neighborhood.connect.authenticate.service

import com.neighborhood.connect.jwtlib.service.JWTService
import com.neighborhood.connect.jwtlib.util.JWTTokenUtil
import com.neighborhood.connect.authenticate.entities.UserCredentials
import com.neighborhood.connect.authenticate.models.SignUpRequest
import com.neighborhood.connect.authenticate.models.AuthenticateResponse
import com.neighborhood.connect.authenticate.service.db.UserCredentialsRepositoryServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
@Service
class AuthenticateRsServiceImpl(
    private val userCredentialsRepositoryServiceImpl: UserCredentialsRepositoryServiceImpl,
    private val jwtTokenUtil: JWTTokenUtil,
    private val jwtService: JWTService
)
    : IAuthenticateRsService {
    override fun createUserCredentials(signUpRequest: SignUpRequest): ResponseEntity<Any> {
        //check whether user already exists or not
        val doesUserExist = userCredentialsRepositoryServiceImpl.doesUserExist(signUpRequest.username)
        if(doesUserExist != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Account already exists with the username")
        } else {
            val encryptedPassword = BCryptPasswordEncoder().encode(signUpRequest.password)
            val userCredentials = UserCredentials(username = signUpRequest.username, password = encryptedPassword)
            kotlin.runCatching {
                //add user to the database if user does not already exist
                userCredentialsRepositoryServiceImpl.addUserCredentials(userCredentials)

                // generate jwt token to return to the user
                val token = jwtTokenUtil.generateToken(jwtService.loadUserByUsername(signUpRequest.username))
                return ResponseEntity.ok(AuthenticateResponse(token))
            }.getOrElse {
                throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, it.message)
            }
        }
    }
}