package net.seabuild.api.user

import java.util.UUID

interface UserProvider<U: User> {
    fun exists(uuid: UUID): Boolean

    fun getOrCreate(uuid: UUID): U

    fun persist(u: U)

    fun delete(u: U)

    fun delete(uuid: UUID)

    suspend fun existsSuspended(uuid: UUID): Boolean

    suspend fun getOrCreateSuspended(uuid: UUID): U

    suspend fun persistSuspended(u: U)

    suspend fun deleteSuspended(u: U)

    suspend fun deleteSuspended(uuid: UUID)
}