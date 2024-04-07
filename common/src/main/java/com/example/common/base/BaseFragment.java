package com.example.common.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @Author winiymissl
 * @Date 2024-04-05 13:28
 * @Version 1.0
 */
public class BaseFragment<T> extends Fragment {
    protected T binding;

    protected BaseActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    protected BaseActivity getHoldingsActivity() {
        return mActivity;
    }
}
