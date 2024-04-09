package debug;

import android.app.Application;
import android.util.Log;

/**
 * @Author winiymissl
 * @Date 2024-04-07 16:15
 * @Version 1.0
 * @Description 用于测试时的初始化
 */
public class MineDebugApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("世界是一个bug", "初始化完成");
    }
}
