package com.at.globalclasses;

public enum ApiPath {
    create_users("/api/v1/users"),
    authenticate_users("/api/v1/login"),
    create_histories("/api/v1/histories"),
    create_history("/api/v1/histories"),
    delete_users("/api/v1/users"),
    updating_users("/api/v1/users/"),
    get_histories("/api/v1/histories?userid="),
    get_user("/api/v1/users/");

    private String apiPath;

    ApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getApiPath() {
        return this.apiPath;
    }

}
