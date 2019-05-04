package com.dino.library.ext

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

infix operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}