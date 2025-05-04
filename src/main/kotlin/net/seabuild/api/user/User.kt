package net.seabuild.api.user

import java.util.UUID

interface User {

    val uuid: UUID

    var name: String

    val knownNames: MutableList<String>

    val properties: MutableMap<String, Any>
}