package com.neigborhoodconnect.authenticaters.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

interface IAuthenticateRsController {

    @GetMapping("/heartbeat")
    fun heartBeat(): String

    @PostMapping("/sign-up")
    fun signUp(): ResponseEntity<String>
}