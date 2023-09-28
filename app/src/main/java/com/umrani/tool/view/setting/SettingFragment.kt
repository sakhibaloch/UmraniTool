package com.umrani.tool.view.setting

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.DocumentsContract
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.preference.DropDownPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SeekBarPreference
import androidx.preference.SwitchPreferenceCompat
import com.umrani.tool.BlackBoxCore
import com.umrani.tool.R
import com.umrani.tool.app.AppManager
import com.umrani.tool.util.toast
import com.umrani.tool.view.fake.FakeManagerActivity
import com.umrani.tool.view.gms.GmsManagerActivity
import com.umrani.tool.view.main.LoginActivity
import com.umrani.tool.view.xp.XpActivity


/**
 *
 * @Description:
 * @Author: wukaicheng
 * @CreateDate: 2021/5/6 22:13
 */
class SettingFragment : PreferenceFragmentCompat() {

    private val NEW_FOLDER_REQUEST_CODE = 1

    private var mUri: Uri? = null

    private lateinit var xpEnable: SwitchPreferenceCompat
    private lateinit var xpModule: Preference
    private lateinit var fakeLocationPreference: Preference
    private lateinit var updatetoolPreference: Preference
    private lateinit var crashfixpubgPreference: Preference
    private lateinit var deletegustpubgPreference: Preference
    private lateinit var lightseekbar: SeekBarPreference
    private val WRITE_SETTINGS_REQUEST_CODE = 100
    private lateinit var applicationContext: Context

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)

        xpEnable = findPreference("xp_enable")!!
        xpEnable.isChecked = BlackBoxCore.get().isXPEnable

        xpEnable.setOnPreferenceChangeListener { _, newValue ->
            BlackBoxCore.get().isXPEnable = (newValue == true)
            true
        }
        //xp模块跳转
        xpModule = findPreference("xp_module")!!
        xpModule.setOnPreferenceClickListener {
            val intent = Intent(requireActivity(), XpActivity::class.java)
            requireContext().startActivity(intent)
            true
        }
        initGms()

/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                openDocumentTree()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    NEW_FOLDER_REQUEST_CODE
                )
            }
        } else {
            // Permission not required for versions prior to Marshmallow
            openDocumentTree()
        }*/


        invalidHideState {
            val xpHidePreference: Preference = (findPreference("xp_hide")!!)
            val hideXposed = AppManager.mBlackBoxLoader.hideXposed()
            xpHidePreference.setDefaultValue(hideXposed)
            xpHidePreference
        }

        invalidHideState {
            val rootHidePreference: Preference = (findPreference("root_hide")!!)
            val hideRoot = AppManager.mBlackBoxLoader.hideRoot()
            rootHidePreference.setDefaultValue(hideRoot)
            rootHidePreference
        }

        invalidHideState {
            val daemonPreference: Preference = (findPreference("daemon_enable")!!)
            val mDaemonEnable = AppManager.mBlackBoxLoader.daemonEnable()
            daemonPreference.setDefaultValue(mDaemonEnable)
            daemonPreference
        }

        fakeLocationPreference = findPreference("fake_location")!!
        fakeLocationPreference.setOnPreferenceClickListener {
            val intent = Intent(context, FakeManagerActivity::class.java)
            intent.putExtra("userID", 0)
            startActivity(intent)
            true
        }

        updatetoolPreference = findPreference("update_tool")!!
        updatetoolPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.data = Uri.parse("https://umranistore.blogspot.com/")
            activity!!.startActivity(intent)
            Toast.makeText(
                activity,
                "\uD83D\uDE0EUPDATE UMRANI TOOL\uD83D\uDE0E",
                Toast.LENGTH_SHORT
            ).show()
            true
        }

        crashfixpubgPreference = findPreference("crash_fix_pubg")!!
        crashfixpubgPreference.setOnPreferenceClickListener {
            MyFileHelper.deleteFile("/storage/emulated/0/Android/data/com.tencent.ig/files/ProgramBinaryCache");

            val TIME_SPLASH = 1500
            Handler().postDelayed({
                MyFileHelper.writeFile("/storage/emulated/0/Android/data/com.tencent.ig/files/ProgramBinaryCache", "ProgramBinaryCache");
            }, TIME_SPLASH.toLong())
           //
            Toast.makeText(
                activity,
                "PUBG CRASH FIXED",
                Toast.LENGTH_SHORT
            ).show()
            true
        }

        deletegustpubgPreference = findPreference("delete_gust_pubg")!!
        deletegustpubgPreference.setOnPreferenceClickListener {
            Toast.makeText(
                activity,
                "PUBG GUST DELETED",
                Toast.LENGTH_SHORT
            ).show()
            true
        }


        val lightseekbar = findPreference<SeekBarPreference>("light_seekbar")
        lightseekbar?.setOnPreferenceChangeListener { preference: Preference, newValue: Any ->
            val brightness = newValue as Int
            setBrightness(brightness)
            true
        }
        if (!hasWriteSettingsPermission()) {
            requestWriteSettingsPermission()
        }

        val autoLightPreference = findPreference<SwitchPreferenceCompat>("auto_light")
        autoLightPreference?.setOnPreferenceChangeListener { preference, newValue ->
            val isAutoLightEnabled = newValue as Boolean
            if (Settings.System.canWrite(context)) {
                showToast("Brightness Auto Disabled")
                // Auto brightness setting can be modified
                Settings.System.putInt(
                    context?.contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
                )
            } else {
                showToast("Brightness Auto Enabled")
                // Open the system settings screen for the user to grant the WRITE_SETTINGS permission
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:" + context?.packageName)
                context?.startActivity(intent)
            }
            true // Return true to persist the new preference value
        }


        val BlockNotifcation = findPreference<SwitchPreferenceCompat>("Block_Notifcation")
        BlockNotifcation?.setOnPreferenceChangeListener { preference, newValue ->
            val BlockNotifcationEnabled = newValue as Boolean
            if (BlockNotifcationEnabled) {
                //enableDoNotDisturbMode()
                showToast("Auto Calls Reject Enabled")
            } else {
               // disableDoNotDisturbMode()
                showToast("Auto Calls Reject Disabled")

            }
            true // Return true to persist the new preference value
        }


    //    val soundModePreference: DropDownPreference = findPreference("sound_mode")!! as DropDownPreference
        val soundModePreference = findPreference<DropDownPreference>("sound_mode")
        if (soundModePreference != null) { soundModePreference.onPreferenceChangeListener =
                Preference.OnPreferenceChangeListener { preference, newValue ->
                    val selectedValue = newValue.toString()
                    // Get the AudioManager instance
                    val audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                    // Set the appropriate audio mode based on the selected sound mode
                    when (selectedValue) {
                        "silent" -> {
                            try {
                                audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
                            } catch (e: SecurityException) {
                                // Handle the exception (e.g., show an error message, log the error, etc.)
                                e.printStackTrace()
                            }
                        }
                        "vibration" -> audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
                        "normal" -> audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
                    }
                    // Display a toast message with the selected sound mode
                    showToast("Selected Sound Mode: $selectedValue")
                    true
                }
        }

    }
