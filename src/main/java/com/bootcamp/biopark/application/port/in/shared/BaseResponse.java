package com.bootcamp.biopark.application.port.in.shared;

import org.springframework.http.HttpStatus;
// envoltorio estandar para  las responses exitosas de la API.
public record BaseResponse<K>(
        int code,
        String message,
        K data
) {

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), "Operacion exitosa", data);
    }
    //sobrecarga de success  para mensaje personalizado
    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), message, data);
    }
    //para respuestas de creacion 201
    public static <T> BaseResponse<T> created(T data) {
        return new BaseResponse<>(HttpStatus.CREATED.value(), "Recurso creado correctamente", data);
    }

    //sobrecarga opcional de created por si el mensaje varía
    public static <T> BaseResponse<T> created(String message, T data) {
        return new BaseResponse<>(HttpStatus.CREATED.value(), message, data);
    }
}