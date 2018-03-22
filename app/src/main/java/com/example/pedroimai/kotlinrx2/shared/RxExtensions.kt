package com.example.pedroimai.kotlinrx2.shared

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

val ioScheduler: Scheduler get() = Schedulers.io()
val uiScheduler: Scheduler get() = AndroidSchedulers.mainThread()

operator fun CompositeDisposable.minusAssign(d: Disposable) { this.remove(d) }
operator fun CompositeDisposable.plusAssign(d: Disposable) { this.add(d) }