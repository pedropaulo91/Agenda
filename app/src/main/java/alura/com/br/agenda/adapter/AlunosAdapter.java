package alura.com.br.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import alura.com.br.agenda.ListaAlunosActivity;
import alura.com.br.agenda.R;
import alura.com.br.agenda.modelo.Aluno;

/**
 * Created by Pedro Paulo on 28/12/2017.
 */

public class AlunosAdapter extends BaseAdapter {


    private final List<Aluno> alunos;
    private final Context context;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    // Retorna a quantidade de itens da lista
    @Override
    public int getCount() {
        return alunos.size();
    }

    // Retorna uma item da lista
    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }


    // Retorna o id do item da lista
    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }


    // Constrói a View
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Pega um aluno da lista na posicão fornecida
        Aluno aluno = alunos.get(position);

        // Reaproveitamento das views com a convertView
        View view = convertView; // Uso da convertView por questões de desempenho de exibição da lista
        LayoutInflater inflater = LayoutInflater.from(context);
        if(convertView == null) {
            // Infla o layout personalizado
            view = inflater.inflate(R.layout.list_item, parent, false); // Explicação sobre o 3º argumento desse método está na aula 3 do 2º curso de Android
        }

        // Pega a referência do TextView
        TextView campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());


        // É preciso verificar se os TextViews não são nulos para não tentar
        // alterá-los durante a exibição do layout na orientação padrão (Portrait).
        // Nessa ortientação esses TextViews ainda não existem.
        // Esse TextViews só existem na orientação Landscape.
        TextView campoEndereco = (TextView) view.findViewById(R.id.item_endereco);
        if(campoEndereco != null) {
            campoEndereco.setText(aluno.getEndereco());
        }

        TextView campoSite = (TextView) view.findViewById(R.id.item_site);
        if(campoSite != null) {
            campoSite.setText(aluno.getSite());
        }



        // Pega a referência do ImageView
        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido); // Coloca a foto tirada no ImageView
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);

        }

        return view;
    }



}
