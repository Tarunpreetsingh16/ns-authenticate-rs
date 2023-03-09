package com.neigborhoodconnect.authenticaters.models

import com.neigborhoodconnect.authenticaters.models.interfaces.IBaseAuthenticate

data class SignUpRequest(
    override val username: String,
    override val password: String)
    : IBaseAuthenticate {

}
