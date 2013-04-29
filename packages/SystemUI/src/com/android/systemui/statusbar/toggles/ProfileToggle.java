
package com.android.systemui.statusbar.toggles;

import android.app.AlertDialog;
import android.app.Profile;
import android.app.ProfileManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.app.Profile;
import android.app.ProfileManager;
import com.android.server.ProfileManagerService;

import com.android.systemui.R;

import java.util.UUID;

public class ProfileToggle extends BaseToggle {

    private Profile mChosenProfile;
    private ProfileManager mProfileManager;

    @Override
    protected void init(Context c, int style) {
        super.init(c, style);
        mProfileManager = (ProfileManager) mContext.getSystemService(Context.PROFILE_SERVICE);
        onProfileChanged();
        setIcon(R.drawable.ic_qs_profiles);
    }

    @Override
    public void onClick(View v) {
        createProfileDialog();
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

    // copied from com.android.internal.policy.impl.GlobalActions
    private void createProfileDialog() {
        final ProfileManager profileManager = (ProfileManager) mContext
                .getSystemService(Context.PROFILE_SERVICE);

        final Profile[] profiles = profileManager.getProfiles();
        UUID activeProfile = profileManager.getActiveProfile().getUuid();
        final CharSequence[] names = new CharSequence[profiles.length];

        int i = 0;
        int checkedItem = 0;

        for (Profile profile : profiles) {
            if (profile.getUuid().equals(activeProfile)) {
                checkedItem = i;
                mChosenProfile = profile;
            }
            names[i++] = profile.getName();
        }

        final AlertDialog.Builder ab = new AlertDialog.Builder(mContext);

        AlertDialog dialog = ab.setSingleChoiceItems(names, checkedItem,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which < 0)
                            return;
                        mChosenProfile = profiles[which];
                        profileManager.setActiveProfile(mChosenProfile.getUuid());
                        dialog.cancel();
                    }
                }).create();
        collapseStatusBar();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
        dialog.show();
    }

    private void onProfileChanged() {
        setLabel(mChosenProfile.getName());
        scheduleViewUpdate();
    }

} 


