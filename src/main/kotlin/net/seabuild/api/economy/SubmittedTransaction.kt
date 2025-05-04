package net.seabuild.api.economy

interface SubmittedTransaction: Transaction {
    val transactionId: Long
    val time: Long
}