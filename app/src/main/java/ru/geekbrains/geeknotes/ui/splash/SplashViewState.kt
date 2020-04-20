package ru.geekbrains.geeknotes.ui.splash

import ru.geekbrains.geeknotes.ui.base.BaseViewState

class SplashViewState(isAuth: Boolean? = null, error: Throwable? = null) :
    BaseViewState<Boolean?>(isAuth, error)
