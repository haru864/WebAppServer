package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import server.WebAppServer;

public class ErrorHandler {

    public Exception exception;
    public String jsonName;
    public File errorJson;

    public ErrorHandler(Exception e) {

        // スタックトレースをコンソールに表示する
        e.printStackTrace();
        this.exception = e;

        // クラス名を取得する
        String temp = e.toString();
        int n = temp.indexOf(".");
        int m = temp.indexOf("Exception");
        this.jsonName = temp.substring(n + 1, m);

        // エラーJSONファイルを取得する
        this.errorJson = new File("./bin/error/" + this.jsonName + ".json");
    }

    public void sendError(BufferedWriter w) {

        try {
            // ステータスライン生成
            String statusCode = getValueFromErrorJson("status");
            String reasonPhrase = getValueFromErrorJson("reason");
            String statusLine = WebAppServer.HTTP_Ver + " " + statusCode + " " + reasonPhrase;

            // ヘッダー生成
            StringBuffer responseHeader = new StringBuffer();
            responseHeader.append("Content-Type: application/json; charset=UTF-8" + "\n");
            responseHeader.append("Content-Location: \\error\\" + this.jsonName + ".json" + "\n");

            // メッセージボディ生成
            int length = 0;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.errorJson));
            String temp = "";
            StringBuffer bs = new StringBuffer();
            while ((temp = bufferedReader.readLine()) != null) {
                length += temp.length();
                bs.append(temp);
            }

            responseHeader.append("Content-Length: " + length);
            w.append(statusLine + "\n");
            w.append(responseHeader + "\n");
            w.append("\n");
            w.append(bs);
            w.flush();
            w.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String getValueFromErrorJson(String targetKey) {
        String ret = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.errorJson))) {

            String temp = "";
            String target = "\"" + targetKey + "\"";

            while ((temp = bufferedReader.readLine()) != null) {
                if (temp.indexOf(target) == -1)
                    continue;
                if (targetKey == "status") {
                    ret = temp.substring(temp.indexOf(":") + 2, temp.indexOf(","));
                } else {
                    ret = temp.substring(temp.indexOf(":") + 3, temp.indexOf(",") - 1);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return ret;
    }
}
