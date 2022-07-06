package uz.gita.anorbank.ui.viewModel

import androidx.lifecycle.LiveData

interface PinCodeScreenVM {
    val goBackLD: LiveData<Unit>


    fun goBack()
}