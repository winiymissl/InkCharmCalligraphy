package com.example.module_community.dao;

import com.example.module_community.data.LocalDataSource;
import com.example.module_community.data.RemoteDataSource;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:38
 * @Version 1.0
 */
public class Repository {
    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;

    public Repository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public RemoteDataSource getRemoteDataSource() {
        return remoteDataSource;
    }

    public void setRemoteDataSource(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public LocalDataSource getLocalDataSource() {
        return localDataSource;
    }

    public void setLocalDataSource(LocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }
}
