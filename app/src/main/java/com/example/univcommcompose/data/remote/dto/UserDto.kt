package com.example.univcommcompose.data.remote.dto

import com.example.univcommcompose.domain.model.Post
import com.example.univcommcompose.domain.model.User

data class UserDto(
    val email: String,
    val first_name: String,
    val id: Int,
    val is_active: Int,
    val last_name: String,
    val roll_no: String,
    val user_type: Int
)


fun UserDto.toUser(): User {
    return User(
        id = id,
        email = email,
        first_name = first_name,
        last_name = last_name,
        roll_no = roll_no,
        user_type = user_type
    )
}