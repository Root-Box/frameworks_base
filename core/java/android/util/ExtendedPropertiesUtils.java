/*
 * Copyright (C) 2012 ParanoidAndroid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.util;

<<<<<<< HEAD
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.ActivityManager.RunningAppProcessInfo;
=======
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.content.ContentResolver;
>>>>>>> upstream/jb-mr1
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
<<<<<<< HEAD
import android.view.Display;

public class ExtendedPropertiesUtils {

=======
import android.content.res.Resources;
import android.content.res.CompatibilityInfo;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Math;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ExtendedPropertiesUtils {
 
>>>>>>> upstream/jb-mr1
    private static final String TAG = "ExtendedPropertiesUtils";

    /**
     * Public variables
     */
<<<<<<< HEAD
    public static final String BEERBONG_MAINCONF = "properties.conf";
    public static final String BEERBONG_BACKUPCONF = "backup.conf";
    public static final String BEERBONG_PROPERTIES = "/system/etc/beerbong/" + BEERBONG_MAINCONF;
    public static final String BEERBONG_DIR = "/system/etc/beerbong/";
    public static final String BEERBONG_PREFIX = "%";
    public static final String BEERBONG_SEPARATOR = ".";
    public static final String BEERBONG_STRING_DELIMITER = "\\|";
    public static final String BEERBONG_DPI_SUFFIX = ".dpi";
    public static final String BEERBONG_LAYOUT_SUFFIX = ".layout";
    public static final String BEERBONG_DENSITY_SUFFIX = ".den";
    public static final String BEERBONG_SCALEDDENSITY_SUFFIX = ".sden";
=======
    public static final String PARANOID_MAINCONF = "properties.conf";
    public static final String PARANOID_BACKUPCONF = "backup.conf";
    public static final String PARANOID_PROPERTIES = "/system/etc/paranoid/" + PARANOID_MAINCONF;
    public static final String PARANOID_DIR = "/system/etc/paranoid/";
    public static final String PARANOID_PREFIX = "%";
    public static final String PARANOID_SEPARATOR = ".";
    public static final String PARANOID_STRING_DELIMITER = "\\|";
    public static final String PARANOID_DPI_SUFFIX = ".dpi";
    public static final String PARANOID_LAYOUT_SUFFIX = ".layout";
    public static final String PARANOID_FORCE_SUFFIX = ".force";
    public static final String PARANOID_LARGE_SUFFIX = ".large";
    public static final String PARANOID_CHECK_SUFFIX = ".version";
    public static final String PARANOID_DENSITY_SUFFIX = ".den";
    public static final String PARANOID_SCALEDDENSITY_SUFFIX = ".sden";
    public static final String PARANOID_EXPAND_SUFFIX = ".expand";
    public static final String PARANOID_LANDSC_SUFFIX = ".landsc";

    // Color definitions
    public static final String PARANOID_COLORS_SUFFIX = ".colors";
    public static final String PARANOID_MANCOL_SUFFIX = ".mancol";
    public static final int PARANOID_COLORS_COUNT = 5;
    public static final String[] PARANOID_COLORS_SETTINGS = {Settings.System.NAV_BAR_COLOR,
        Settings.System.NAV_BUTTON_COLOR, Settings.System.NAV_GLOW_COLOR,
        Settings.System.STATUS_BAR_COLOR, Settings.System.STATUS_ICON_COLOR};
    public static final int[] PARANOID_COLORCODES_DEFAULTS = 
        {0xFF000000, 0xB2FFFFFF, 0xFFFFFFFF, 0xFF000000, 0xFF33B5E5};
    public static final int PARANOID_COLORS_NAVBAR = 0;
    public static final int PARANOID_COLORS_NAVBUTTON = 1;
    public static final int PARANOID_COLORS_NAVGLOW = 2;
    public static final int PARANOID_COLORS_STATBAR = 3;
    public static final int PARANOID_COLORS_STATICONS = 4;
>>>>>>> upstream/jb-mr1

    public static HashMap<String, String> mPropertyMap = new HashMap<String, String>();
    public static ActivityThread mMainThread;
    public static Context mContext;
