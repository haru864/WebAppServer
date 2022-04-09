package handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HttpResponse {

    // ソケットのoutputストリーム
    BufferedWriter responseWriter;

    // レスポンスステータスライン
    public final String HTTP_VERSION = "HTTP/1.1";
    public String statusCode;
    public String reasonPhrase;
    public StringBuilder statusLine;

    // レスポンスヘッダー
    public String contentType;
    public String contentLength;
    public StringBuilder header;

    // レスポンスボディ
    public StringBuilder messageBody;

    public HttpResponse(BufferedWriter writer, String contentPath, String statusCode) throws IOException {

        // ステータスライン生成
        this.responseWriter = writer;
        this.statusCode = statusCode;
        this.reasonPhrase = ReasonPhrase.REASON_PHRASE_MAP.get(this.statusCode);
        this.statusLine = new StringBuilder();
        this.statusLine.append(this.HTTP_VERSION + " " + this.statusCode + " " + this.reasonPhrase);

        // ヘッダー生成
        int index = contentPath.lastIndexOf(".");
        String extension = contentPath.substring(index + 1);
        this.contentType = "Content-Type: " + ContentType.CONTENT_TYPE_MAP.get(extension);
        this.contentLength = "Content-Length: ";

        // レスポンスボディ
        this.messageBody = new StringBuilder();
        File responseFile = new File("./bin/content" + contentPath);
        FileReader fileIn = new FileReader(responseFile);
        int n = 0;
        int length = 0;
        while ((n = fileIn.read()) != -1) {
            length++;
            messageBody.append((char) n);
        }

        this.header = new StringBuilder();
        this.contentLength += length;
        this.header.append(this.contentType + "\n");
        this.header.append(this.contentLength + "\n");
    }

    // レスポンス送信メソッド
    public void sendResponse() throws IOException {
        StringBuilder response = new StringBuilder();
        response.append(this.statusLine + "\n");
        response.append(this.header);
        response.append("\n");
        response.append(this.messageBody);
        responseWriter.write(response.toString());
        responseWriter.flush();
    }
}
