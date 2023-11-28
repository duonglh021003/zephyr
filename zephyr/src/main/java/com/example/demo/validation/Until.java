package com.example.demo.validation;

public class Until {

    public static final int MAX_NAME_LENGTH = 200;
    public static final int PHONE_NUMBER_LENGTH = 10;
    public static final int MAX_ADDRESS_LENGTH = 200;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 200;
    public static final int MAX_DESCRIBE = 200;
    public static final int MAX_INVENTORY = 200;
    public static final double MIN_IMPORT_PRICE = 100.0;
    public static final double MAX_IMPORT_PRICE = 1000.0;
    public static final double MIN_PRICE_LENGTH = 100.0;
    public static final double MAX_PRICE_LENGTH = 1000.0;


    public static final String GMAIL = "^[a-zA-Z0-9._%+-]+@gmail.com$";
    public static final String PASSWORD = "^(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}$";
    public static final String NUMBER = "[0-9.9]{1,10}";
    public static final String TEXT = "[a-z A-Z]+";




//    public static final String GMAIL = "/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/";
}
