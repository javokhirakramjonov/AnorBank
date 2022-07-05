package uz.gita.anorbank.ui.viewModel

import androidx.lifecycle.LiveData

interface SplashScreenVM {
    val goRegisterScreenLD: LiveData<Int>

    fun goRegisterScreen(language: Int)
}