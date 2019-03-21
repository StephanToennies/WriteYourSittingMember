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
    }

    public void sendE_Mail(){
        editTextSubject = (EditText) findViewById(R.id.inputSubject);
        editText = (EditText) findViewById(R.id.inputMessageToSittingMember);

        sendE_Mail = (Button) findViewById(R.id.send_Email);

        sendE_Mail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_SEND);
                
                subject = editTextSubject.getText().toString();
                massageToMember = editText.getText().toString();

                //
                intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");

                intent.putExtra(Intent.EXTRA_EMAIL, eMail);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, massageToMember);

                startActivity(Intent.createChooser(intent, "E-Mail gesendet."));

            }
        });


    }

    public void setEmail(View v){
        int memberID = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(memberID);

        String id_rb = rb.getText().toString();

        if(id_rb.equals("id00_MaxMinimus"))
             eMail = "Max.Minimus@exaple.com";
        if(id_rb.equals("id01_MaxMustermann"))
            eMail = "Max.Mustermann@exaple.com";
        if(id_rb.equals("id02_MaxiMusterfrau"))
            eMail = "Maxi.Musterfrau@exaple.com";
        if(id_rb.equals("id03_MaximilanMustermann"))
            eMail = "Maximilan.Mustermann@exaple.com";
        if(id_rb.equals("id04_MaxMaximus"))
            eMail = "Max.Maximus@exaple.com";
    }
}
