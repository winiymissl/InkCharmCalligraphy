package com.example.module_community.data;

/**
 * @Author winiymissl
 * @Date 2024-04-12 21:28
 * @Version 1.0
 */
public class Repository {
    private RemoteDataSource remoteDataSource;

    public Repository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public RemoteDataSource getRemoteDataSource() {
        return remoteDataSource;
    }

    public void setRemoteDataSource(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
}
