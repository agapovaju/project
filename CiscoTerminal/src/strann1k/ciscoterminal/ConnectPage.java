package strann1k.ciscoterminal;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnectPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect_page);
		EditText editIP=(EditText) findViewById(R.id.editIP);
		registerForContextMenu(editIP);
		Button connectBut=(Button) findViewById(R.id.connectButton);
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

}
