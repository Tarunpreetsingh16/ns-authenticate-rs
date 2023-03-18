package com.neighborhood.connect.authenticate.controller

import com.neighborhood.connect.authenticate.models.SignInRequest
import com.neighborhood.connect.authenticate.models.SignUpRequest
import com.neighborhood.connect.authenticate.service.AuthenticateRsServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/authenticate")
class AuthenticateRsController (private val authenticateRsServiceImpl: AuthenticateRsServiceImpl):
    IAuthenticateRsController {

    override fun heartBeat(): String {
        return "Service is up and running"
    }

    override fun signUp(signUpRequest: SignUpRequest): ResponseEntity<Any> {
         return authenticateRsServiceImpl.createUserCredentials(signUpRequest)
    }

    override fun login(signInRequest: SignInRequest): ResponseEntity<Any> {
        return authenticateRsServiceImpl.login(signInRequest)
    }
}