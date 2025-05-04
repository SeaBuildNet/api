package net.seabuild.api.economy
import net.seabuild.api.economy.*
import net.seabuild.api.economy.Currency
import java.lang.Exception
import java.time.Duration
import java.util.*

class FallbackEconomyProvider: EconomyProvider {

    val cached = mutableMapOf<Long, SubmittedTransaction>()

    override fun deleteTransaction(id: Long) {
        cached.remove(id)
    }

    override suspend fun deleteTransactionSuspended(id: Long) {
        deleteTransaction(id)
    }

    override fun getBalance(uuid: UUID, currency: Currency): Long {
        var balance = 0L
        for (transaction in cached.values) {
            if (transaction.from == uuid) {
                balance -= transaction.amount
            }
            if (transaction.to == uuid) {
                balance += transaction.amount
            }
        }
        return balance
    }

    override suspend fun getBalanceSuspended(uuid: UUID, currency: Currency): Long {
        return getBalance(uuid, currency)
    }

    override fun getTransaction(id: Long): SubmittedTransaction? {
        return cached.get(id)
    }

    override suspend fun getTransactionSuspended(id: Long): SubmittedTransaction? {
        return getTransaction(id)
    }

    override fun getTransactions(uuid: UUID): List<SubmittedTransaction> {
        return cached.values
            .filter { it.from == uuid || it.to == uuid }
    }

    override fun getTransactions(uuid: UUID, duration: Duration): List<SubmittedTransaction> {
        return getTransactions(uuid, duration.toMillis())
    }

    override fun getTransactions(uuid: UUID, duration: Long): List<SubmittedTransaction> {
        return cached.values
            .filter { it.from == uuid || it.to == uuid }
            .filter { it.time >= System.currentTimeMillis() - duration }
    }

    override suspend fun getTransactionsSuspended(uuid: UUID): List<SubmittedTransaction> {
        return getTransactions(uuid)
    }

    override suspend fun getTransactionsSuspended(uuid: UUID, duration: Duration): List<SubmittedTransaction> {
        return getTransactionsSuspended(uuid, duration)
    }

    override suspend fun getTransactionsSuspended(uuid: UUID, duration: Long): List<SubmittedTransaction> {
        return getTransactionsSuspended(uuid, duration)
    }

    override fun transact(transaction: Transaction): Pair<Long?, Exception?> {
        val submittedTransaction = SubmittedTransactionImpl(id(), System.currentTimeMillis(), transaction)
        cached.put(submittedTransaction.transactionId, submittedTransaction)
        return submittedTransaction.transactionId to null
    }

    override suspend fun transactSuspended(transaction: Transaction): Pair<Long?, Exception?> {
        return transact(transaction)
    }

    private fun id(): Long {
        var id = 0L;
        while (getTransaction(id) != null) {
            id++;
        }
        return id
    }

    private data class SubmittedTransactionImpl(
        override val transactionId: Long,
        override val time: Long,
        val transaction: Transaction
    ) : SubmittedTransaction {
        override val amount = transaction.amount
        override val currency = transaction.currency
        override val description = transaction.description
        override val from = transaction.from
        override val reason = transaction.reason
        override val to = transaction.to
    }

}