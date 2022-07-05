package uz.gita.anorbank.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.anorbank.data.model.simple.RegisterModel

interface VerificationScreenVM {
    val inputCodeStateLD: LiveData<Boolean>
    val codeStateLD: LiveData<Boolean>
    val goBackLD: LiveData<Unit>
    val timerLD: LiveData<Int>
    val errorMessageLD: LiveData<String>
    val resendButtonStateLD: LiveData<Boolean>
    val sendCodeButtonLD: LiveData<Boolean>

    fun resend(user: RegisterModel)
    fun sendCode(code: String)
    fun goBack()
    fun startTime()
}