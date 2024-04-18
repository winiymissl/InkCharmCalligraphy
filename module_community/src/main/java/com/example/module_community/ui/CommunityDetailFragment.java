package com.example.module_community.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.Explode;

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.ViewSkeletonScreen;
import com.example.common.base.BaseFragment;
import com.example.common.base.MyMMkv;
import com.example.common.util.Utils;
import com.example.module_community.R;
import com.example.module_community.data.model.result.CommentResult;
import com.example.module_community.databinding.FragmentCommunityDetailBinding;
import com.example.module_community.ui.adapter.CommentAdapter;
import com.example.module_community.ui.adapter.model.CommentItem;
import com.example.module_community.ui.viewmodel.CommunityDetailViewModel;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.wx.goodview.GoodView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-12 0:02
 * @Version 1.0
 */
public class CommunityDetailFragment extends BaseFragment<FragmentCommunityDetailBinding> {
    private CommunityDetailViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new Explode());
        setExitTransition(new Explode());
    }

    private int user_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_detail, container, false);
        binding = FragmentCommunityDetailBinding.bind(view);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
         * 基础资源
         * */
        int id = getArguments().getInt("post_id");
        Log.d("世界是一个bug", String.valueOf(id));
        mViewModel = new ViewModelProvider(this).get(CommunityDetailViewModel.class);

        CommentAdapter adapter_comment = new CommentAdapter();
        binding.recyclerViewComment.setLayoutManager(new LinearLayoutManager(getHoldingsActivity()));
        binding.recyclerViewComment.setAdapter(adapter_comment);

        ViewSkeletonScreen skeleton_nickName = Skeleton.bind(binding.textViewNickname).load(R.layout.item_skeleton_nick_name).show();
        ViewSkeletonScreen skeleton_avatar = Skeleton.bind(binding.circleImageView).load(R.layout.item_skeleton_avatar).show();
        ViewSkeletonScreen skeleton_banner = Skeleton.bind(binding.banner).load(R.layout.item_skeleton_image_view).show();
        ViewSkeletonScreen skeleton_content = Skeleton.bind(binding.textViewContent).load(R.layout.item_skeleton_content).show();
        RecyclerViewSkeletonScreen skeleton_comment = Skeleton.bind(binding.recyclerViewComment).adapter(adapter_comment).load(R.layout.item_skeleton_comment).show();

        mViewModel.fetchCommentData(id, 1, 1);
        mViewModel.fetchPostData(MyMMkv.getMyDefaultMMkv().getString("token", null), id);


        mViewModel.getmCommentMutableLiveData().observe(getViewLifecycleOwner(), commentResult -> {
            if (commentResult == null) {
                return;
            }

            if (commentResult.getCode() == 200) {
                skeleton_comment.hide();
//                Log.d("世界是一个bug", commentResult.toString());
                if (commentResult.getData().getComment_data() != null) {
                    adapter_comment.setCommentData(net2comment(commentResult));
                }
            } else {
                skeleton_comment.show();
            }
        });
        mViewModel.getmPostDetailMutableLiveData().observe(getViewLifecycleOwner(), postDetailResult -> {
            if (postDetailResult == null) {
                return;
            }
            user_id = postDetailResult.getData().getPost_data().getUser_id();
            if (postDetailResult.getCode() == 200) {
                skeleton_nickName.hide();
                skeleton_avatar.hide();
                skeleton_banner.hide();
                skeleton_content.hide();
                binding.setItem(postDetailResult.getData());
            } else {
                skeleton_nickName.show();
                skeleton_avatar.show();
                skeleton_banner.show();
                skeleton_content.show();
            }
        });

        binding.iconLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MaterialCheckBox c = (MaterialCheckBox) buttonView;
                if (isChecked) {
                    c.setButtonDrawable(getResources().getDrawable(com.example.common.R.drawable.like_red));
                    GoodView goodView = new GoodView(getHoldingsActivity());
                    goodView.setText("+1");
                    goodView.show(c);
                    mViewModel.like(MyMMkv.getMyDefaultMMkv().getString("token", null), id);
                    mViewModel.getmLikeMutableLiveData().observe(getViewLifecycleOwner(), likeResult -> {
                        if (likeResult == null) {
                            return;
                        }
                        if (likeResult.getCode() == 200) {
                            Toast.makeText(mActivity, "点赞成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    c.setButtonDrawable(getResources().getDrawable(com.example.common.R.drawable.like));
                    GoodView goodView = new GoodView(getHoldingsActivity());
                    goodView.setText("-1");
                    goodView.show(c);
                    mViewModel.cancelLike(MyMMkv.getMyDefaultMMkv().getString("token", null), id);
                    mViewModel.getmCancelLikeMutableLiveData().observe(getViewLifecycleOwner(), cancelLikeResult -> {
                        if (cancelLikeResult == null) {
                            return;
                        }
                        if (cancelLikeResult.isSuccess()) {
                            Toast.makeText(mActivity, "取消点赞成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        binding.iconCollect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MaterialCheckBox c = (MaterialCheckBox) buttonView;
                if (isChecked) {
                    c.setButtonDrawable(getResources().getDrawable(com.example.common.R.drawable.collect_yellow));
                    GoodView goodView = new GoodView(getHoldingsActivity());
                    goodView.setText("收藏");
                    goodView.show(c);
                    mViewModel.collect(MyMMkv.getMyDefaultMMkv().getString("token", null), user_id);
                    mViewModel.getmCollectMutableLiveData().observe(getViewLifecycleOwner(), collectResult -> {
                        if (collectResult == null) {
                            return;
                        }
                        if (collectResult.isSuccess()) {
                            Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    c.setButtonDrawable(getResources().getDrawable(com.example.common.R.drawable.collect));
                    GoodView goodView = new GoodView(getHoldingsActivity());
                    goodView.setText("取消收藏");
                    goodView.show(c);
                    mViewModel.cancelCollect(MyMMkv.getMyDefaultMMkv().getString("token", null), user_id);
                    mViewModel.getmCancelCollectMutableLiveData().observe(getViewLifecycleOwner(), cancelCollectResult -> {
                        if (cancelCollectResult == null) {
                            return;
                        }
                        if (cancelCollectResult.isSuccess()) {
                            Toast.makeText(mActivity, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        binding.chipIsFollowed.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mViewModel.follow(MyMMkv.getMyDefaultMMkv().getString("token", null), user_id);
                mViewModel.getmFollowMutableLiveData().observe(getViewLifecycleOwner(), followResult -> {
                    if (followResult == null) {
                        return;
                    }
                    if (followResult.isSuccess()) {
                        Toast.makeText(mActivity, "followed", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                mViewModel.cancelFollow(MyMMkv.getMyDefaultMMkv().getString("token", null), user_id);
                mViewModel.getmCancelFollowMutableLiveData().observe(getViewLifecycleOwner(), cancelFollowResult -> {
                    if (cancelFollowResult == null) {
                        return;
                    }
                    if (cancelFollowResult.isSuccess()) {
                        Toast.makeText(mActivity, "unfollowed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mViewModel.getThrowableMutableLiveData().observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                return;
            }
            Utils.tryAgain(throwable.getMessage(), getHoldingsActivity());
        });
        binding.iconButtonShare.setOnClickListener(v -> {
            /*
             * 分享的dialog
             * */
            NavHostFragment.findNavController(this).navigate(R.id.shareDialogFragment);
        });

        binding.iconButtonCommon.setOnClickListener(v -> {
            /*
             * 定格到评论的位置
             * */
        });
        binding.fabBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).popBackStack();
        });
    }

    private List<CommentItem> net2comment(CommentResult result) {
        List<CommentItem> list = new ArrayList<>();
        if (result.getData().getComment_data() != null) {
            result.getData().getComment_data().forEach(item -> {
                long temp = item.getCreate_time();
                Date date = new Date(temp);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = sdf.format(date);
                list.add(new CommentItem(item.getUser_info().getAvatar_image(), formattedDate, item.getComment(), item.getUser_info().getNick_name()));
            });
        }
        return list;
    }
}
