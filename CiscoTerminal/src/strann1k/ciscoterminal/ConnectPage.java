package strann1k.ciscoterminal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.net.*;



public class ConnectPage extends Activity {

	public EditText server_ip;
	public EditText server_port;
	
	public TextView ip_s;
	public TextView port_s;
	public String result_mess = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect_page);
		server_ip=(EditText) findViewById(R.id.editIP);
		server_port=(EditText)findViewById(R.id.editPort);
		registerForContextMenu(server_ip);
		Button connectBut=(Button) findViewById(R.id.connectButton);
		connectBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent toWP = new Intent(ConnectPage.this, WorkActivity.class);
				toWP.putExtra("Key_port", server_port.getText().toString());
				toWP.putExtra("Key_address", server_ip.getText().toString());
				startActivity(toWP);
			}
		});
	}
	
	//Создание коснтекстного меню для строки IP-адреса
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.connectpage, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId()==R.id.item_copy){
			//дописать копирование в буфер обмена
		}
		if (item.getItemId()==R.id.item_paste){
			//дописать вставку из буфера обмена
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connect_page, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId()==R.id.item_quit){
			ConnectPage.this.finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
