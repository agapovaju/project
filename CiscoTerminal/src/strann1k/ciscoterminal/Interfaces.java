package strann1k.ciscoterminal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Interfaces extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interfaces);
		String[] data = {"FastEthernet0/0", "FastEthernet0/1", "Vlan1"};
		TextView information = (TextView) findViewById(R.id.informLabel);
		EditText newIP = (EditText) findViewById(R.id.editIP);
		EditText newMask = (EditText) findViewById(R.id.editMask);
		
		information.append( "State: Up \n"+
							"IP: 192.168.0.1/24 \n"+
							"Encapsulation: ARPA");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       
        Spinner spinner = (Spinner) findViewById(R.id.InterfacesSpinner);
        spinner.setAdapter(adapter);
	}
}
