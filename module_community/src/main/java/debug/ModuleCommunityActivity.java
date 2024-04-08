package debug;

import android.os.Bundle;

import com.example.common.base.BaseActivity;
import com.example.module_community.R;
import com.example.module_community.databinding.ActivityModuleCommunityBinding;
import com.example.module_community.ui.CommunityFragment;

public class ModuleCommunityActivity extends BaseActivity<ActivityModuleCommunityBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_community);
    }
}