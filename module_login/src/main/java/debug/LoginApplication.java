package debug;

import com.example.common.base.BaseApplication;
import com.example.lib_router_core.template.Router;

/**
 * @Author winiymissl
 * @Date 2024-04-08 16:23
 * @Version 1.0
 */
public class LoginApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.DEBUG(true);
        Router.init(this);
    }
}
