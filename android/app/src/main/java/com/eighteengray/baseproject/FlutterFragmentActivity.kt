package com.eighteengray.baseproject

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentActivity
import io.flutter.embedding.android.FlutterFragment


class FlutterFragmentActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.flutter_layout)
        val fragment = FlutterFragment.withNewEngine().initialRoute("home").build<FlutterFragment>()
        supportFragmentManager.beginTransaction().add(R.id.flutter_container, fragment).commit()
    }

}