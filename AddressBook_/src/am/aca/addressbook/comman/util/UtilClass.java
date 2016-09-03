package am.aca.addressbook.comman.util;

import java.util.Properties;
import java.util.Scanner;

import static java.lang.System.in;

public class UtilClass {
    public static final String FILE_PATH ="C:\\Users\\Saqo\\projects\\AddressBook-master\\Resources\\config.properties";
    public static Properties properties=new Properties();
    public static Scanner scanner=new Scanner(in);
    public  static Scanner scannerint = new Scanner(in);
    public static void printMessage(String key) {
        System.out.print(properties.getProperty(key));
    }

    private static Integer next_id=0;
    public static Integer generateId(){
        return next_id++;
    }

}