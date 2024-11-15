package com.olixcorp.seletiva.controller

import com.olixcorp.seletiva.model.Transaction
import com.olixcorp.seletiva.service.TransactionService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(private val transactionService: TransactionService) {

    @PostMapping("/credit-total")
    fun getCreditTotal(@RequestBody transactions: List<Transaction>): Double =
        transactionService.calculateCreditTotal(transactions)

    @PostMapping("/debit-total")
    fun getDebitTotal(@RequestBody transactions: List<Transaction>): Double =
        transactionService.calculateDebitTotal(transactions)

    @PostMapping("/balance")
    fun getBalance(@RequestBody transactions: List<Transaction>): Double =
        transactionService.calculateBalance(transactions)
}