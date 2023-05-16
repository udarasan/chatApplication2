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

public class ClientController {
    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtMessage;

    String message="";
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    public void initialize(){
        new Thread(() -> {
            try {

                socket=new Socket("localhost",5000);
                dataInputStream=new DataInputStream(socket.getInputStream());
                dataOutputStream=new DataOutputStream(socket.getOutputStream());
                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    txtArea.appendText("\nServer: "+message);
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
