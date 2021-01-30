package com.aamfahrur.retrofitrestapi.models

data class UsersResponse(
    val error: Boolean,
    val users: ArrayList<User>
)