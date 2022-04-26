package parameter;

import java.util.HashMap;

public class ExtensionList {

    public static String[] extensionList = new String[] { "html", "json" };
    public static String[] mimeList = new String[] { "text/html", "application/json" };

    public static HashMap<String, Boolean> extensionMap;
    public static HashMap<String, String> mimeToExtension;

    static {

        // 許可対象のメソッド一覧を生成
        extensionMap = new HashMap<>();
        for (String extension : extensionList) {
            extensionMap.put(extension, true);
        }

        // MIMEと拡張子を紐づけ
        mimeToExtension = new HashMap<>();
        for (int i = 0; i < mimeList.length; i++) {
            mimeToExtension.put(mimeList[i], extensionList[i]);
        }
    }

    // メソッドの検証
    public static boolean validateMethod(String extension) {

        if (extensionMap.get(extension) != null) {
            return true;
        } else {
            return false;
        }
    }
}
