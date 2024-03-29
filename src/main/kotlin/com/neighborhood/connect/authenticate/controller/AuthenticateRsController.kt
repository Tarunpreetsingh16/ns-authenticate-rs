package com.neighborhood.connect.authenticate.controller

import com.neighborhood.connect.authenticate.models.SignInRequest
import com.neighborhood.connect.authenticate.models.SignUpRequest
import com.neighborhood.connect.authenticate.service.AuthenticateRsServiceImpl
import com.neighborhood.connect.authenticate.validators.SignInRequestValidator
import com.neighborhood.connect.authenticate.validators.SignUpRequestValidator
import com.neighborhood.connect.errorhandlerlib.customExceptions.FieldsValidationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/authenticate")
class AuthenticateRsController(private val authenticateRsServiceImpl: AuthenticateRsServiceImpl) :
    IAuthenticateRsController {

    override fun heartBeat(): String {
        return "Service is up and running"
    }

    override fun signUp(signUpRequest: SignUpRequest): ResponseEntity<Any> {
        val signUpRequestValidationResult = SignUpRequestValidator().validate(signUpRequest = signUpRequest)
        if (signUpRequestValidationResult.errors.size > 0) {
            throw FieldsValidationException(signUpRequestValidationResult)
        }
        return authenticateRsServiceImpl.createUserCredentials(signUpRequest)
    }

    override fun login(signInRequest: SignInRequest): ResponseEntity<Any> {
        val signInRequestValidationResult = SignInRequestValidator().validate(signInRequest = signInRequest)
        if (signInRequestValidationResult.errors.size > 0) {
            throw FieldsValidationException(signInRequestValidationResult)
        }
        return authenticateRsServiceImpl.login(signInRequest)
    }
}
