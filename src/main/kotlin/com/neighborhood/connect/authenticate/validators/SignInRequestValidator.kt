package com.neighborhood.connect.authenticate.validators

import com.neighborhood.connect.authenticate.models.SignInRequest
import com.neighborhood.connect.errorhandlerlib.ErrorCode
import com.neighborhood.connect.errorhandlerlib.models.FieldErrors

class SignInRequestValidator : GenericValidator() {
    fun validate(signInRequest: SignInRequest): FieldErrors {
        rule(signInRequest.username, "username")
            .isValueBlank()
            .match(
                regex = EMAIL_REGEX,
                failureCode = ErrorCode.INVALID_USERNAME_OR_EMAIL
            )
        rule(signInRequest.password, "password")
            .isValueBlank()
        return getFieldErrors()
    }
}
