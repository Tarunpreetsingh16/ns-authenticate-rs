package com.neighborhood.connect.authenticate.models

import com.neighborhood.connect.authenticate.models.interfaces.IBaseAuthenticate

class SignInRequest (
    override val username: String,
    override val password: String)
    : IBaseAuthenticate {

}
