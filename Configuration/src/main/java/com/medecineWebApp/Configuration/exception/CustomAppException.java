package com.medecineWebApp.Configuration.exception;

public class CustomAppException extends RuntimeException {
  private final BusinessErrorCodes errorCode;

  public CustomAppException(BusinessErrorCodes errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public BusinessErrorCodes getErrorCode() {
    return errorCode;
  }
}
