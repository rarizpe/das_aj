package com.project.seguridad.dto.user;

import com.project.base.response.BaseResponse;
import com.project.utils.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserResponse extends BaseResponse {
    private String uuid;

    public UserResponse(ResponseCode response){
        super(response);
    }
}