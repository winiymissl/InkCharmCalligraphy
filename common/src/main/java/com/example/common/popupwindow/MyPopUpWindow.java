package com.example.common.popupwindow;

import android.app.Dialog;
import android.content.Context;

import androidx.fragment.app.Fragment;

import razerdp.basepopup.BasePopupWindow;

/**
 * @Author winiymissl
 * @Date 2024-04-17 12:10
 * @Version 1.0
 */
public class MyPopUpWindow extends BasePopupWindow {
    public MyPopUpWindow(Context context, Integer layoutId) {
        super(context);
        setContentView(createPopupById(layoutId));
    }

    public MyPopUpWindow(Context context, int width, int height) {
        super(context, width, height);
    }

    public MyPopUpWindow(Fragment fragment) {
        super(fragment);
    }

    public MyPopUpWindow(Fragment fragment, int width, int height) {
        super(fragment, width, height);
    }

    public MyPopUpWindow(Dialog dialog) {
        super(dialog);
    }

    public MyPopUpWindow(Dialog dialog, int width, int height) {
        super(dialog, width, height);
    }
}
