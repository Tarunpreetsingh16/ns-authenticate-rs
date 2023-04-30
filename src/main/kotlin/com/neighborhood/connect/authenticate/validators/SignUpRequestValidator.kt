package com.neighborhood.connect.authenticate.validators

import com.neighborhood.connect.authenticate.models.SignUpRequest
import com.neighborhood.connect.errorhandlerlib.ErrorCode
import com.neighborhood.connect.errorhandlerlib.models.FieldErrors

class SignUpRequestValidator : GenericValidator() {
    fun validate(signUpRequest: SignUpRequest): FieldErrors {
        rule(signUpRequest.username, "username")
            .isValueBlank()
            .match(
                regex = EMAIL_REGEX,
                failureCode = ErrorCode.INVALID_USERNAME_OR_EMAIL
            )
        rule(signUpRequest.password, "password")
            .isValueBlank()
            .match(
                regex = PASSWORD_REGEX,
                failureCode = ErrorCode.INVALID_PASSWORD
            )
        rule(signUpRequest.confirmPassword, "confirmPassword")
            .isValueBlank()
            .compareWith(signUpRequest.password, failureCode = ErrorCode.PASSWORD_MISMATCH)
        return getFieldErrors()
    }
}
