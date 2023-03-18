package com.neighborhood.connect.authenticate.models

import com.neighborhood.connect.authenticate.models.interfaces.IBaseAuthenticate

data class SignUpRequest(
    override val username: String,
    override val password: String)
    : IBaseAuthenticate {

}
