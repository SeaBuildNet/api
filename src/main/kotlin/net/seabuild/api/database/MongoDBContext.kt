package net.seabuild.api.storage

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

interface MongoDBContext {
    var client: MongoClient
    var database: MongoDatabase
    var isConnected :Boolean
}