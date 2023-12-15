package com.example.tourmate.model

import com.google.firebase.Timestamp

data class ExpenseModel (

    var expenseId: String? = null,
    var title: String? = null,
    var amount: Int? = null,
    var timestamp: Timestamp = Timestamp.now()
)
