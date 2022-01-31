package com.card.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    String responseCode;
    String responseMessage;

    public ResponseDTO() {
    }

    public ResponseDTO(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
