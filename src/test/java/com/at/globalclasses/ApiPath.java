package com.at.globalclasses;

public enum ApiPath {
    create_users("/api/v1/users"),
	authenticate_users("/api/v1/login"),
    create_histories("/api/v1/histories");

    private String apiPath;

    ApiPath(String apiPath){
        this.apiPath=apiPath;
    }

    public String getApiPath(){
        return this.apiPath;
    }

}
