package com.at.globalclasses;

public enum ApiPath {
	authenticate_users("/at-sce-api/api/v1/login");

    private String apiPath;

    ApiPath(String apiPath){
        this.apiPath=apiPath;
    }

    public String getApiPath(){
        return this.apiPath;
    }

}
