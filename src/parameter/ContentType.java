package parameter;

import java.util.HashMap;

public class ContentType {

    public static final String CHARSET = "UTF-8";
    public static HashMap<String, String> CONTENT_TYPE_MAP;

    static {
        CONTENT_TYPE_MAP = new HashMap<>();
        CONTENT_TYPE_MAP.put("html", "text/html; charset=" + CHARSET);
        CONTENT_TYPE_MAP.put("txt", "text/plain; charset=" + CHARSET);
        CONTENT_TYPE_MAP.put("json", "application/json; charset=" + CHARSET);
    }
}
