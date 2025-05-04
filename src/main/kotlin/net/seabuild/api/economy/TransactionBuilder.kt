package net.seabuild.api.economy

import java.util.*

fun buildTransaction(block: TransactionBuilder.() -> Unit): Transaction {
    return TransactionBuilder().apply(block).build()
}

class TransactionBuilder {
    var from: UUID? = null
    var to: UUID? = null
    var amount: Int? = null
    var currency: Currency = Currency.COINS
    var reason: String? = null
    var description: String? = null

    fun build(): Transaction {
        val from = requireNotNull(from) { "'from' must be set" }
        val to = requireNotNull(to) { "'to' must be set" }
        val amount = requireNotNull(amount) { "'amount' must be set" }
        val reason = requireNotNull(reason) { "'reason' must be set" }

        return TransactionImpl(from, to, amount, currency, reason, description)
    }
}

private data class TransactionImpl(
    override val from: UUID,
    override val to: UUID,
    override val amount: Int,
    override val currency: Currency,
    override val reason: String,
    override val description: String?
) : Transaction