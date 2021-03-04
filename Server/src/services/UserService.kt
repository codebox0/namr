package com.namrserver.services


import com.namrserver.data.User
import com.namrserver.data.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction

class UserService {

    fun getAllUsers(): Iterable<User> = transaction {
        UserEntity.all().map(UserEntity::toUser)
    }

    fun addUser(user: User) = transaction {
        UserEntity.new {
            this.name = user.name
            this.lastName = user.lastName
            this.email = user.email
            this.phone = user.phone
        }
    }

    fun deleteUser(userId: Int) = transaction {
        UserEntity[userId].delete()
    }

    fun updateUser(userId: Int, user: User) = transaction {
        val userToUpdate: UserEntity? = UserEntity.findById(userId)
        if (userToUpdate != null) {
            userToUpdate.name = user.name
            userToUpdate.lastName = user.lastName
            userToUpdate.email = user.email
            userToUpdate.phone = user.phone
        }
    }
}