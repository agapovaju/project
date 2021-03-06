package com.example.serverclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
 
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
 
public class MainActivity extends Activity {
   private Button bt;
   private TextView tv;
   private Socket socket;
   private String serverIpAddress = "10.0.2.2";
   // AND THAT'S MY DEV'T MACHINE WHERE PACKETS TO
   // PORT 5000 GET REDIRECTED TO THE SERVER EMULATOR'S
   // PORT 6000
   private static final int REDIRECTED_SERVERPORT = 5000;
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      bt = (Button) findViewById(R.id.myButton);
      tv = (TextView) findViewById(R.id.myTextView);
      try {
         InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
         socket = new Socket(serverAddr, REDIRECTED_SERVERPORT);
      } catch (UnknownHostException e1) {
         e1.printStackTrace();
      } catch (IOException e1) {
         e1.printStackTrace();
      }
      bt.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            try {
               EditText et = (EditText) findViewById(R.id.EditText01);
               String str = et.getText().toString();
               PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
               out.println(str);
               Log.d("Client", "Client sent message");
            } catch (UnknownHostException e) {
               tv.setText("Error1");
               e.printStackTrace();
            } catch (IOException e) {
               tv.setText("Error2");
               e.printStackTrace();
            } catch (Exception e) {
               tv.setText("Error3");
               e.printStackTrace();
            }
         }
      });
   }
}
