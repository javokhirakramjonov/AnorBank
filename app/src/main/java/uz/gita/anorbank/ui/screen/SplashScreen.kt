package uz.gita.anorbank.ui.screen

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.anorbank.R
import uz.gita.anorbank.databinding.ScreenSplashBinding
import uz.gita.anorbank.ui.viewModel.SplashScreenVM
import uz.gita.anorbank.ui.viewModel.impl.SplashScreenVMImpl

class SplashScreen : Fragment(R.layout.screen_splash) {

    private val viewModel: SplashScreenVM by viewModels<SplashScreenVMImpl>()
    private val binding by viewBinding(ScreenSplashBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.goRegisterScreenLD.observe(this) {
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToRegisterScreen(it))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            title.apply {
                visibility = View.GONE
                animate().translationYBy(1000f)
            }
            txtChooseLanguage.apply {
                visibility = View.GONE
                animate().translationYBy(1000f)
            }
            languages.apply {
                visibility = View.GONE
                animate().alpha(0f)
            }
            delay(4000)
            title.apply {
                visibility = View.VISIBLE
                animate().setDuration(500).translationYBy(-1000f).setInterpolator(DecelerateInterpolator())
                    .withEndAction {
                        txtChooseLanguage.apply {
                            visibility = View.VISIBLE
                            animate().setDuration(300).translationYBy(-1000f)
                                .setInterpolator(DecelerateInterpolator())
                                .withEndAction {
                                    languages.apply {
                                        visibility = View.VISIBLE
                                        animate().setDuration(100).alpha(1f)
                                    }
                                }
                        }
                    }.start()
            }
        }

        buttonUZ.setOnClickListener {
            viewModel.goRegisterScreen(R.drawable.uz_flag)
        }
        buttonRU.setOnClickListener {
            viewModel.goRegisterScreen(R.drawable.ru_flag)
        }
        buttonUK.setOnClickListener {
            viewModel.goRegisterScreen(R.drawable.uk_flag)
        }
    }
}