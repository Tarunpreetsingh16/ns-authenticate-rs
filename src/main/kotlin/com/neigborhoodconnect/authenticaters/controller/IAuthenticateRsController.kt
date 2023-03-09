package com.neigborhoodconnect.authenticaters.controller

import com.neigborhoodconnect.authenticaters.models.SignUpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface IAuthenticateRsController {

    @GetMapping("/heartbeat")
    fun heartBeat(): String

    @PostMapping("/sign-up")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Any>
}