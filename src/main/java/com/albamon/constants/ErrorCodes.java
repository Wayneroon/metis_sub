package com.albamon.constants;

public final class ErrorCodes {

    // 커스텀
    public static final String BAD_REQUEST_CASE1 = "B901";
    public static final String BAD_REQUEST_CASE2 = "B902";
    public static final String BAD_REQUEST_CASE3 = "B903";
    public static final String BAD_REQUEST_CASE4 = "B904";

    /*HttpStatus.BAD_REQUEST*/
    public static final String BAD_REQUEST_INVALID_VALUE = "B001";
    public static final String BAD_REQUEST_NOT_FOUND = "B002";
    public static final String BAD_REQUEST_FILE_NOT_FOUND_EXCEPTION = "B003";
    public static final String BAD_REQUEST_INCORRECT_DATA_EXCEPTION = "B004";
    public static final String BAD_REQUEST_DUPLICATE_DATA_EXCEPTION = "B005";
    public static final String BAD_REQUEST_LOGIN_INPUT_INVALID = "B006";
    public static final String BAD_REQUEST_NOT_ALLOW_FILE_EXTENSION_EXCEPTION = "B007";

    /*HttpStatus.METHOD_NOT_ALLOWED*/
    public static final String METHOD_NOT_ALLOWED = "M001";

    /*HttpStatus.FORBIDDEN*/
    public static final String FORBIDDEN = "F001";

    /*HttpStatus.INTERNAL_SERVER_ERROR*/
    public static final String INTERNAL_SERVER_ERROR = "I001";


    private ErrorCodes() {
    }
}
