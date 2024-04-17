package com.example.module_community.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.common.base.BaseApplication;
import com.example.common.dagger.AppComponent;
import com.example.module_community.dagger.CommunityModule;
import com.example.module_community.dagger.DaggerCommunityComponent;
import com.example.module_community.data.model.result.CommunityInfoResult;
import com.example.module_community.ui.adapter.model.CommunityItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Author winiymissl
 * @Date 2024-04-15 9:28
 * @Version 1.0
 */
public class MyRxPagingSource extends RxPagingSource<Integer, CommunityItem> {
    @Inject
    Repository repository;

    public MyRxPagingSource() {
        AppComponent appComponent = BaseApplication.getAppComponent();
        DaggerCommunityComponent.builder().appComponent(appComponent).communityModule(new CommunityModule()).build().injectTo(this);
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, CommunityItem> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, CommunityItem> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, CommunityItem>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
//        Integer page = loadParams.getKey() != null ? loadParams.getKey() : 1;
        Integer nextPageNumber = loadParams.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 1;
        }
        Integer finalNextPageNumber = nextPageNumber;
//        return repository.getRemoteDataSource()
//                .getAllPosts(finalNextPageNumber, 10)
//                .subscribeOn(Schedulers.io())
//                .map(this::toLoadResult)
//                .map(integerPagerItemLoadResult -> {
//                    Log.d("世界是一个bug", integerPagerItemLoadResult.toString() + "\n" + "   page : " + finalNextPageNumber);
//                    return integerPagerItemLoadResult;
//                }).onErrorReturn(new Function<Throwable, LoadResult<Integer, CommunityItem>>() {
//                    @Override
//                    public LoadResult<Integer, CommunityItem> apply(Throwable throwable) throws Throwable {
//                        Log.d("世界是一个bug", throwable.toString());
//                        return null;
//                    }
//                });
        return null;
    }

    private LoadResult<Integer, CommunityItem> toLoadResult(CommunityInfoResult response) {
//        List<CommunityInfoResult.DataDTO.PostDataDTO> list = response.getData().getPost_data();
//        Integer curPage = response.getData().getCurrent_page();
//        Integer pageCount = response.getData().getTotal_count();
//        Integer prevPage = curPage > 1 ? curPage - 1 : null;
//        Integer nextPage = !pageCount.equals(curPage) ? curPage + 1 : null;
//        List<CommunityItem> itemList = new ArrayList<>();
//        list.forEach(item -> {
//            itemList.add(new CommunityItem(item.getImage_urls().get(0),
//                    item.getUser_info().getNick_name(),
//                    item.getContent(),
//                    item.getUser_info().getAvatar_image()));
//        });
//        return new LoadResult.Page<>(itemList, null, nextPage);
        return null;
    }
}