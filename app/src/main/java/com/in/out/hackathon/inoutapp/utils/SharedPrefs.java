package com.in.out.hackathon.inoutapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vrihas on 21/5/2019.
 */
public class SharedPrefs {

    // Shared preferences file name
    private static final String PREF_NAME = "FitzuSharedPreferences";
    private static final String KEY_STEPS = "UserTotalSteps";
    private static final String KEY_STEP_COUNTER_STEPS = "stepCounterSteps";
    private static final String KEY_FITCOINS = "fitcoins";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";
    private static final String KEY_NAME = "name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_INTEREST = "interests";
    private static final String KEY_COMPANY = "company";
    private static final String KEY_DAY = "day";
    private static final String KEY_MONTH= "month";
    private static final String KEY_YEAR = "year";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_FEET = "feet";
    private static final String KEY_INCH = "inch";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_OTP = "otp";
    private static final String KEY_FCM = "fcm";
    private static final String KEY_IS_WPL_PLAYING = "wplPlaying";
    private static final String KEY_WPL_TEAM_NAME = "wplTeamName";
    private static final String KEY_WPL_TEAM_CODE = "wplTeamCode";
    private static final String KEY_WPL_TEAM_RANK = "wplTeamRank";
    private static final String KEY_WPL_TEAM_ID = "wplTeamId";
    private static final String KEY_WPL_PLAYER_ID = "wplPlayerId";
    private static final String KEY_WPL_LEADERBOARD_MODE = "wolLeaderboardMode";
    private static final String KEY_GRAPH_TYPE = "graphType";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_STEPS_DATE = "stepsDate";
    private static final String KEY_SHOW_WPL_INTRO = "wplIntro";
    private static final String KEY_FIREBASE_ID = "firebaseId";
    private static final String KEY_NEW_NOTIFICATION = "newNotification";
    public static final String KEY_GENDER_LEADERBOARD = "genderLeaderboard";
    public static final String KEY_PREVIOUS_STEPS = "previousSteps";
    public static final String KEY_PREVIOUS_STEP_DATE = "previousStepsDate";

    private static final int KEY_VERSION=1;
    // LogCat tag
    private static String TAG = "Shared Preference";
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public static int getKeyVersion() {
        return KEY_VERSION;
    }

    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setFirstTimeLaunch(boolean isFirstTimeLaunch) {

        editor.putBoolean(KEY_IS_FIRST_TIME_LAUNCH, isFirstTimeLaunch);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(KEY_IS_FIRST_TIME_LAUNCH, true);
    }


    public void setUsername(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString(KEY_USERNAME, "");
    }


    public void setFCM(String fcm) {
        editor.putString(KEY_FCM, fcm);
        editor.commit();
    }

    public String getFcm() {
        return pref.getString(KEY_FCM, null);
    }

    public void setEmailId(String emailId) {
        editor.putString(KEY_EMAIL, emailId);
        editor.commit();
    }

