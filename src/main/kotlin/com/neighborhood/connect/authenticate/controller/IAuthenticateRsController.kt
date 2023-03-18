package com.neighborhood.connect.authenticate.controller

import com.neighborhood.connect.authenticate.models.SignInRequest
import com.neighborhood.connect.authenticate.models.SignUpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

interface IAuthenticateRsController {

    @GetMapping("/heartbeat", produces = ["application/json"])
    fun heartBeat(): String

    @PostMapping("/sign-up", produces = ["application/json"])
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Any>

    @PostMapping("/login", produces = ["application/json"])
    fun login(@RequestBody signInRequest: SignInRequest): ResponseEntity<Any>
}