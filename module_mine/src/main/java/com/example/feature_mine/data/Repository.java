package com.example.feature_mine.data;

import javax.inject.Inject;

/**
 * @Author winiymissl
 * @Date 2024-04-05 14:51
 * @Version 1.0
 */
public class Repository {
    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;


    @Inject
    public Repository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public RemoteDataSource getRemoteDataSource() {
        return remoteDataSource;
    }

    public LocalDataSource getLocalDataSource() {
        return localDataSource;
    }
}
