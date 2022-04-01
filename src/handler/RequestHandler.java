package handler;

import java.util.HashMap;

public class RequestHandler {

    public String requestMethod;
    public String requestContent;
    public String requestQueryString;
    public String[] allowedMethod = new String[] { "GET", "POST" };
    public HashMap<String, Boolean> allowedMethodList;

    public RequestHandler(StringBuilder header, StringBuilder body, int contentLength) {

        // 許可対象のメソッド一覧を生成
        allowedMethodList = new HashMap<>();
        for (String method : allowedMethod) {
            allowedMethodList.put(method, true);
        }

        // リクエストパラメータ取得・検証
        String[] headerList = header.toString().split("\n");
        System.out.println("\"" + headerList[0] + "\"");
        requestMethod = getMethod(headerList[0]);
        // requestContent = getContentPath(headerList[0]);
        // requestQueryString = getQueryString(headerList[0]);
        System.out.println("Method: " + requestMethod);
        // System.out.println("Content: " + requestContent);
        // System.out.println("Query: " + requestQueryString);
    }

    public String getMethod(String line) {
        System.out.println("line: " + line);
        String ret = "";
        int index = line.indexOf(" ");
        System.out.println(line.substring(0, 3));
        // ret = line.substring(0, index);
        return ret;
    }

    public String getContentPath(String line) {
        String ret = "";
        int head = line.indexOf(" ");
        int tail = line.lastIndexOf(" ");
        String path = line.substring(head + 1, tail);
        int q = path.indexOf("?");
        ret = path.substring(head, (q == -1) ? tail : q);
        return ret;
    }

    public String getQueryString(String line) {
        String ret = "";
        int tail = line.lastIndexOf(" ");
        int q = line.indexOf("?");
        if (q != -1)
            ret = line.substring(q, tail);
        return ret;
    }
}
