package com.doolsjka.kingfile.data
import android.content.Context
import com.doolsjka.kingfile.KingApp

class KingPrefs(ctx: Context = KingApp.instance) {
    private val p = ctx.getSharedPreferences(KingApp.PREFS, Context.MODE_PRIVATE)
    var theme: String get() = p.getString("theme","gold")!!; set(v) = p.edit().putString("theme",v).apply()
    var welcomed: Boolean get() = p.getBoolean("welcomed",false); set(v) = p.edit().putBoolean("welcomed",v).apply()
    var adBlock: Boolean get() = p.getBoolean("adblock",true); set(v) = p.edit().putBoolean("adblock",v).apply()
    var rootEnabled: Boolean get() = p.getBoolean("root",false); set(v) = p.edit().putBoolean("root",v).apply()
    var downloadPath: String get() = p.getString("dl_path","/sdcard/Download/KingFile")!!; set(v) = p.edit().putString("dl_path",v).apply()
}
