package net.seabuild.api.database

import javax.sql.DataSource

interface SqlDBContext {
    val dataSource: DataSource
    val isConnected: Boolean
}