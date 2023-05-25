package com.neighborhood.connect.authenticate.entities

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "user_credentials")
data class UserCredentials (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "username", nullable = false)
    val username: String?,

    @Column(name = "password", nullable = false)
    val password: String?,
) {
    constructor() : this(null, null, null)
}
