package debug;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.module_mine.R;
import com.example.module_mine.databinding.ActivityMainModuleBinding;


public class ModuleMainActivity extends AppCompatActivity {
    private ActivityMainModuleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_module);
    }
}