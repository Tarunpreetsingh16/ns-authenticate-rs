package com.neigborhoodconnect.authenticaters.entities

import jakarta.persistence.*

@Entity
@Table(name = "user_credentials")
data class UserCredentials (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "username", nullable = false)
    val username: String?,

    @Column(name = "password", nullable = false)
    val password: String?,

    @Column(name = "access_token", nullable = true, )
    val accessToken: String? = null
) {
    constructor() : this(null, null, null, null)
}
