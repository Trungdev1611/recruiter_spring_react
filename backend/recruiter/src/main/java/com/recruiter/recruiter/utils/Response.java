package com.recruiter.recruiter.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private int status;
    private String message;
    private Object data;



    static public Response successResponse(Object data) {
        return new Response(1, "success", data);
    }
    static public Response errorResponse(Object data) {
        return new Response(0, "error", data);
    }


}
