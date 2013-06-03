package strann1k.ciscoterminal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class WorkActivity extends Activity {
	String address= getIntent().getExtras().getString("address");
	String port =getIntent().getExtras().getString("port");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work);
		EditText msg=(EditText) findViewById(R.id.editSend);
		
		Integer port = getIntent().getExtras().getInt("port");
		try {
			try {
			DatagramSocket sock = new DatagramSocket();
			byte [] buf= msg.getText().toString().getBytes();
			DatagramPacket pack= new DatagramPacket(buf,buf.length,InetAddress.getByName(address),port);
			try {
				sock.send(pack);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sock.close();
			} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_work, menu);
		return true;
	}

}
