package game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Location {

    ServerSocket serverSocket;

    public void startUpdateLocation() {
        try {
            serverSocket = new ServerSocket(8080);

            // 开始服务端监听
            Socket socket1 = serverSocket.accept();
            // 获取输入流
            InputStream inputStream = socket1.getInputStream();
            // 解析数据
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String location = null;
            StringBuffer sb = new StringBuffer();

            while ((location = bufferedReader.readLine()) != null) {
                sb.append(location);
            }
            socket1.shutdownInput();

            OutputStream outputStream = socket1.getOutputStream();
            outputStream.write("shoudaoxiaoxi".getBytes(StandardCharsets.UTF_8));

            outputStream.flush();
            socket1.shutdownOutput();
            outputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
            inputStream.close();

            socket1.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
