package am.aca.addressbook.comman.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.System.in;

public class Utils {
    public static final String FILE_PATH ="C:\\Users\\Saqo\\projects\\AddressBook_SQL\\Resources\\config.properties";
    public static Properties properties=new Properties();
    public static Scanner scanner=new Scanner(in);
    public static String url = "jdbc:mysql://localhost:3306/phonebook?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static String userName = "root";
    public static String password = "root";
    public static int signInCount=0;

    public static void printMessage(String key) {
        System.out.print(properties.getProperty(key));
    }

    public static void loadProperties(){
        try {
            properties.load(new FileInputStream(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static {
        loadProperties();

    }

}