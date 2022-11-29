package com.eighteengray.baseproject

import android.os.Bundle
import android.widget.FrameLayout
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.view.FlutterMain
import io.flutter.view.FlutterView


class FlutterViewActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = FrameLayout(this)
        setContentView(layout)
        val flutterView = FlutterView(this)
        val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        flutterView.addFirstFrameListener {
        }
        var dartExecutor = flutterView.dartExecutor
        dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint(FlutterMain.findAppBundlePath(), "main"))
        layout.addView(flutterView, lp)
    }
}