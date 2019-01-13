package org.nuvo.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object EventBus {

    private val publisher = PublishSubject.create<Any>()

    fun post(event:Any){
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

}