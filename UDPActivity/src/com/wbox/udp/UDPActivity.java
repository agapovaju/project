package com.wbox.udp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.*;

public class UDPActivity extends Activity {
    /** Called when the activity is first created. */
	public EditText server_ip;
	public EditText server_port;
	public EditText mess;
	public TextView ip_s;
	public TextView port_s;
	public String result_mess = "";
    @Override
    public void onCreate(Bundle  savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        server_ip=(EditText)findViewById(R.id.editText1);
        server_port=(EditText)findViewById(R.id.editText2);
        mess=(EditText)findViewById(R.id.editText3);
        ip_s=(TextView)findViewById(R.id.textView1);
        port_s=(TextView)findViewById(R.id.textView2);
        new Thread(new server()).start();
        try {
        	Thread.sleep(500);
        }catch (InterruptedException e){}
    }
    public void send_btn(View v){
    try
    {
    	InetAddress serv_addr= InetAddress.getByName(server_ip.getText().toString());
    	int port= Integer.parseInt(server_port.getText().toString());
    	DatagramSocket sock = new DatagramSocket();
    	byte [] buf = ("c: Hello from android").getBytes();
    	DatagramPacket pack= new DatagramPacket(buf, buf.length,serv_addr,port);
    	sock.send(pack);
    	sock.close();
    	for (int i=0; i<buf.length;i++) buf[i]=0;
    }
    catch (Exception e){
    	Log.d("UDP", "Error: "+e);
    }
    }
    public class server implements Runnable{
    	@Override
    	public void run () {
    		try{
    			WifiManager wifiManager=(WifiManager)getSystemService(WIFI_SERVICE);
    			WifiInfo wifiinfo= wifiManager.getConnectionInfo();
    			int ip_adress=wifiinfo.getIpAddress();
    			String newip= String.format("%d.%d.%d.%d", (ip_adress & 0xff),
    					(ip_adress>>8 & 0xff),
    					(ip_adress>>16 & 0xff),
    					(ip_adress>>24 & 0xff));
    			mess.append("Set ip: "+newip+"\n");
    			int port=1254;
    			ip_s.setText(newip);
    			port_s.setText(Integer.toString(port));
    			mess.append("Set port: "+Integer.toString(port)+"\n");
    			InetAddress serv_address = InetAddress.getByName(newip);
    			DatagramSocket serversocket = new DatagramSocket(port,serv_address);
    			mess.append("Create socket \n");
    			byte [] buf = new byte[17];  	
    			while(true) {
    				DatagramPacket packet= new DatagramPacket(buf, buf.length);
    				serversocket.receive(packet);
    				result_mess=new String(packet.getData(),0,packet.getData().length);
     				mess.post(new Runnable() {
						@Override
						public void run() {
		    				mess.append("Create datagramPacket \n");
		    				mess.append("Wait for packet ....\n");
							mess.append(result_mess);
						}
					});
    				for (int i=0;i<buf.length;i++) buf[i]=0;
    			}
    			}
    		catch (Exception e){
    			mess.append("Error: "+e);
    		}
    	}
    }
}
