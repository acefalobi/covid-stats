package com.aceinteract.topcoder.covidstats.domain.usecases

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
abstract class FlowUseCase<Params, T> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * Function which builds Flow instance based on given arguments
     * @param params initial use case arguments
     */
    abstract fun build(params: Params? = null): Flow<T>

    fun execute(params: Params? = null): Flow<T> {
        return this.build(params).flowOn(postExecutionThread.io)
    }

    suspend fun executeAndPerformAction(
        params: Params? = null,
        result: ActionResult.FetchResult<T>
    ) {
        execute(params).catch {
            Timber.e(it)
            result.onError(it)
        }.collect {
            result.onSuccess(it)
        }
    }

}