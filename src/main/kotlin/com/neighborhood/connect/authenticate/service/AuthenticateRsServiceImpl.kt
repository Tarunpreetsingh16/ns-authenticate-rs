package com.neighborhood.connect.authenticate.service

import com.neighborhood.connect.jwtlib.service.JWTService
import com.neighborhood.connect.jwtlib.util.JWTTokenUtil
import com.neighborhood.connect.authenticate.entities.UserCredentials
import com.neighborhood.connect.authenticate.models.SignUpRequest
import com.neighborhood.connect.authenticate.models.AuthenticateResponse
import com.neighborhood.connect.authenticate.models.SignInRequest
import com.neighborhood.connect.authenticate.service.db.UserCredentialsRepositoryServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
@Service
class AuthenticateRsServiceImpl(
    private val userCredentialsRepositoryServiceImpl: UserCredentialsRepositoryServiceImpl,
    private val jwtTokenUtil: JWTTokenUtil,
    private val jwtService: JWTService,
    private val authenticationManager: AuthenticationManager
)
    : IAuthenticateRsService {
    override fun createUserCredentials(signUpRequest: SignUpRequest): ResponseEntity<Any> {
        //check whether user already exists or not
        val userCredentialsIfExists =
            userCredentialsRepositoryServiceImpl.getUserCredentialsIfExists(signUpRequest.username)
        if (userCredentialsIfExists != null) {
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

    override fun login(signInRequest: SignInRequest): ResponseEntity<Any> {
        kotlin.runCatching {
            //try to authenticate the user using the authentication manager
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    signInRequest.username,
                    signInRequest.password
                )
            )

            // generate jwt token to return to the user
            val token = jwtTokenUtil.generateToken(jwtService.loadUserByUsername(signInRequest.username))
            return ResponseEntity.ok(AuthenticateResponse(token))
        }.getOrElse {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, it.message)
        }
    }
}