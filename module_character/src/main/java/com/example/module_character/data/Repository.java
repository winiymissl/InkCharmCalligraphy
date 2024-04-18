package com.example.module_character.data;

import javax.inject.Inject;

/**
 * @Author winiymissl
 * @Date 2024-04-18 16:34
 * @Version 1.0
 */
public class Repository {
    private RemoteDataSource remoteDataSource;

    @Inject
    public Repository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public RemoteDataSource getRemoteDataSource() {
        return remoteDataSource;
    }
}
