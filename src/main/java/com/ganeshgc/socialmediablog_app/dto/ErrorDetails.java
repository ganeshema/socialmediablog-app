package com.ganeshgc.socialmediablog_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
public class ErrorDetails {
   private LocalDateTime timestamp;
   private String message;
   private String details;

}
