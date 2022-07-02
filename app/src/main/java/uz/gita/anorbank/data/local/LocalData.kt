package uz.gita.anorbank.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalData @Inject constructor(@ApplicationContext context: Context) {

    private val local = context.getSharedPreferences("DATA", Context.MODE_PRIVATE)

    var accessToken: String
        get() = local.getString("ACCESS_TOKEN", "")!!
        set(value) = local.edit().putString("ACCESS_TOKEN", value).apply()
}