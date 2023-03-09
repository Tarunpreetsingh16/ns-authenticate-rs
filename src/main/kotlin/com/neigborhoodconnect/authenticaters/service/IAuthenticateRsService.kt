package com.neigborhoodconnect.authenticaters.service

import com.neigborhoodconnect.authenticaters.models.SignUpRequest
import org.springframework.http.ResponseEntity

interface IAuthenticateRsService {
    fun createUserCredentials(signUpRequest: SignUpRequest): ResponseEntity<Any>
}