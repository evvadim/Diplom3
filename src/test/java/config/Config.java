package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String baseURI;
    private static final String registerPath;
    private static final String loginPath;
    private static final String forgotPasswordPath;

    private static final String email;
    private static final String userPassword;
    private static final String userName;

    static {

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/java/config/resources.properties"));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        baseURI = properties.getProperty("baseURI");
        registerPath = properties.getProperty("registerPath");
        loginPath = properties.getProperty("loginPath");
        forgotPasswordPath = properties.getProperty("forgotPasswordPath");

        email = properties.getProperty("email");
        userPassword = properties.getProperty("userPassword");
        userName = properties.getProperty("userName");

    }

    public static String getBaseURI() {
        return baseURI;
    }

    public static String getRegisterPath() {
        return registerPath;
    }

    public static String getLoginPath() {
        return loginPath;
    }

    public static String getForgotPasswordPath() {
        return forgotPasswordPath;
    }


    public static String getEmail() {
        return email;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static String getUserName() {
        return userName;
    }

}