/*
  private fun executeShellCommand(command: String) {
        try {
            val process = Runtime.getRuntime().exec(command)
            process.waitFor()

            val output = BufferedReader(InputStreamReader(process.inputStream)).use {
                it.readText()
            }

            val error = BufferedReader(InputStreamReader(process.errorStream)).use {
                it.readText()
            }

            // Handle the output and error as desired
            Log.d("SettingFragment", "Shell command output:\n$output")
            Log.e("SettingFragment", "Shell command errors:\n$error")
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle any exceptions that occur
        }
    }

*/


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun hasWriteSettingsPermission(): Boolean {
        val context = preferenceManager.context
        return Settings.System.canWrite(context)
    }

    private fun requestWriteSettingsPermission() {
        val context = preferenceManager.context
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        intent.data = Uri.parse("package:${context.packageName}")
        startActivityForResult(intent, WRITE_SETTINGS_REQUEST_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == WRITE_SETTINGS_REQUEST_CODE) {
            if (hasWriteSettingsPermission()) {
                // The WRITE_SETTINGS permission has been granted
                showToast("permission granted!")
                // Perform your brightness adjustment or other operations here
            } else {
                // The user did not grant the WRITE_SETTINGS permission
                // Handle accordingly (e.g., display a message or fallback behavior)
            }
        }
    }


private fun setBrightness(brightness: Int) {
        val brightnessValue = brightness.toFloat() / 255

        val window = activity?.window
        val layoutParams = window?.attributes
        layoutParams?.screenBrightness = brightnessValue
        window?.attributes = layoutParams

        // Store the brightness value in system settings
        val contentResolver = requireContext().contentResolver
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightness)
    }

    private fun initGms() {
        val gmsManagerPreference: Preference = (findPreference("gms_manager")!!)

        if (BlackBoxCore.get().isSupportGms) {

            gmsManagerPreference.setOnPreferenceClickListener {
                GmsManagerActivity.start(requireContext())
                true
            }
        } else {
            gmsManagerPreference.summary = getString(R.string.no_gms)
            gmsManagerPreference.isEnabled = false
        }
    }



    private fun invalidHideState(block: () -> Preference) {
        val pref = block()
        pref.setOnPreferenceChangeListener { preference, newValue ->
            val tmpHide = (newValue == true)
            when (preference.key) {
                "xp_hide" -> {
                    AppManager.mBlackBoxLoader.invalidHideXposed(tmpHide)
                }

                "root_hide" -> {

                    AppManager.mBlackBoxLoader.invalidHideRoot(tmpHide)
                }

                "daemon_enable" -> {
                    AppManager.mBlackBoxLoader.invalidDaemonEnable(tmpHide)
                }
            }

            toast(R.string.restart_module)
            return@setOnPreferenceChangeListener true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == NEW_FOLDER_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openDocumentTree()
            } else {
               // Toast.makeText(this, "Please Enable Permissions", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openDocumentTree() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        mUri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3AAndroid%2Fdata")
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, mUri)
        startActivityForResult(intent, NEW_FOLDER_REQUEST_CODE)
    }


}