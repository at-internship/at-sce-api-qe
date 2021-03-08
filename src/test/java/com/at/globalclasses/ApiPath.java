package com.at.globalclasses;

public enum ApiPath {
    create_users("/at-sce-api/api/v1/users");
    private String apiPath;

    ApiPath(String apiPath){
        this.apiPath=apiPath;
    }

    public String getApiPath(){
        return this.apiPath;
    }

}
