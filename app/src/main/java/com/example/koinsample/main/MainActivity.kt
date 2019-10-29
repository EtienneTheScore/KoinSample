package com.example.koinsample.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.koinsample.R
import com.example.koinsample.databinding.ActivityMainBinding
import com.example.koinsample.di.Log
import com.example.koinsample.koinLogger
import com.example.koinsample.main.view.LogsAdapter
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private val splitInstallManager: SplitInstallManager by inject()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        koinLogger.log(Log.Action("[MainActivity] ON CREATE"))
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.reload = ::reloadActivity
        viewModel.loadAndLaunchUserDetails = ::loadAndLaunchUserDetails
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        configureRecyclerView()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        koinLogger.log(Log.Action("ON CONFIGURATION CHANGED"))
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.reload = null
        viewModel.loadAndLaunchUserDetails = null
    }

    private fun configureRecyclerView() {
        binding.logsRv.adapter = LogsAdapter()
    }

    private fun reloadActivity() {
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun loadAndLaunchUserDetails() {
        Timber.i("loadAndLaunchUserDetails()")

        val moduleName = "userdetails"
        if (splitInstallManager.installedModules.contains(moduleName)) {
            launchUserDetails()
            return
        }

        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener { Timber.i("Loading %s", moduleName) }
            .addOnFailureListener { Timber.e(it, "Error loading %s", moduleName) }
            .addOnCompleteListener {
                Timber.i("Module %s installed", moduleName)
                launchUserDetails()
            }
    }

    private fun launchUserDetails() {
        Timber.i("launchUserDetails()")

        val intent = Intent(Intent.ACTION_VIEW).setClassName(
            packageName,
            "com.example.userdetails.UserDetailsActivity"
        )
        startActivity(intent)
    }
}
