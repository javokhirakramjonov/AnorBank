package uz.gita.anorbank.ui.screen

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.anorbank.R
import uz.gita.anorbank.databinding.ScreenSplashBinding

class SplashScreen : Fragment(R.layout.screen_splash) {

    private val binding by viewBinding(ScreenSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            title.apply {
                visibility = View.INVISIBLE
                animate().translationYBy(1000f)
            }
            txtChooseLanguage.apply {
                visibility = View.INVISIBLE
                animate().translationYBy(1000f)
            }
            languages.apply {
                visibility = View.INVISIBLE
                animate().translationYBy(1000f)
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
                                        animate().setDuration(500).translationYBy(-1000f).interpolator =
                                            DecelerateInterpolator()
                                    }
                                }.start()
                        }
                    }
            }
        }
        Unit
    }
}