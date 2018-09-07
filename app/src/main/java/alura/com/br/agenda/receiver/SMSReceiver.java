package alura.com.br.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import alura.com.br.agenda.R;
import alura.com.br.agenda.dao.AlunoDAO;

/**
 * Created by Pedro Paulo on 28/12/2017.
 */

public class SMSReceiver extends BroadcastReceiver {

    //Os sms são enviados em formato PDU (string de bytes)

    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus"); // Caso o SMS exceda o nº máximo de caracteres, a mensagem é quebrada em partes menores.
        byte[] pdu = (byte[]) pdus[0]; // Pega a 1º PDU e a converte para um array de bytes
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);

        if(dao.ehAluno(telefone)){
            Toast.makeText(context, "Chegou um SMS de Aluno!", Toast.LENGTH_SHORT).show();
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }
        dao.close(); // OBS: SEMPRE LEMBRAR DE FECHAR O BANCO

    }

}
