package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import handler.RequestHandler;

public class WebAppServer {

    public final static int PortHttp = 8080;

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

                // リクエスト読み込み
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // ヘッダ取得
                String line;
                StringBuilder header = new StringBuilder();
                int contentLength = 0;

                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    if (line.indexOf("Content-Length:") == 0) {
                        contentLength = Integer.parseInt(line.substring(line.indexOf(":") + 2));
                    }
                    header.append(line + "\n");
                }
                System.out.println("-------header-------\n" + header +
                        "--------------------\n");

                // ボディ取得
                StringBuilder body = new StringBuilder();
                if (0 < contentLength) {
                    char[] c = new char[contentLength];
                    reader.read(c);
                    body.append(new String(c));
                }
                System.out.println("-------body-------\n" + body + "\n" + "--------------------\n");

                // リクエストハンドラ呼び出し
                // var requestHandler = new RequestHandler(header, body, contentLength);

                // サンプルレスポンス
                // PrintWriter w = new PrintWriter(
                // socket.getOutputStream(), true);
                // w.print("HTTP/1.1 200 OK \n");
                // w.print("Content-Type: text/html \n");
                // w.print("\n");
                // w.print("<h1>Hello World!!</h1>");
                // w.flush();
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write("HTTP/1.1 200 OK" + "\n");
                writer.write("Content-Type: text/html" + "\n");
                writer.write("\n");
                writer.write("<h1>Hello World!!</h1>");
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
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
