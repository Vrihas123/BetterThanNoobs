package com.in.out.hackathon.inoutapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;

public class DialogFactory {

    private DialogFactory() {

    }

    public static final int CONNECTION_PROBLEM_DIALOG = 1;
    public static final int UPDATE_APP = 2;
    public static final int DELETE_TEAM_CONFIRMATION = 3;
    public static final int LEAVE_TEAM_CONFIRMATION = 4;


    public static void showDialog(int id, final Context context, DialogInterface.OnClickListener clickListenerPositive, DialogInterface.OnClickListener clickListenerNegative, boolean isCancellable, Object... d) {
        switch (id) {
            case CONNECTION_PROBLEM_DIALOG:
                getDialog(context, clickListenerPositive, clickListenerNegative, isCancellable, d);
                break;
            case UPDATE_APP:
                getDialog(context, clickListenerPositive, clickListenerNegative, isCancellable, d);
                break;
            case DELETE_TEAM_CONFIRMATION:
                getDialog(context, clickListenerPositive, clickListenerNegative, isCancellable, d);
                break;
            case LEAVE_TEAM_CONFIRMATION:
                getDialog(context, clickListenerPositive, clickListenerNegative, isCancellable, d);
                break;
            default:
                break;
        }
    }

    private static void getDialog(Context context, DialogInterface.OnClickListener clickListenerPositive, DialogInterface.OnClickListener clickListenerNegative, boolean isCancellable, Object... d) {
        final AlertDialog alertDialog = new AlertDialog.Builder(
                context).create();
        alertDialog.setTitle(d[0].toString());
        alertDialog.setMessage(d[1].toString());
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, d[2].toString(), clickListenerPositive);
        if (d.length > 3){
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, d[3].toString(), clickListenerNegative);
        }
        if (isCancellable){
            alertDialog.setCancelable(true);
        }else {
            alertDialog.setCancelable(false);
        }
        alertDialog.show();
    }

    public static void setDynamicDialogHeightWidth(AppCompatActivity activity, AppCompatDialog dialog, float widthDimen, float heightDimen, boolean isHeightDynamic) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);

        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        int dialogWindowWidth = (int) (displayWidth * widthDimen);
        layoutParams.width = dialogWindowWidth;

        int dialogWindowHeight = (int) (displayHeight * heightDimen);

        if (isHeightDynamic) {
            layoutParams.height = dialogWindowHeight;
        } else {
            layoutParams.height = (ViewGroup.LayoutParams.MATCH_PARENT);//dialogWindowHeight;
        }
        dialog.getWindow().setAttributes(layoutParams);
    }

}
