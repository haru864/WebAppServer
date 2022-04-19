package handler;

import java.io.BufferedWriter;

public class ErrorHandler {

    public Exception exception;
    public String exceptionClassName;

    public ErrorHandler(Exception e) {

        // スタックトレースをコンソールに表示する
        e.printStackTrace();

        // クラス名を取得する
        String temp = e.toString();
        int n = temp.indexOf(".");
        int m = temp.indexOf(":");
        this.exceptionClassName = temp.substring(n + 1, m);

        this.exception = e;
    }

    public void sendError(BufferedWriter w) {
        // 例外クラスとステータスコードを紐づけるクラスを用意する
        // ⇒ステータスコードとエラーコンテンツのパスを紐づける
        // このメソッドで必要なのは、以下の値。
        // ・status
        // ・reason phrase
        // ・message
    }
}
