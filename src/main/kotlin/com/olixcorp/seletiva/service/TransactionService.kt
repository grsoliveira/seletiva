package com.olixcorp.seletiva.service

import com.olixcorp.seletiva.model.Transaction
import com.olixcorp.seletiva.model.TransactionType
import org.springframework.stereotype.Service

@Service
class TransactionService {

    // Função pura para calcular o total de créditos
    fun calculateCreditTotal(transactions: List<Transaction>): Double =
        transactions.filter { it.type == TransactionType.CREDIT }
            .sumOf { it.amount }

    // Função pura para calcular o total de débitos
    fun calculateDebitTotal(transactions: List<Transaction>): Double =
        transactions.filter { it.type == TransactionType.DEBIT }
            .sumOf { it.amount }

    // Função pura para calcular o saldo final
    fun calculateBalance(transactions: List<Transaction>): Double =
        calculateCreditTotal(transactions) - calculateDebitTotal(transactions)
}