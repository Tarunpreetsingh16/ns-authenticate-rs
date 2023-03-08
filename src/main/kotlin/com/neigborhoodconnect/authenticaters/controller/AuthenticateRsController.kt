package com.neigborhoodconnect.authenticaters.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/authenticate")
class AuthenticateRsController: IAuthenticateRsController {

    override fun heartBeat(): String {
        return "Service is up and running"
    }
}