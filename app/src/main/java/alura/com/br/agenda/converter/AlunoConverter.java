package alura.com.br.agenda.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import alura.com.br.agenda.modelo.Aluno;

/**
 * Created by Pedro Paulo on 29/12/2017.
 */

// Classe responsável por converter a lista de alunos em JSON

public class AlunoConverter {


    public String converterParaJSON(List<Aluno> alunos) {
        JSONStringer js = new JSONStringer(); // Espécie de concatenador

        try {

            js.object().key("list").array().object().key("aluno").array();
            for(Aluno aluno : alunos){
                js.object();
                js.key("nome").value(aluno.getNome());
                js.key("nota").value(aluno.getNota());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }


}
