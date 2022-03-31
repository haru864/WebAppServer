package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.Arrays;

public class WebAppServer {

    public final static int PortHttp = 8080;
    public static ServerSocket server;

    public static void main(String[] args) throws Exception {

        // サーバー立ち上げ
        try {
            // サーバソケット生成
            server = new ServerSocket(PortHttp);
            System.out.println("### SERVER IS UP AND RUNNING, WAITING FOR A CLIENT TO CONNECT ON " + PortHttp + " ###");

            while (true) {
                // リクエスト待機
                var requestSocket = server.accept();

                // リクエスト読み込み
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(requestSocket.getInputStream()));

                // ヘッダ取得
                String line;
                StringBuilder header = new StringBuilder();
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    header.append(line + "\n");
                }
                System.out.println(header);

                // ボディ取得
                StringBuilder body = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    body.append(line);
                }
                System.out.println(body);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
