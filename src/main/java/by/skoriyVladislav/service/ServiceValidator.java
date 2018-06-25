package by.skoriyVladislav.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceValidator {

    public static boolean isValidObj(Object obj) {
        return obj != null;
    }

    public static boolean isValidSting(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isValidInteger(int number) {
        return number >= 0;
    }

    public static boolean isValidDouble(double number) {
        return number >= 0;
    }

    public static boolean isValidName(String userNameString){
        if (userNameString != null) {
            Pattern p = Pattern.compile("^([A-Z]|[А-я])+([a-z]|[а-я])+$");
            Matcher m = p.matcher(userNameString);
            return m.matches();
        } else {
            return false;
        }
    }
}
