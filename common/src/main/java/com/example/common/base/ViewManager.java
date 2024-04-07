package com.example.common.base;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-05 12:39
 * @Version 1.0
 */
public class ViewManager {
    private static List<Fragment> fragmentList;

    public static ViewManager getInstance() {
        return ViewManagerHolder.sInstance;
    }

    public void addFragment(Fragment fragment) {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.add(fragment);
    }

    public List<Fragment> getAllFragment() {
        if (fragmentList != null) {
            return fragmentList;
        }
        return null;
    }

    public Fragment getFragment(int index) {
        if (fragmentList != null) {
            return fragmentList.get(index);
        }
        return null;
    }

    private static class ViewManagerHolder {
        private static final ViewManager sInstance = new ViewManager();
    }
}