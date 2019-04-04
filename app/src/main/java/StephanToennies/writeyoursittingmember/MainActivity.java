package StephanToennies.writeyoursittingmember;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText editTextSubject;
    EditText editText;
    String eMail, subject;
    EditText massageToMember;

    Button sendE_Mail;

    //for local storage
    private static Context context;
    SharedPreferences.Editor editor;
    SharedPreferences settings;

    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for local storage
        context = getApplicationContext();
        settings = context.getSharedPreferences("userdetails", MODE_PRIVATE);
        editor = settings.edit();

        massageToMember = (EditText) findViewById(R.id.inputMessageToSittingMember);
        editTextSubject = (EditText) findViewById(R.id.inputSubject);

        //for triggering the radio buttons
        rg = (RadioGroup) findViewById(R.id.idRadioGroup);
        sendE_Mail = (Button) findViewById(R.id.send_Email);

        sendE_Mail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("Senden geklickt");
                sendE_Mail();
            }
        });

    }

    public void sendE_Mail(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        editTextSubject = (EditText) findViewById(R.id.inputSubject);
        editText = (EditText) findViewById(R.id.inputMessageToSittingMember);
        
        subject = editTextSubject.getText().toString();
        massageToMember = editText;

        intent.setData(Uri.parse(eMail));

        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, massageToMember.toString());


        try {
            startActivity(Intent.createChooser(intent, "E-Mail gesendet."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "Es ist kein E-Mail Klient vorhanden.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setEmail(View v){
        int memberID = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(memberID);

        int id_00 = R.id.id00_MaxMinimus;
        int id_01 = R.id.id01_MaxMustermann;
        int id_02 = R.id.id02_MaxiMusterfrau;
        int id_03 = R.id.id03_MaximilanMustermann;
        int id_04 = R.id.id04_MaxMaximus;

        System.out.println(memberID);

        //find id and set the right e-mail adress
        if(memberID == id_00)
             eMail = "Max.Minimus@exaple.com";
        if(memberID == id_01)
            eMail = "Max.Mustermann@exaple.com";
        if(memberID == id_02)
            eMail = "Maxi.Musterfrau@exaple.com";
        if(memberID == id_03)
            eMail = "Maximilan.Mustermann@exaple.com";
        if(memberID == id_04)
            eMail = "Max.Maximus@exaple.com";

        System.out.println(eMail);
    }


    @Override
    public void onPause() {
        super.onPause();

        // Store the data
        editor.putString("EMail", String.valueOf(eMail));
        editor.putString("to", String.valueOf(eMail));

        editor.putString("subject", String.valueOf(editTextSubject.getText()));
        editor.putString("from", String.valueOf(editTextSubject.getText()));

        editor.putString("message", String.valueOf(massageToMember.getText()));
        editor.putString("concerning", String.valueOf(massageToMember.getText()));


        editor.commit();
    }

    @Override
    public void onStart() {
        super.onStart();

        //load data from storage
        eMail = settings.getString("EMail","");
        eMail = settings.getString("to","");

        editTextSubject.setText(settings.getString("subject",""));
        editTextSubject.setText(settings.getString("from", ""));

        massageToMember.setText(settings.getString("message",""));
        massageToMember.setText(settings.getString("concerning", ""));
    }


}
