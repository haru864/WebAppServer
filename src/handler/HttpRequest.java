package handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HttpRequest {

    public StringBuilder header; // リクエストヘッダー
    public StringBuilder body; // リクエストボディ
    public int contentLength; // リクエストのコンテンツ長
    public String method; // リクエストメソッド
    public String contentPath; // パス名
    public String queryString; // クエリストリング
    public ArrayList<String> keyList; // クエリストリングまたはメッセージボディのKEYリスト
    public HashMap<String, String> paramMap; // クエリストリングまたはメッセージボディのKEYとVALUEのマップ
    public ArrayList<String> headerList; // リクエストヘッダーのKEYリスト
    public HashMap<String, String> headerMap; // リクエストヘッダーのKEYとVALUEのマップ

    public HttpRequest(BufferedReader reader) throws NumberFormatException, IOException {

        // ヘッダ取得
        String line;
        header = new StringBuilder();
        contentLength = 0;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            if (line.indexOf("Content-Length:") == 0) {
                contentLength = Integer.parseInt(line.substring(line.indexOf(":") + 2));
            }
            header.append(line + "\n");
        }
        System.out.println("-------header-------\n" + header +
                "--------------------\n");

        // ボディ取得
        body = new StringBuilder();
        if (0 < contentLength) {
            char[] c = new char[contentLength];
            reader.read(c);
            body.append(new String(c));
        }
        System.out.println("-------body-------\n" + body + "\n" + "--------------------\n");

        // リクエストパラメータ取得・検証
        String[] headerArr = header.toString().split("\n");
        method = getMethod(headerArr[0]);
        contentPath = getContentPath(headerArr[0]);
        queryString = getQueryString(headerArr[0]);
        getParameter();

        // リクエストヘッダー取得
        headerList = new ArrayList<>();
        headerMap = new HashMap<>();
        for (int i = 1; i < headerArr.length; i++) {
            int index = headerArr[i].indexOf(":");
            String key = headerArr[i].substring(0, index);
            String value = headerArr[i].substring(index + 2);
            headerList.add(key);
            headerMap.put(key, value);
        }
    }

    // パラメータのKEYリストとマップを生成するメソッド
    public void getParameter() {
        String params = "";
        if (this.method.equals("GET")) {
            params = this.queryString.substring(1);
        } else if (this.method.equals("POST")) {
            params = this.body.toString();
        }

        String[] temp = params.split("&");
        keyList = new ArrayList<>();
        paramMap = new HashMap<>();

        for (String p : temp) {
            String key = p.substring(0, p.indexOf("="));
            String value = p.substring(p.indexOf("=") + 1);
            this.keyList.add(key);
            this.paramMap.put(key, value);
        }
    }

    // Method取得メソッド
    public String getMethod(String line) {
        String ret = "";
        int index = line.indexOf(" ");
        ret = line.substring(0, index);
        return ret;
    }

    // パス取得メソッド
    public String getContentPath(String line) {
        String ret = "";
        int head = line.indexOf(" ");
        int tail = line.lastIndexOf(" ");
        int q = line.indexOf("?");
        if (q != -1) {
            ret = line.substring(head + 1, q);
        } else {
            ret = line.substring(head + 1, tail);
        }
        return ret;
    }

    // クエリストリング取得メソッド
    public String getQueryString(String line) {
        String ret = "";
        int tail = line.lastIndexOf(" ");
        int q = line.indexOf("?");
        if (q != -1)
            ret = line.substring(q, tail);
        return ret;
    }
}
