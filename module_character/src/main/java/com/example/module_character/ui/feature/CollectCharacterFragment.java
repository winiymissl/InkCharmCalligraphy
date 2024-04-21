package com.example.module_character.ui.feature;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.common.base.BaseFragment;
import com.example.module_character.R;
import com.example.module_character.databinding.FragmentCollectCharacterBinding;
import com.google.android.material.transition.MaterialFade;

/**
 * @Author winiymissl
 * @Date 2024-04-15 17:31
 * @Version 1.0
 */
public class CollectCharacterFragment extends BaseFragment<FragmentCollectCharacterBinding> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setEnterTransition(new MaterialFade());
        setExitTransition(new MaterialFade());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.webView.loadUrl("http://www.shufaway.com/");
        binding.webView.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                //按返回键操作并且能回退网页
                if (keyCode == KeyEvent.KEYCODE_BACK && binding.webView.canGoBack()) {
                    //后退
                    binding.webView.goBack();
                    return true;
                }
            }
            return false;
        });
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect_character, container, false);
        binding = FragmentCollectCharacterBinding.bind(view);
        return binding.getRoot();
    }
}