    public void setUserToken(String accessToken) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getAccessToken() {
        return pref.getString(KEY_ACCESS_TOKEN, "");
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, "");
    }


    public String getMobile() {
        return pref.getString(KEY_MOBILE, "0");
    }

    public void setMobile(String mobile) {
        editor.putString(KEY_MOBILE, mobile);
        editor.commit();
    }


    public String getName() {
        return pref.getString(KEY_NAME, "");
    }

    public void setName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getInterest() {
        return pref.getString(KEY_INTEREST, "");
    }

    public void setInterest(String interest) {
        editor.putString(KEY_INTEREST, interest);
        editor.commit();
    }

    public String getCompany() {
        return pref.getString(KEY_COMPANY, "");
    }

    public void setCompany(String company) {
        editor.putString(KEY_COMPANY, company);
        editor.commit();
    }

    public String getDay() {
        return pref.getString(KEY_DAY, "");
    }

    public void setDay(String day) {
        editor.putString(KEY_DAY, day);
        editor.commit();
    }

    public String getMonth() {
        return pref.getString(KEY_MONTH, "");
    }

    public void setMonth(String month) {
        editor.putString(KEY_MONTH, month);
        editor.commit();
    }

    public String getYear() {
        return pref.getString(KEY_YEAR, "");
    }

    public void setYear(String year) {
        editor.putString(KEY_YEAR, year);
        editor.commit();
    }

    public String getGender() {
        return pref.getString(KEY_GENDER, "");
    }

    public void setGender(String gender) {
        editor.putString(KEY_GENDER, gender);
        editor.commit();
    }

    public void setWeight(int weight) {
        editor.putInt(KEY_WEIGHT, weight);
        editor.commit();
    }

    public int getWeight() {
        return pref.getInt(KEY_WEIGHT, 0);
    }


    public void setFeet(int feet) {
        editor.putInt(KEY_FEET, feet);
        editor.commit();
    }

    public int getFeet() {
        return pref.getInt(KEY_FEET, 0);
    }


    public void setInch(int inch) {
        editor.putInt(KEY_INCH, inch);
        editor.commit();
    }

    public int getInch() {
        return pref.getInt(KEY_INCH, 0);
    }


    public void firstTimeAskingPermission(String permission, boolean isFirstTime) {
        editor.putBoolean(permission, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeAskingPermission(String permission) {
        return pref.getBoolean(permission, true);
    }

    public void setWplPlaying(boolean isWplPlaying) {
        editor.putBoolean(KEY_IS_WPL_PLAYING, isWplPlaying);
        editor.commit();
    }

    public boolean isWplPlaying() {
        return pref.getBoolean(KEY_IS_WPL_PLAYING, false);
    }

    public String getTeamName() {
        return pref.getString(KEY_WPL_TEAM_NAME, "");
    }

    public void setTeamName(String teamName) {
        editor.putString(KEY_WPL_TEAM_NAME, teamName);
        editor.commit();
    }

    public String getTeamCode() {
        return pref.getString(KEY_WPL_TEAM_CODE, "");
    }

    public void setTeamCode(String teamCode) {
        editor.putString(KEY_WPL_TEAM_CODE, teamCode);
        editor.commit();
    }

    public void setWplLeaderboardMode(int mode) {
        editor.putInt(KEY_WPL_LEADERBOARD_MODE, mode);
        editor.commit();
    }

    public int getWplLeaderboardMode() {
        return pref.getInt(KEY_WPL_LEADERBOARD_MODE, 0);
    }



    public void setSteps(long steps) {
        editor.putLong(KEY_STEPS, steps);
        editor.commit();
    }

    public long getSteps() {
        return pref.getLong(KEY_STEPS, 0);
    }

    public void setInitialStepCounterSteps(long initialSteps) {
        editor.putLong(KEY_STEP_COUNTER_STEPS, initialSteps);
        editor.commit();
    }

    public long getInitialStepCounterSteps() {
        return pref.getLong(KEY_STEP_COUNTER_STEPS, 0);
    }


    public String getFitcoins() {
        return pref.getString(KEY_FITCOINS, "0");
    }

    public void setFitcoins(String fitcoins) {
        editor.putString(KEY_FITCOINS, fitcoins);
        editor.commit();
    }


    public String getOtp() {
        return pref.getString(KEY_OTP, "");
    }

    public void setOtp(String otp) {
        editor.putString(KEY_OTP, otp);
        editor.commit();
    }

    public String getStepDate() {
        return pref.getString(KEY_STEPS_DATE,null);
    }

    public void setStepDate(String stepDate) {
        editor.putString(KEY_STEPS_DATE, stepDate);
        editor.commit();
    }

    public String getPreviousStepDate() {
        return pref.getString(KEY_PREVIOUS_STEP_DATE,"date");
    }

    public void setPreviousStepDate(String previousStepDate) {
        editor.putString(KEY_PREVIOUS_STEP_DATE, previousStepDate);
        editor.commit();
    }

    public void setGraphType(int graphType) {
        editor.putInt(KEY_GRAPH_TYPE, graphType);
        editor.commit();
    }

    public int getGraphType() {
        return pref.getInt(KEY_GRAPH_TYPE, 0);
    }

    public String getTeamRank() {
        return pref.getString(KEY_WPL_TEAM_RANK, "NA");
    }

    public void setTeamRank(String teamrank) {
        editor.putString(KEY_WPL_TEAM_RANK, teamrank);
        editor.commit();
    }

    public void setTeamId(int id) {
        editor.putInt(KEY_WPL_TEAM_ID, id);
        editor.commit();
    }

    public int getTeamId() {
        return pref.getInt(KEY_WPL_TEAM_ID, -1);
    }


    public void setPlayerId(int id) {
        editor.putInt(KEY_WPL_PLAYER_ID, id);
        editor.commit();
    }

    public int getPlayerId() {
        return pref.getInt(KEY_WPL_PLAYER_ID, -1);
    }



    public void setShowWPlIntro(boolean isWplPlaying) {
        editor.putBoolean(KEY_SHOW_WPL_INTRO, isWplPlaying);
        editor.commit();
    }

    public boolean isShowWplIntro() {
        return pref.getBoolean(KEY_SHOW_WPL_INTRO, true);
    }


    public void setNotificationSize(int notificationSize) {
        editor.putInt(KEY_NEW_NOTIFICATION, notificationSize);
        editor.commit();
    }

    public int getNotificationSize() {
        return pref.getInt(KEY_NEW_NOTIFICATION, 0);
    }

    public String getFirebaseId() {
        return pref.getString(KEY_FIREBASE_ID,"");
    }

    public void setFirebaseId(String bidDate) {
        editor.putString(KEY_FIREBASE_ID, bidDate);
        editor.commit();
    }

    public void setGenderLeaderboard(boolean isGenderLeaderboard) {
        editor.putBoolean(KEY_GENDER_LEADERBOARD, isGenderLeaderboard);
        editor.commit();
    }

    public boolean isGenderLederboard() {
        return pref.getBoolean(KEY_GENDER_LEADERBOARD, false);
    }

    public void setPreviousSteps(long previousSteps) {
        editor.putLong(KEY_PREVIOUS_STEPS, previousSteps);
        editor.commit();
    }

    public long getPreviousSteps() {
        return pref.getLong(KEY_PREVIOUS_STEPS, 0);
    }

}
