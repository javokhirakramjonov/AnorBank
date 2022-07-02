package uz.gita.anorbank.ui.viewModel

import androidx.lifecycle.LiveData
import uz.gita.anorbank.data.enums.RegisterErrorEnum
import uz.gita.anorbank.data.model.simple.RegisterModel

interface RegisterScreenVM {
    val errorMessageLD: LiveData<String>
    val continueButtonStateLD: LiveData<Boolean>
    val invalidFieldsLD: LiveData<List<RegisterErrorEnum>>

    fun checkData(user: RegisterModel, privacyPolicyState: Boolean)
    fun goVerification(user: RegisterModel)
}