<<<<<<< HEAD
    public static PackageManager mPackageManager;
    public static Display mDisplay;
    public static List<PackageInfo> mPackageList;

    public static BeerbongAppInfo mGlobalHook = new BeerbongAppInfo();
    public BeerbongAppInfo mLocalHook = new BeerbongAppInfo();
=======
    public static PackageManager mPackageManager;    
    public static Display mDisplay;
    public static List<PackageInfo> mPackageList;

    public static ParanoidAppInfo mGlobalHook = new ParanoidAppInfo();
    public ParanoidAppInfo mLocalHook = new ParanoidAppInfo();
>>>>>>> upstream/jb-mr1

    public static boolean sIsHybridModeEnabled;

    public static int mRomLcdDensity = DisplayMetrics.DENSITY_DEFAULT;

    // Native methods
    public static native String readFile(String s);
<<<<<<< HEAD

    /**
     * Contains all the details for an application
     */
    public static class BeerbongAppInfo {

=======
    
    /**
     * Contains all the details for an application
     */
    public static class ParanoidAppInfo {
>>>>>>> upstream/jb-mr1
        public String name = "";
        public String path = "";
        public boolean active;
        public int pid;
        public ApplicationInfo info;
        public int dpi;
        public int layout;
<<<<<<< HEAD
        public int firstRun;
        public float scaledDensity;
        public float density;
=======
        public int force;
        public int large;
        public int expand;
        public int landsc;
        public int mancol;
        public int firstRun;
        public float scaledDensity;
        public float density;
        public String[] colors = new String[PARANOID_COLORS_COUNT];
>>>>>>> upstream/jb-mr1
    }

    /**
     * Enum interface to allow different override modes
     */
    public static enum OverrideMode {
        ExtendedProperties, AppInfo, FullName, FullNameExclude, PackageName
    }

    /**
<<<<<<< HEAD
     * Set app configuration for the input argument <code>info</code>. This is
     * done by fetching properties.conf or our stored {@link HashMap}.
     * 
     * @param info
     *            instance containing app details
     */
    public static void setAppConfiguration(BeerbongAppInfo info) {
        if (sIsHybridModeEnabled) {
            // Load default values to be used in case that property is
            // missing from configuration.
            boolean isSystemApp = info.path.contains("system/app");
            int defaultDpi = getActualProperty(BEERBONG_PREFIX + (isSystemApp ?
                    "system_default_dpi" : (info.path.length() == 0 ? "0" : "user_default_dpi")));
            int defaultLayout = getActualProperty(BEERBONG_PREFIX + "user_default_layout");

            if (defaultLayout == 0) {
                defaultLayout = getActualProperty("com.android.systemui.layout");
            }
            if (defaultDpi == 0) {
                defaultDpi = getActualProperty("com.android.systemui.dpi");
            }

            // Layout fetching.
            info.layout = Integer.parseInt(getProperty(info.name + BEERBONG_LAYOUT_SUFFIX, String.valueOf(defaultLayout)));

            // DPI fetching.
            info.dpi = Integer.parseInt(getProperty(info.name + BEERBONG_DPI_SUFFIX, String.valueOf(defaultDpi)));
            if (info.dpi == 0) {
                info.dpi = defaultDpi;
            }

            // Extra density fetching.
            info.density = Float.parseFloat(getProperty(info.name + BEERBONG_DENSITY_SUFFIX));
            info.scaledDensity = Float.parseFloat(getProperty(info.name + BEERBONG_SCALEDDENSITY_SUFFIX));
=======
     * Set app configuration for the input argument <code>info</code>.
     * This is done by fetching properties.conf or our stored {@link HashMap}.
     *
     * @param  info  instance containing app details
     */
    public static void setAppConfiguration(ParanoidAppInfo info) {
        if(sIsHybridModeEnabled){
            // Load default values to be used in case that property is 
            // missing from configuration.
            boolean isSystemApp = info.path.contains("system/app");
            int defaultDpi = Integer.parseInt(getProperty(PARANOID_PREFIX + (isSystemApp ? 
                "system_default_dpi" : (info.path.length() == 0 ? "0" : "user_default_dpi"))));
            int defaultLayout = Integer.parseInt(getProperty(PARANOID_PREFIX + (isSystemApp ? 
                "system_default_layout" : (info.path.length() == 0 ? "0" : "user_default_layout"))));

            // Layout fetching.
            info.layout = Integer.parseInt(getProperty(info.name + PARANOID_LAYOUT_SUFFIX, String.valueOf(defaultLayout)));

            // DPI fetching.
            info.dpi = Integer.parseInt(getProperty(info.name + PARANOID_DPI_SUFFIX, String.valueOf(defaultDpi)));

            // Extra density fetching.
            info.density = Float.parseFloat(getProperty(info.name + PARANOID_DENSITY_SUFFIX));
            info.scaledDensity = Float.parseFloat(getProperty(info.name + PARANOID_SCALEDDENSITY_SUFFIX));
>>>>>>> upstream/jb-mr1

            // In case that densities aren't determined in previous step
            // we calculate it by dividing DPI by default density (160).
            if (info.dpi != 0) {
                info.density = info.density == 0 ? info.dpi / (float) DisplayMetrics.DENSITY_DEFAULT : info.density;
<<<<<<< HEAD
                info.scaledDensity = info.scaledDensity == 0 ? info.dpi / (float) DisplayMetrics.DENSITY_DEFAULT
                        : info.scaledDensity;
            }

            info.firstRun = 0;

=======
                info.scaledDensity = info.scaledDensity == 0 ? info.dpi / (float) DisplayMetrics.DENSITY_DEFAULT : info.scaledDensity;
            }

            // Extra parameters. Force allows apps to penetrate their hosts, 
            // while large appends SCREENLAYOUT_SIZE_XLARGE mask that makes 
            // layout matching to assign bigger containers.
            info.force = Integer.parseInt(getProperty(info.name + PARANOID_FORCE_SUFFIX));
            info.large = Integer.parseInt(getProperty(info.name + PARANOID_LARGE_SUFFIX));
            info.expand = Integer.parseInt(getProperty(info.name + PARANOID_EXPAND_SUFFIX));
            info.landsc = Integer.parseInt(getProperty(info.name + PARANOID_LANDSC_SUFFIX));
            info.mancol = Integer.parseInt(getProperty(info.name + PARANOID_MANCOL_SUFFIX));
            info.firstRun = 0;

            // Color parameters
            String[] colors = getProperty(info.name +
                    PARANOID_COLORS_SUFFIX).split(PARANOID_STRING_DELIMITER);
            for(int i=0; i < PARANOID_COLORS_COUNT; i++) {
                    info.colors[i] = colors.length == PARANOID_COLORS_COUNT ?
                            colors[i].toUpperCase() : "";
            }

>>>>>>> upstream/jb-mr1
            // If everything went nice, stop parsing.
            info.active = true;
        }
    }

    /**
<<<<<<< HEAD
     * Overrides current hook with input parameter <code>mode</code>, wich is an
     * enum interface that stores basic override possibilities.
     * 
     * @param input
     *            object to be overriden
     * @param mode
     *            enum interface
=======
     * Overrides current hook with input parameter <code>mode</code>, wich
     * is an enum interface that stores basic override possibilities.
     *
     * @param  input  object to be overriden
     * @param  mode  enum interface
>>>>>>> upstream/jb-mr1
     */
    public void overrideHook(Object input, OverrideMode mode) {
        if (isInitialized() && input != null) {

            ApplicationInfo tempInfo;
            ExtendedPropertiesUtils tempProps;

            switch (mode) {
                case ExtendedProperties:
                    tempProps = (ExtendedPropertiesUtils) input;
                    if (tempProps.mLocalHook.active) {
                        mLocalHook.active = tempProps.mLocalHook.active;
                        mLocalHook.pid = tempProps.mLocalHook.pid;
                        mLocalHook.info = tempProps.mLocalHook.info;
                        mLocalHook.name = tempProps.mLocalHook.name;
                        mLocalHook.path = tempProps.mLocalHook.path;
<<<<<<< HEAD
                        mLocalHook.dpi = tempProps.mLocalHook.dpi;
                        mLocalHook.layout = tempProps.mLocalHook.layout;
=======
                        mLocalHook.layout = tempProps.mLocalHook.layout;
                        mLocalHook.dpi = tempProps.mLocalHook.dpi;
                        mLocalHook.force = tempProps.mLocalHook.force;
                        mLocalHook.large = tempProps.mLocalHook.large;
>>>>>>> upstream/jb-mr1
                        mLocalHook.scaledDensity = tempProps.mLocalHook.scaledDensity;
                        mLocalHook.density = tempProps.mLocalHook.density;
                    }
                    return;
                case AppInfo:
<<<<<<< HEAD
                    mLocalHook.info = (ApplicationInfo) input;
=======
                    mLocalHook.info = (ApplicationInfo)input;
>>>>>>> upstream/jb-mr1
                    break;
                case FullName:
                    mLocalHook.info = getAppInfoFromPath((String) input);
                    break;
                case FullNameExclude:
                    tempInfo = getAppInfoFromPath((String) input);
<<<<<<< HEAD
                    if (tempInfo != null && !isHooked()) {
=======
                    if (tempInfo != null && (!isHooked() || getProperty(tempInfo.packageName + PARANOID_FORCE_SUFFIX).equals("1"))) {
>>>>>>> upstream/jb-mr1
                        mLocalHook.info = tempInfo;
                    }
                    break;
                case PackageName:
                    mLocalHook.info = getAppInfoFromPackageName((String) input);
                    break;
            }

            if (mLocalHook.info != null) {
                mLocalHook.pid = android.os.Process.myPid();
                mLocalHook.name = mLocalHook.info.packageName;
<<<<<<< HEAD
                mLocalHook.path = mLocalHook.info.sourceDir.substring(0,
=======
                mLocalHook.path = mLocalHook.info.sourceDir.substring(0, 
>>>>>>> upstream/jb-mr1
                        mLocalHook.info.sourceDir.lastIndexOf("/"));

                setAppConfiguration(mLocalHook);
            }
        }
    }

    /**
<<<<<<< HEAD
     * This methods are used to retrieve specific information for a hook.
=======
     * This methods are used to retrieve specific information for a hook. 
>>>>>>> upstream/jb-mr1
     */
    public static boolean isInitialized() {
        return (mContext != null);
    }
<<<<<<< HEAD

    public static boolean isHooked() {
        return (isInitialized() && !mGlobalHook.name.equals("android") && !mGlobalHook.name.equals(""));
    }

    public boolean getActive() {
        return mLocalHook.active ? mLocalHook.active : mGlobalHook.active;
    }

    public int getPid() {
        return mLocalHook.active ? mLocalHook.pid : mGlobalHook.pid;
    }

    public ApplicationInfo getInfo() {
        return mLocalHook.active ? mLocalHook.info : mGlobalHook.info;
    }

    public String getName() {
        return mLocalHook.active ? mLocalHook.name : mGlobalHook.name;
    }

    public String getPath() {
        return mLocalHook.active ? mLocalHook.path : mGlobalHook.path;
    }

    public int getDpi() {
        return mLocalHook.active ? mLocalHook.dpi : mGlobalHook.dpi;
    }

    public int getLayout() {
        return mLocalHook.active ? mLocalHook.layout : mGlobalHook.layout;
    }

    public float getScaledDensity() {
        return mLocalHook.active ? mLocalHook.scaledDensity : mGlobalHook.scaledDensity;
    }

    public float getDensity() {
        return mLocalHook.active ? mLocalHook.density : mGlobalHook.density;
    }

    /**
     * Returns whether if device is running hybrid mode
     * 
=======
    public static boolean isHooked() {
        return (isInitialized() && !mGlobalHook.name.equals("android") && !mGlobalHook.name.equals(""));
    }
    public boolean getActive() {
        return mLocalHook.active ? mLocalHook.active : mGlobalHook.active;
    }
    public int getPid() {
        return mLocalHook.active ? mLocalHook.pid : mGlobalHook.pid;
    }
    public ApplicationInfo getInfo() {
        return mLocalHook.active ? mLocalHook.info : mGlobalHook.info;
    }
    public String getName() {
        return mLocalHook.active ? mLocalHook.name : mGlobalHook.name;
    }
    public String getPath() {
        return mLocalHook.active ? mLocalHook.path : mGlobalHook.path;
    }
    public int getLayout() {
        return mLocalHook.active ? mLocalHook.layout : mGlobalHook.layout;
    }
    public int getDpi() {
        return mLocalHook.active ? mLocalHook.dpi : mGlobalHook.dpi;
    }
    public float getScaledDensity() { 
        return mLocalHook.active ? mLocalHook.scaledDensity : mGlobalHook.scaledDensity;
    }
    public boolean getForce() {
        return (mLocalHook.active ? mLocalHook.force : mGlobalHook.force) == 1;
    }
    public boolean getLarge() {
        return (mLocalHook.active ? mLocalHook.large : mGlobalHook.large) == 1;
    }
    public float getDensity() {
        return mLocalHook.active ? mLocalHook.density : mGlobalHook.density;
    }
    public boolean getLandscape() {
        return (mLocalHook.active ? mLocalHook.landsc : mGlobalHook.landsc) == 1;
    }

    /**
     * Returns whether if device is running hybrid mode
     *
>>>>>>> upstream/jb-mr1
     * @return hybrid mode enabled
     */
    public static boolean isHybridModeEnabled() {
        return sIsHybridModeEnabled;
    }

    /**
     * Returns whether if device is on tablet UI or not
<<<<<<< HEAD
     * 
     * @return device is tablet
     */
    public static boolean isTablet() {
        int dpi;
        String prop = readProperty("com.android.systemui.dpi", "0");
        if (isParsableToInt(prop)) {
            dpi = Integer.parseInt(prop);
        } else {
            dpi = getActualProperty(prop);
        }
        return dpi < 213;
    }

    /**
     * Returns an {@link ApplicationInfo}, with the given path.
     * 
     * @param path
     *            the apk path
     * @return application info
     */
    public static ApplicationInfo getAppInfoFromPath(String path) {
        if (isInitialized()) {
            for (int i = 0; mPackageList != null && i < mPackageList.size(); i++) {
=======
     *
     * @return device is tablet
     */
    public static boolean isTablet() {
        int layout;
        String prop = readProperty("com.android.systemui.layout", "0");
        if(isParsableToInt(prop)) {
            layout = Integer.parseInt(prop);
        } else {
            layout = getActualProperty(prop);
        }
        return layout >= 1000;
    }

    
    /**
     * Returns an {@link ApplicationInfo}, with the given path.
     *
     * @param  path  the apk path
     * @return application info
     */
    public static ApplicationInfo getAppInfoFromPath(String path) {
        if(isInitialized()) {
            for(int i=0; mPackageList != null && i<mPackageList.size(); i++) {
>>>>>>> upstream/jb-mr1
                PackageInfo p = mPackageList.get(i);
                if (p.applicationInfo != null && p.applicationInfo.sourceDir.equals(path)) {
                    return p.applicationInfo;
                }
            }
        }
        return null;
    }

<<<<<<< HEAD
    /**
     * Returns an {@link ApplicationInfo}, with the given package name.
     * 
     * @param packageName
     *            the application package name
     * @return application info
     */
    public static ApplicationInfo getAppInfoFromPackageName(String packageName) {
        if (isInitialized()) {
            for (int i = 0; mPackageList != null && i < mPackageList.size(); i++) {
=======
    
    /**
     * Returns an {@link ApplicationInfo}, with the given package name.
     *
     * @param  packageName  the application package name
     * @return application info
     */
    public static ApplicationInfo getAppInfoFromPackageName(String packageName) {
        if(isInitialized()) {
            for(int i=0; mPackageList != null && i<mPackageList.size(); i++) {
>>>>>>> upstream/jb-mr1
                PackageInfo p = mPackageList.get(i);
                if (p.applicationInfo != null && p.applicationInfo.packageName.equals(packageName)) {
                    return p.applicationInfo;
                }
            }
        }
        return null;
    }

<<<<<<< HEAD
    /**
     * Returns an {@link ApplicationInfo}, with the given PID.
     * 
     * @param pid
     *            the application PID
=======
    
    /**
     * Returns an {@link ApplicationInfo}, with the given PID.
     *
     * @param  pid  the application PID
>>>>>>> upstream/jb-mr1
     * @return application info
     */
    public static ApplicationInfo getAppInfoFromPID(int pid) {
        if (isInitialized()) {
<<<<<<< HEAD
            List<RunningAppProcessInfo> mProcessList = ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE))
                    .getRunningAppProcesses();
            Iterator<RunningAppProcessInfo> mProcessListIt = mProcessList.iterator();
            while (mProcessListIt.hasNext()) {
                ActivityManager.RunningAppProcessInfo mAppInfo = (ActivityManager.RunningAppProcessInfo) (mProcessListIt
                        .next());
                if (mAppInfo.pid == pid) {
=======
            List mProcessList = ((ActivityManager)mContext.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses();
            Iterator mProcessListIt = mProcessList.iterator();
            while(mProcessListIt.hasNext()) {
                ActivityManager.RunningAppProcessInfo mAppInfo = (ActivityManager.RunningAppProcessInfo)(mProcessListIt.next());
                if(mAppInfo.pid == pid) {
>>>>>>> upstream/jb-mr1
                    return getAppInfoFromPackageName(mAppInfo.processName);
                }
            }
        }
        return null;
    }

    /**
<<<<<<< HEAD
     * Traces the input argument <code>msg</code> as a log. Used for debugging.
     * Should not be used on public classes.
     * 
     * @param msg
     *            the message to log
=======
     * Traces the input argument <code>msg</code> as a log. 
     * Used for debugging. Should not be used on public classes.
     *
     * @param  msg  the message to log
>>>>>>> upstream/jb-mr1
     */
    public static void traceMsg(String msg) {
        StringWriter sw = new StringWriter();
        new Throwable("").printStackTrace(new PrintWriter(sw));
        String stackTrace = sw.toString();
<<<<<<< HEAD
        Log.i(TAG + ":" + msg, "Trace=" + stackTrace);
=======
        Log.i(TAG + ":" + msg, "Trace=" + stackTrace); 
>>>>>>> upstream/jb-mr1
    }

    /**
     * Updates the {@link HashMap} that contains all the properties.
     */
    public static void refreshProperties() {
        mPropertyMap.clear();
<<<<<<< HEAD
        String[] props = readFile(BEERBONG_PROPERTIES).split("\n");
        for (int i = 0; i < props.length; i++) {
=======
        String[] props = readFile(PARANOID_PROPERTIES).split("\n");
        for(int i=0; i<props.length; i++) {
>>>>>>> upstream/jb-mr1
            if (!props[i].startsWith("#")) {
                String[] pair = props[i].split("=");
                if (pair.length == 2) {
                    mPropertyMap.put(pair[0].trim(), pair[1].trim());
                }
            }
        }
    }

    /**
<<<<<<< HEAD
     * Returns a {@link String}, containing the result of the configuration for
     * the input argument <code>prop</code>. If the property is not found it
     * returns zero.
     * 
     * @param prop
     *            a string containing the property to checkout
     * @return current stored value of property
     */
    public static String getProperty(String prop) {
=======
     * Returns a {@link String}, containing the result of the configuration
     * for the input argument <code>prop</code>. If the property is not found
     * it returns zero.
     *
     * @param  prop  a string containing the property to checkout
     * @return current stored value of property
     */
    public static String getProperty(String prop){
>>>>>>> upstream/jb-mr1
        return getProperty(prop, String.valueOf(0));
    }

    /**
<<<<<<< HEAD
     * Returns a {@link String}, containing the result of the configuration for
     * the input argument <code>prop</code>. If the property is not found it
     * returns the input argument <code>def</code>.
     * 
     * @param prop
     *            a string containing the property to checkout
     * @param def
     *            default value to be returned in case that property is missing
=======
     * Returns a {@link String}, containing the result of the configuration
     * for the input argument <code>prop</code>. If the property is not found
     * it returns the input argument <code>def</code>.
     *
     * @param  prop  a string containing the property to checkout
     * @param  def  default value to be returned in case that property is missing
>>>>>>> upstream/jb-mr1
     * @return current stored value of property
     */
    public static String getProperty(String prop, String def) {
        try {
            if (isInitialized()) {
                String result = mPropertyMap.get(prop);
<<<<<<< HEAD
                if (result == null)
                    return def;
                if (result.startsWith(BEERBONG_PREFIX)) {
=======
                if (result == null) return def;
                if (result.startsWith(PARANOID_PREFIX)) {
>>>>>>> upstream/jb-mr1
                    result = getProperty(result, def);
                }
                return result;
            } else {
                return readProperty(prop, def);
            }
<<<<<<< HEAD
        } catch (NullPointerException e) {
=======
        } catch (NullPointerException e){
>>>>>>> upstream/jb-mr1
            e.printStackTrace();
        }
        return def;
    }

    /**
<<<<<<< HEAD
     * Returns a {@link String}, containing the result of the configuration for
     * the input argument <code>prop</code>. If the property is not found it
     * returns the input argument <code>def</code>. This property is directly
     * read from the configuration file.
     * 
     * @param prop
     *            a string containing the property to checkout
     * @param def
     *            default value to be returned in case that property is missing
     * @return current stored value of property
     */
    public static String readProperty(String prop, String def) {
        String[] props = readFile(BEERBONG_PROPERTIES).split("\n");
        for (int i = 0; i < props.length; i++) {
            if (props[i].contains("=")) {
                if (props[i].substring(0, props[i].lastIndexOf("=")).equals(prop)) {
                    String result = props[i].replace(prop + "=", "").trim();
                    if (result.startsWith(BEERBONG_PREFIX)) {
=======
     * Returns a {@link String}, containing the result of the configuration
     * for the input argument <code>prop</code>. If the property is not found
     * it returns the input argument <code>def</code>. This property is directly
     * read from the configuration file.
     *
     * @param  prop  a string containing the property to checkout
     * @param  def  default value to be returned in case that property is missing
     * @return current stored value of property
     */
    public static String readProperty(String prop, String def) {
        String[] props = readFile(PARANOID_PROPERTIES).split("\n");
        for(int i=0; i<props.length; i++) {
            if(props[i].contains("=")) {
                if(props[i].substring(0, props[i].lastIndexOf("=")).equals(prop)) {
                    String result = props[i].replace(prop+"=", "").trim();  
                    if (result.startsWith(PARANOID_PREFIX)) {
>>>>>>> upstream/jb-mr1
                        result = getProperty(result, def);
                    }
                    return result;
                }
            }
        }
        return def;
    }

    /**
<<<<<<< HEAD
     * Returns an {@link Integer}, equivalent to what other classes will
     * actually load for the input argument <code>property</code>. it differs
     * from {@link #getProperty(String, String) getProperty}, because the values
     * returned will never be zero.
     * 
     * @param property
     *            a string containing the property to checkout
=======
     * Returns an {@link Integer}, equivalent to what other classes will actually 
     * load for the input argument <code>property</code>. it differs from 
     * {@link #getProperty(String, String) getProperty}, because the values
     * returned will never be zero.
     *
     * @param  property  a string containing the property to checkout
>>>>>>> upstream/jb-mr1
     * @return the actual integer value of the selected property
     * @see getProperty
     */
    public static int getActualProperty(String property) {
        int result = 0;
        boolean getProp = false;

<<<<<<< HEAD
        if (property.endsWith(BEERBONG_DPI_SUFFIX)) {
            ApplicationInfo appInfo = getAppInfoFromPackageName(property.substring(0, property.length()
                    - BEERBONG_DPI_SUFFIX.length()));
            if (appInfo != null) {
                boolean isSystemApp =
                        appInfo.sourceDir.substring(0, appInfo.sourceDir.lastIndexOf("/")).contains("system/app");
                result = Integer.parseInt(getProperty(property, getProperty(BEERBONG_PREFIX + (isSystemApp ?
=======
        if (property.endsWith(PARANOID_DPI_SUFFIX)) {
            ApplicationInfo appInfo = getAppInfoFromPackageName(property.substring(0, property.length()
                    - PARANOID_DPI_SUFFIX.length()));
            if(appInfo != null) {
                boolean isSystemApp = 
                        appInfo.sourceDir.substring(0, appInfo.sourceDir.lastIndexOf("/")).contains("system/app");
                result = Integer.parseInt(getProperty(property, getProperty(PARANOID_PREFIX + (isSystemApp ? 
>>>>>>> upstream/jb-mr1
                        "system_default_dpi" : "user_default_dpi"))));
            } else {
                getProp = true;
            }
<<<<<<< HEAD
        } else if (property.endsWith(BEERBONG_LAYOUT_SUFFIX)) {
            ApplicationInfo appInfo = getAppInfoFromPackageName(property.substring(0, property.length()
                    - BEERBONG_LAYOUT_SUFFIX.length()));
            if(appInfo != null) {
                boolean isSystemApp =
                        appInfo.sourceDir.substring(0, appInfo.sourceDir.lastIndexOf("/")).contains("system/app");
                result = Integer.parseInt(getProperty(property, getProperty(BEERBONG_PREFIX + (isSystemApp ? 
=======
        } else if (property.endsWith(PARANOID_LAYOUT_SUFFIX)) {
            ApplicationInfo appInfo = getAppInfoFromPackageName(property.substring(0, property.length()
                    - PARANOID_LAYOUT_SUFFIX.length()));
            if(appInfo != null) {
                boolean isSystemApp =
                        appInfo.sourceDir.substring(0, appInfo.sourceDir.lastIndexOf("/")).contains("system/app");
                result = Integer.parseInt(getProperty(property, getProperty(PARANOID_PREFIX + (isSystemApp ? 
>>>>>>> upstream/jb-mr1
                        "system_default_layout" : "user_default_layout"))));
            } else {
                getProp = true;
            }
        } else if (property.endsWith("_dpi") || property.endsWith("_layout")) {
            getProp = true;
        }

<<<<<<< HEAD
        if (getProp)
            result = Integer.parseInt(getProperty(property));

        if (result == 0) {
            result = Integer.parseInt(property.endsWith("dpi") ? getProperty(BEERBONG_PREFIX + "rom_default_dpi")
                    : getProperty(BEERBONG_PREFIX + "rom_default_layout"));
=======
        if(getProp) result = Integer.parseInt(getProperty(property));

        if (result == 0) {
            result = Integer.parseInt(property.endsWith("dpi") ? getProperty(PARANOID_PREFIX + "rom_default_dpi")
                : getProperty(PARANOID_PREFIX + "rom_default_layout"));
>>>>>>> upstream/jb-mr1
        }

        return result;
    }

    /**
     * Returns a {@link Boolean}, meaning if the input argument is an integer
     * number.
<<<<<<< HEAD
     * 
     * @param str
     *            the string to be tested
=======
     *
     * @param  str  the string to be tested
>>>>>>> upstream/jb-mr1
     * @return the string is an integer number
     */
    public static boolean isParsableToInt(String str) {
        try {
<<<<<<< HEAD
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void debugOut(String msg) {
        Log.i(TAG + ":" + msg, "Init=" + (mMainThread != null && mContext != null &&
                mPackageManager != null) + " App=" + getName() + " Dpi=" + getDpi());
=======
            int i = Integer.parseInt(str);
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }
    
    public void debugOut(String msg) {
        Log.i(TAG + ":" + msg, "Init=" + (mMainThread != null && mContext != null && 
            mPackageManager != null) + " App=" + getName() + " Dpi=" + getDpi() + 
            " Layout=" + getLayout());
>>>>>>> upstream/jb-mr1
    }
}
