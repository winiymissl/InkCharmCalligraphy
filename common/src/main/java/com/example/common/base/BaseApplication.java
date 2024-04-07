package com.example.common.base;

import android.app.Application;
import android.util.Log;

import com.example.common.config.ModuleConfig;
import com.example.common.dagger.AppComponent;
import com.example.common.dagger.AppModule;
import com.example.common.dagger.DaggerAppComponent;
import com.example.common.dagger.network.NetModule;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-03 10:12
 * @Version 1.0
 */
public class BaseApplication extends Application {

    private static BaseApplication sInstance;
    private static AppComponent appComponent;
    private List<IApplicationDelegate> mAppDelegateList;

    public static BaseApplication getInstance() {
        return sInstance;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).netModule(new NetModule()).build();
    }

    private List<IApplicationDelegate> modulesApplicationInit(List<IApplicationDelegate> list) {
        list = new ArrayList<>();
        for (String moduleImpl : ModuleConfig.MODULESLIST) {
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IApplicationDelegate) {
                    list.add((IApplicationDelegate) obj);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.d("世界是一个bug", e.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                Log.d("世界是一个bug", e.toString());
            } catch (InstantiationException e) {
                e.printStackTrace();
                Log.d("世界是一个bug", e.toString());
            }
        }
        return list;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        sInstance = this;
        mAppDelegateList = modulesApplicationInit(mAppDelegateList);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }
}
