package net.seabuild.api.storage

import com.github.reapermaga.library.common.repository.Repository
import com.github.reapermaga.library.common.repository.SuspendedRepository
import com.github.reapermaga.library.common.repository.retrieveId
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

abstract class MongoRepository<T : Any, ID>(
    val database: MongoDatabase,
    collectionName: String,
    clazz: Class<T>,
) : Repository<T, ID>,
    SuspendedRepository<T, ID> {
    val collection: MongoCollection<T> = database.getCollection(collectionName, clazz)

    override fun persist(entity: T) =
        runBlocking {
            persistSuspended(entity)
        }

    override fun count(): Long =
        runBlocking {
            countSuspended()
        }

    override fun deleteAll() =
        runBlocking {
            deleteAllSuspended()
        }

    override fun findAll(): Collection<T> =
        runBlocking {
            findAllSuspended()
        }

    override fun save(entity: T) =
        runBlocking {
            saveSuspended(entity)
        }

    override fun findById(id: ID): T? =
        runBlocking {
            findByIdSuspended(id)
        }

    override fun existsById(id: ID): Boolean =
        runBlocking {
            existsByIdSuspended(id)
        }

    override fun deleteById(id: ID) =
        runBlocking {
            deleteByIdSuspended(id)
        }

    override suspend fun countSuspended(): Long = collection.countDocuments()

    override suspend fun deleteAllSuspended() {
        collection.deleteMany(Filters.empty())
    }

    override suspend fun findAllSuspended(): Collection<T> = collection.find(Filters.empty()).toList()

    override suspend fun saveSuspended(entity: T) {
        val id = retrieveId(entity)
        collection.replaceOne(Filters.eq("_id", id), entity)
    }

    override suspend fun persistSuspended(entity: T) {
        collection.insertOne(entity)
    }

    override suspend fun findByIdSuspended(id: ID): T? = collection.find(Filters.eq("_id", id)).firstOrNull()

    override suspend fun existsByIdSuspended(id: ID): Boolean = collection.countDocuments(Filters.eq("_id", id)) > 0

    override suspend fun deleteByIdSuspended(id: ID) {
        collection.deleteOne(Filters.eq("_id", id))
    }
}
