package com.at.globalclasses;

public enum ApiPath {
    create_users("/api/v1/users"),
	authenticate_users("/api/v1/login");

    private String apiPath;

    ApiPath(String apiPath){
        this.apiPath=apiPath;
    }

    public String getApiPath(){
        return this.apiPath;
    }

}
