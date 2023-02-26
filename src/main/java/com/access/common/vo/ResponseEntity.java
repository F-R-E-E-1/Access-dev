package com.access.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> ResponseEntity<T> success(){
        return new ResponseEntity<>(20000,"success",null);
    }

    public static <T> ResponseEntity<T> success(T data){
        return new ResponseEntity<>(20000,"success",data);
    }

    public static <T> ResponseEntity<T> success(T data,String message){
        return new ResponseEntity<>(20000,message,data);
    }

    public static <T> ResponseEntity<T> success(String message){
        return new ResponseEntity<>(20000,message,null);
    }

    public static <T> ResponseEntity<T> fail(){
        return new ResponseEntity<>(20001,"fail",null);
    }

    public static <T> ResponseEntity<T> fail(Integer code){
        return new ResponseEntity<>(code,"fail",null);
    }

    public static <T> ResponseEntity<T> fail(Integer code, String message){
        return new ResponseEntity<>(code,message,null);
    }

    public static <T> ResponseEntity<T> fail( String message){
        return new ResponseEntity<>(20001,message,null);
    }
}
