package StephanToennies.writeyoursittingmember;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText editTextSubject;
    EditText editText;
    String eMail, subject, massageToMember;

    Button sendE_Mail;

    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent intent = new Intent(Intent.ACTION_SEND);

        editTextSubject = (EditText) findViewById(R.id.inputSubject);
        editText = (EditText) findViewById(R.id.inputMessageToSittingMember);
        
        subject = editTextSubject.getText().toString();
        massageToMember = editText.getText().toString();

        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_EMAIL, eMail);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, massageToMember);



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
        
        /*
        switch(memberID){
            case 0: eMail = "Max.Minimus@exaple.com"; break;
            case 1: eMail = "Max.Mustermann@exaple.com"; break;
            case 2: eMail = "Maxi.Musterfrau@exaple.com"; break;
            case 3: eMail = "Maximilan.Mustermann@exaple.com"; break;
            case 4: eMail = "Max.Maximus@exaple.com"; break;
            default: break;
        }
        */

        int id_00 = R.id.id00_MaxMinimus;
        int id_01 = R.id.id01_MaxMustermann;
        int id_02 = R.id.id02_MaxiMusterfrau;
        int id_03 = R.id.id03_MaximilanMustermann;
        int id_04 = R.id.id04_MaxMaximus;

        System.out.println(memberID);

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

}
