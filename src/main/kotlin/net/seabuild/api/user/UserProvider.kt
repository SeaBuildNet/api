package net.seabuild.api.user

import java.util.UUID

interface UserProvider {
    fun exists(uuid: UUID): Boolean

    fun get(uuid: UUID): User?

    fun getOrCreate(uuid: UUID, name: String): User

    fun persist(user: User)

    fun delete(user: User)

    fun delete(uuid: UUID)

    suspend fun existsSuspended(uuid: UUID): Boolean

    suspend fun getSuspended(uuid: UUID): User?

    suspend fun getOrCreateSuspended(uuid: UUID, name: String): User

    suspend fun persistSuspended(user: User)

    suspend fun deleteSuspended(user: User)

    suspend fun deleteSuspended(uuid: UUID)
}