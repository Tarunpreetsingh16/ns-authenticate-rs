package com.neigborhoodconnect.authenticaters.controller

import org.springframework.web.bind.annotation.GetMapping

interface IAuthenticateRsController {

    @GetMapping("/heartbeat")
    fun heartBeat(): String
}