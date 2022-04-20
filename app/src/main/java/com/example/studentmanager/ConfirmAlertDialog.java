package com.example.studentmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AlertDialog;

public class ConfirmAlertDialog {
    private boolean isConfirm;

    public void showAlertDialog(final Context context) {
        final Drawable positiveIcon = context.getResources().getDrawable(R.drawable.ic_baseline_check_circle_24);
        final Drawable negativeIcon = context.getResources().getDrawable(R.drawable.ic_baseline_cancel_24);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set Title and Message:
        builder.setTitle("Hey dude!").setMessage("Do you want to delete?");


        builder.setCancelable(true);
        // builder.setIcon(R.drawable.icon_title);

        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setConfirm(true);
            }
        });
        builder.setPositiveButtonIcon(positiveIcon);

        // Create "No" button with OnClickListener.
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                setConfirm(false);
            }
        });
        builder.setNegativeButtonIcon(negativeIcon);

        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void setConfirm(boolean confirm) {
        isConfirm = confirm;
    }

    public boolean isConfirm() {
        return isConfirm;
    }

}
