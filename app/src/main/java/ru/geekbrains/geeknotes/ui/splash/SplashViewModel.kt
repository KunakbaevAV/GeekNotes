package ru.geekbrains.geeknotes.ui.splash

import ru.geekbrains.geeknotes.data.Repository
import ru.geekbrains.geeknotes.data.errors.NoAuthException
import ru.geekbrains.geeknotes.ui.base.BaseViewModel


class SplashViewModel(private val repository: Repository = Repository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repository.getCurrentUser().observeForever {
            viewStateLiveData.value = if (it != null) {
                SplashViewState(isAuth = true)
            } else {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}
