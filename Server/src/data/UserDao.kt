package com.namrserver.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
    val name = varchar("name", 255)
    val lastName = varchar("lastName", 255)
    val email = varchar("email", 255)
    val phone = varchar("phone", 255)
}

//class BookEntity(id: EntityID<Int>) : IntEntity(id) {
class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(Users)

    var name by Users.name
    var lastName by Users.lastName
    var email by Users.email
    var phone by Users.phone

    override fun toString(): String = "Book($name, $lastName,$email, $phone)"

    fun toUser() = User(id.value, name, lastName, email, phone)
}

data class User(
    val id: Int,
    val name: String  ,
    val lastName: String ,
    val email: String ,
    val phone: String
)