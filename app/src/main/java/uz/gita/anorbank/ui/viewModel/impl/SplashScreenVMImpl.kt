package uz.gita.anorbank.ui.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.anorbank.ui.viewModel.SplashScreenVM

class SplashScreenVMImpl : ViewModel(), SplashScreenVM {
    override val goRegisterScreenLD = MutableLiveData<Int>()

    override fun goRegisterScreen(language: Int) {
        goRegisterScreenLD.value = language
    }
}