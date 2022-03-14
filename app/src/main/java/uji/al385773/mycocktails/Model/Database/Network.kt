package uji.al385773.mycocktails.Model.Database

import android.content.Context

class Network private constructor(context: Context) {

    companion object : SingletonHolder<Network, Context>(::Network) {
    }
}
    