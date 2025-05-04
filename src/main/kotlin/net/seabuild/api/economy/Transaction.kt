package net.seabuild.api.economy

import java.util.UUID

interface Transaction {
    val from: UUID
    val to: UUID
    val amount: Int
    val currency: Currency
    val reason: String
    val description: String?
}