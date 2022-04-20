package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import exception.NotFoundException;
import exception.MethodNotAllowedException;
import handler.ErrorHandler;
import handler.RequestHandler;
import parameter.HttpRequest;
import parameter.HttpResponse;

public class WebAppServer {

    public final static int PortHttp = 8080;
    public final static String HTTP_Ver = "HTTP/1.1";

    public static void main(String[] args) {

        ServerSocket sSocket = null;
        Socket socket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        // サーバー立ち上げ
        while (true) {
            try {
                // IPアドレスとポート番号を指定してサーバー側のソケットを作成
                sSocket = new ServerSocket();
                sSocket.bind(new InetSocketAddress("127.0.0.1", PortHttp));
                System.out.println(
                        "### SERVER IS UP AND RUNNING, WAITING FOR A CLIENT TO CONNECT ON " + PortHttp + " ###");

                // リクエスト待機
                socket = sSocket.accept();

                // IO準備
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // リクエストパラメータ取得
                HttpRequest httpRequest = new HttpRequest(reader);

                // リクエストハンドラ呼び出し
                RequestHandler requestHandler = new RequestHandler(httpRequest);

                // サンプルレスポンス
                HttpResponse httpResponse = new HttpResponse(writer, httpRequest.contentPath, "200");
                httpResponse.sendResponse();

            } catch (Exception e) {

                ErrorHandler errorHandler = new ErrorHandler(e);
                if (writer != null)
                    errorHandler.sendError(writer);

            } finally {

                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                    if (sSocket != null) {
                        sSocket.close();
                    }
                    System.out.println("### SERVER CLOSED ###");
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
