package handler;

import java.util.HashMap;

public class ValidateParameter {

    public static String[] allowedMethod = new String[] { "GET", "POST" };
    public static HashMap<String, Boolean> allowedMethodList;

    static {

        // 許可対象のメソッド一覧を生成
        allowedMethodList = new HashMap<>();
        for (String method : allowedMethod) {
            allowedMethodList.put(method, true);
        }
    }

    // メソッドの検証
    public static boolean validateMethod(String method) {

        if (allowedMethodList.get(method) != null) {
            return true;
        } else {
            return false;
        }
    }
}
