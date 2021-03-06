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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.IOException;
import java.net.*;



public class ConnectPage extends Activity {

	public EditText server_ip;
	public EditText server_port;
	public Socket socket;
	private String ip_address;
	private Integer port;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect_page);
		String[] data = {"TELNET", "SSH"};
		server_ip=(EditText) findViewById(R.id.editIP);
		server_port=(EditText)findViewById(R.id.editPort);
		registerForContextMenu(server_ip);
		registerForContextMenu(server_port);
		
		// �������
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       
        Spinner spinner = (Spinner) findViewById(R.id.protocolSpinner);
        spinner.setAdapter(adapter);
       
		
		Button connectBut=(Button) findViewById(R.id.connectButton);
		//��������� ������� �� ������ �����������
		connectBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ip_address=server_ip.getText().toString();
				port=Integer.parseInt(server_port.getText().toString());
								
				//�������� ������ � ����� � WorkActivity
				Intent toWA = new Intent(ConnectPage.this, WorkActivity.class);
				toWA.putExtra("Key_port", port);
				toWA.putExtra("Key_address", ip_address);
				startActivity(toWA);
			}
		});
	}
	
	//�������� ������������� ���� ��� ������ IP-������ � �����
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
			//�������� ����������� � ����� ������
		}
		if (item.getItemId()==R.id.item_paste){
			//�������� ������� �� ������ ������
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
		if (item.getItemId()==R.id.item_IntPage){
			Intent toIntPage = new Intent(ConnectPage.this, Interfaces.class);
			startActivity(toIntPage);
		}
		return super.onOptionsItemSelected(item);
	}
}
