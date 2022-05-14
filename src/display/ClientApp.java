package display;

public class ClientApp {

    public static String[] STATUS_LIST = new String[] { "Outage", "Running" }; // 0->停止中、1->稼働中
    public static String SERVER_STATUS;

    public static void main(String[] args) {

        // サーバーステータスの初期化
        SERVER_STATUS = new String(STATUS_LIST[0]);

        // 操作画面を表示
        new ClientFrame();
    }
}
