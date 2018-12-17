package com.igor.bykov.skyscannerapp.domain

import kotlinx.coroutines.Deferred

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 */
abstract class UseCase<T, in Params> {

  internal abstract suspend fun buildUseCaseObservable(params: Params): Deferred<T>
}
