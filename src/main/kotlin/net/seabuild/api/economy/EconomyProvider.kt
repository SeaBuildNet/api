package net.seabuild.api.economy

import net.seabuild.api.annon.FallbackImpl
import java.time.Duration
import java.util.*

@FallbackImpl(FallbackEconomyProvider::class)
interface EconomyProvider {

    fun getBalance(uuid: UUID, currency: Currency): Long

    fun transact(transaction: Transaction): Pair<Long?, Exception?>

    fun getTransactions(uuid: UUID): List<SubmittedTransaction>

    fun getTransactions(uuid: UUID, duration: Duration): List<SubmittedTransaction>

    fun getTransactions(uuid: UUID, duration: Long): List<SubmittedTransaction>

    fun getTransaction(id: Long): SubmittedTransaction?

    fun deleteTransaction(id: Long)

    suspend fun getBalanceSuspended(uuid: UUID, currency: Currency): Long

    suspend fun transactSuspended(transaction: Transaction): Pair<Long?, Exception?>

    suspend fun getTransactionsSuspended(uuid: UUID): List<SubmittedTransaction>

    suspend fun getTransactionsSuspended(uuid: UUID, duration: Duration): List<SubmittedTransaction>

    suspend fun getTransactionsSuspended(uuid: UUID, duration: Long): List<SubmittedTransaction>

    suspend fun getTransactionSuspended(id: Long): SubmittedTransaction?

    suspend fun deleteTransactionSuspended(id: Long)
}