package com.neighborhood.connect.authenticate.validators

import com.neighborhood.connect.errorhandlerlib.ErrorCode
import com.neighborhood.connect.errorhandlerlib.models.Error
import com.neighborhood.connect.errorhandlerlib.models.FieldErrors

open class GenericValidator {
    private lateinit var obj: Any
    private var fieldErrors: FieldErrors = FieldErrors(HashMap<String, MutableList<Error>>())
    private lateinit var fieldName: String
    private var hasError = false
    val EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
    val PASSWORD_REGEX = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}\$"

    fun rule(obj: Any, fieldName: String): GenericValidator {
        this.obj = obj
        this.fieldName = fieldName
        return this
    }

    fun isValueBlank(): GenericValidator {
        if (this.obj is String && (this.obj as String).isBlank()) {
            val error = Error(
                id = ErrorCode.BLANK_FIELD.errorCode,
                description = ErrorCode.BLANK_FIELD.errorDescription
            )
            addToFieldErrors(error)
            hasError = true
        }
        return this
    }

    fun match(regex: String, failureCode: ErrorCode): GenericValidator {
        val pattern = Regex(regex)
        if (!(this.obj as String).contains(pattern)) {
            val error = Error(
                id = failureCode.errorCode,
                description = failureCode.errorDescription
            )
            addToFieldErrors(error)
            hasError = true
        }
        return this
    }

    fun compareWith(compareTo: String, failureCode: ErrorCode): GenericValidator {

        if ((this.obj as String).compareTo(compareTo) != 0) {

            val error = Error(
                id = failureCode.errorCode,
                description = failureCode.errorDescription
            )
            addToFieldErrors(error)
            hasError = true
        }
        return this
    }

    private fun addToFieldErrors(error: Error) {
        if (fieldErrors.errors[fieldName] != null)
            fieldErrors.errors[fieldName]?.add(error)
        else fieldErrors.errors[fieldName] = mutableListOf(error)
    }

    fun hasErrors(): Boolean {
        return hasError
    }

    fun getFieldErrors(): FieldErrors {
        return fieldErrors
    }
}
