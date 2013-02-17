
package com.android.systemui.statusbar.toggles;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.app.Profile;
import android.app.ProfileManager;
import com.android.server.ProfileManagerService;

import com.android.systemui.R;

public class ProfileToggle extends BaseToggle {

    private ProfileManager mProfileManager;

    @Override
    protected void init(Context c, int style) {
        super.init(c, style);
        mProfileManager = (ProfileManager) mContext.getSystemService(Context.PROFILE_SERVICE);
        setIcon(R.drawable.ic_qs_profiles);
        setLabel(mProfileManager.getActiveProfile().getName());
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Intent.ACTION_POWERMENU_PROFILE);
        mContext.sendBroadcast(intent);

        collapseStatusBar();
        dismissKeyguard();
    }

    @Override
    public boolean onLongClick(View v) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.android.settings", "com.android.settings.Settings$ProfilesSettingsActivity");
        intent.addCategory("android.intent.category.LAUNCHER");
        
        startActivity(intent);
        collapseStatusBar();
        dismissKeyguard();
        return super.onLongClick(v);
    }

} 


