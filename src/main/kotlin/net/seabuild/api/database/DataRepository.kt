package net.seabuild.api.storage

import com.github.reapermaga.library.common.repository.Repository
import com.github.reapermaga.library.common.repository.SuspendedLocalRepository
import com.github.reapermaga.library.common.repository.SuspendedRepository
import com.mongodb.kotlin.client.coroutine.MongoDatabase

interface DataRepository<K, V> :
        Repository<V, K>,
    SuspendedRepository<V, K>

class LocalRepository<K, V: Any> :
    SuspendedLocalRepository<V, K>(),
    DataRepository<K, V>

class MongoDBRepository<K, V: Any>(
    database: MongoDatabase,
    collection: String,
    clazz: Class<V>
) : MongoRepository<V, K>(database, collection, clazz),
    DataRepository<K, V>

object DataRepositoryFactory {

    fun <K, V: Any> getDataRepository(collection: String, context: MongoDBContext, clazz: Class<V>): DataRepository<K, V> {
        return when(context.isConnected) {
            true -> MongoDBRepository(context.database, collection, clazz)
            false -> LocalRepository()
        }
    }

    inline fun <K, reified V: Any> getDataRepository(collection: String, context: MongoDBContext): DataRepository<K, V> {
        return when(context.isConnected) {
            true -> MongoDBRepository(context.database, collection, V::class.java)
            false -> LocalRepository()
        }
    }

}