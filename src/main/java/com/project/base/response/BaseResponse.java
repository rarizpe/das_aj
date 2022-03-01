package com.project.base.response;

import com.project.utils.enums.ResponseCode;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@MappedSuperclass
public abstract class BaseResponse {

    private String responseCode;
    private String responseMessage;

    public BaseResponse(ResponseCode response){
        this.responseCode =  response.getCode();
        this.responseMessage = response.getMessage();
    }

    public String getResponseCode(){
        return responseCode == null ? ResponseCode.OK.getCode() : responseCode;
    }

    public String getResponseMessage(){
        return responseMessage == null ? ResponseCode.OK.getMessage() : responseMessage;
    }
}
