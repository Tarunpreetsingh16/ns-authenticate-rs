package com.neighborhood.connect.authenticate.service

import com.neighborhood.connect.authenticate.models.SignInRequest
import com.neighborhood.connect.authenticate.models.SignUpRequest
import org.springframework.http.ResponseEntity

interface IAuthenticateRsService {
    fun createUserCredentials(signUpRequest: SignUpRequest): ResponseEntity<Any>

    fun login(signInRequest: SignInRequest): ResponseEntity<Any>
}