package com.clean_architecture_domain.repository

import com.clean_architecture_domain.entity.UserEntity

interface UserRepository {
    suspend fun findUserById(userId: Int): UserEntity
    suspend fun updateUser(user: UserEntity): UserEntity
}