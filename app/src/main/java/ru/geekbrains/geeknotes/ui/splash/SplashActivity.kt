package ru.geekbrains.geeknotes.ui.splash

import android.os.Handler
import androidx.lifecycle.ViewModelProviders
import ru.geekbrains.geeknotes.ui.base.BaseActivity
import ru.geekbrains.geeknotes.ui.main.MainActivity


private const val START_DELAY = 1000L

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

    override val viewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override val layoutRes: Int = com.firebase.ui.auth.R.layout.fui_activity_register_email

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ viewModel.requestUser() }, START_DELAY)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf{ it }?.let {
            startMainActivity()
        }
    }


    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}
