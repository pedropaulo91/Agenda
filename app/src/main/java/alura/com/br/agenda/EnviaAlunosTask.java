package alura.com.br.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import alura.com.br.agenda.converter.AlunoConverter;
import alura.com.br.agenda.dao.AlunoDAO;
import alura.com.br.agenda.modelo.Aluno;

/**
 * Created by Pedro Paulo on 29/12/2017.
 */


// Classe responável por realizar operações fora da UI Thread (Thread principal do android)

public class EnviaAlunosTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog dialog;


    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    // Método executado na Thread principal do Android
    // Método usado para operações antes do método doInBackground() ser executado
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context,"Aguarde", "Enviando alunos...", true, true);

    }

    @Override
    protected String doInBackground(Void... params) {

        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close(); // OBS: SEMPRE LEMBRAR DE FECHAR O BANCO

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converterParaJSON(alunos);

        WebClient client = new WebClient();
        String resposta = client.post(json);
        return resposta;
    }


    // Método executado na Thread principal do Android
    // É executado depois do método doInBackground()
    // Parâmetro Object é o retorno do método doInBackground()
    @Override
    protected void onPostExecute(String resposta) {
        super.onPostExecute(resposta);
        dialog.dismiss(); // Fecha o ProgressDialog após a execução do método doInBackground
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();

    }
}
