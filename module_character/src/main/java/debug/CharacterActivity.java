package debug;

import android.os.Bundle;

import com.example.common.base.BaseActivity;
import com.example.module_character.R;
import com.example.module_character.databinding.ActivityCharacterBinding;

public class CharacterActivity extends BaseActivity<ActivityCharacterBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        binding = ActivityCharacterBinding.inflate(getLayoutInflater());
    }
}