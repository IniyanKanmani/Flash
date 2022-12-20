package com.iniyan.flash

import android.os.Bundle
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.hardware.camera2.CameraManager.TorchCallback


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            if (applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                val manager: CameraManager =
                    getSystemService(Context.CAMERA_SERVICE) as CameraManager

                var torchCallback: TorchCallback? = null

                torchCallback = object : TorchCallback() {
                    override fun onTorchModeChanged(cameraId: String, enabled: Boolean) {

                        super.onTorchModeChanged(cameraId, enabled)
                        manager.setTorchMode(cameraId, !enabled)
                        manager.unregisterTorchCallback(torchCallback!!)

                    }
                }

                manager.registerTorchCallback(torchCallback, null)

            }
        } catch (_: Exception) {
        }

        this.finish()
    }
}
