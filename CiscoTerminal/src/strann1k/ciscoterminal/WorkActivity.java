package strann1k.ciscoterminal;

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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WorkActivity extends Activity {
	
	Socket socket = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work);
		
		
		
		String old_text;
		String address= getIntent().getExtras().getString("Key_address");
		String port =getIntent().getExtras().getString("Key_port");
		EditText editCmd = (EditText) findViewById(R.id.editSend);
		Button sendButton= (Button) findViewById(R.id.sendButton);
		TextView msgList= (TextView) findViewById(R.id.textReply);
		InetAddress serverAddr;
		
		try {
			serverAddr = InetAddress.getByName(address);
			socket = new Socket(serverAddr, Integer.parseInt(port));
		} catch (UnknownHostException e1) {
			editCmd.setText("Socket Create 1");
			e1.printStackTrace();
		}  catch (IOException e) {
			editCmd.setText("Socket Create 2");
			e.printStackTrace();
		}
		sendButton.setOnClickListener(new OnClickListener() {					
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
				String sndMsg= editCmd.getText().toString();
				PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
				out.println(sndMsg);
				msgList.append(sndMsg);
				Log.d("Client", "Client sent message");
				} catch (UnknownHostException e) {
					editCmd.setText("Send msg 1");
		               e.printStackTrace();
		            } catch (IOException e) {
		            	editCmd.setText("Send msg 2");
		               e.printStackTrace();
		            } catch (Exception e) {
		            	editCmd.setText("Send msg 3");
		               e.printStackTrace();
		            }
			
			}
		});
		
		}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_work, menu);
		return true;
	}

}
