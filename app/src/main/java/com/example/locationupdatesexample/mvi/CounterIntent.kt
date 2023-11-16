package com.example.locationupdatesexample.mvi

sealed class CounterIntent {
    object IncrementCounter: CounterIntent()
    object DecrementCounter: CounterIntent()
}
