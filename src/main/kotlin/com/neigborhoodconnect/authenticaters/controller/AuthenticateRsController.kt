package com.neigborhoodconnect.authenticaters.controller

import com.neigborhoodconnect.authenticaters.models.SignUpRequest
import com.neigborhoodconnect.authenticaters.service.AuthenticateRsServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/authenticate")
class AuthenticateRsController (private val authenticateRsServiceImpl: AuthenticateRsServiceImpl): IAuthenticateRsController {

    override fun heartBeat(): String {
        return "Service is up and running"
    }

    override fun signUp(signUpRequest: SignUpRequest): ResponseEntity<Any> {
        kotlin.runCatching {
            return authenticateRsServiceImpl.createUserCredentials(signUpRequest)
        }.getOrElse {
            return ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}