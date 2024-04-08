package debug;

import com.example.common.base.BaseApplication;
import com.example.lib_router_core.template.Router;

/**
 * @Author winiymissl
 * @Date 2024-04-07 16:15
 * @Version 1.0
 * @Description 用于测试时的初始化
 */
public class MineDebugApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.DEBUG(true);
        Router.init(this);
    }
}
