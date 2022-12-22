package com.api.coursesmanager.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exception {
    private Integer status;
    private String message;
    private LocalDateTime timeStamp;
    private String trace;
}
