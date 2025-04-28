package net.seabuild.api.user

import java.util.UUID

interface User {

    val uuid: UUID

    val name: String

    val knownNames: List<String>

    val properties: Map<String, Any>
}