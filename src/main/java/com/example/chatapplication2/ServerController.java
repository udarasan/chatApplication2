package com.example.chatapplication2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtMessage;

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message="";
    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket=new ServerSocket(5000);
                txtArea.appendText("Server Started");
                socket=serverSocket.accept();
                txtArea.appendText("\nClient Connected..");
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());
                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    txtArea.appendText("\nClient: "+message);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    @FXML
    void sendOnAction(ActionEvent event) throws IOException {

        dataOutputStream.writeUTF(txtMessage.getText().trim());
        dataOutputStream.flush();
    }
}
