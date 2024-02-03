package com.clean_architecture_domain.usecase.user

import com.clean_architecture_domain.entity.UserEntity
import com.clean_architecture_domain.repository.UserRepository

class UpdateUserEmailUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(userId: Int, newEmail: String): UserEntity {
        val user = userRepository.findUserById(userId)
        user.email = newEmail
        return userRepository.updateUser(user)
    }
}