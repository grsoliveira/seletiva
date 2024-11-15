package com.olixcorp.seletiva.model

data class Transaction(
    val id: Long,
    val amount: Double,
    val type: TransactionType
)

enum class TransactionType {
    CREDIT, DEBIT
}