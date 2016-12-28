package com.example.kireta.coach.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kireta.coach.modele.Profil;
import com.example.kireta.coach.outils.MesOutils;

import java.util.ArrayList;

import static android.view.LayoutInflater.from;
import com.example.kireta.coach.R;
/**
 * Created by kIRETA on 28/12/2016.
 */

public class HistoListAdapter extends BaseAdapter {
    private ArrayList<Profil> lesProfils ;
    private LayoutInflater inflater ;

    public HistoListAdapter (Context context, ArrayList<Profil> listeDeProfils){
        ArrayList<Profil> lesProfils = listeDeProfils;
        LayoutInflater inflater = from(context);

    }
    @Override
    public int getCount() {
        return lesProfils.size();
    }

    @Override
    public Object getItem(int position) {
        return lesProfils.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        private TextView txtListDate;
        private TextView txtListIMG;
        private ImageButton imgListSuppr;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // holder est un objet de la petite classe
        ViewHolder holder ;
        // si la ligne n'existe pas encore
        if (convertView == null) {
            holder = new ViewHolder() ;
            // la ligne est construite à partir de la structure de la ligne (récupéré dans layout_list_histo)
            convertView = inflater.inflate(R.layout.layout_liste_histo, null) ;
            // chaque propriété de holder (correspondant aux objets graphiques) est liée à un des objets graphiques
            holder.txtListDate = (TextView)convertView.findViewById(R.id.txtListDate) ;
            holder.txtListIMG = (TextView)convertView.findViewById(R.id.txtListIMG) ;
            holder.imgListSuppr = (ImageButton)convertView.findViewById(R.id.imgListSuppr) ;
            holder.imgListSuppr.setImageResource(R.drawable.suppr);
            // affecte un tag (un indice) à la ligne (qui permettra de la repérer facilement)
            convertView.setTag(holder) ;
        }else{
            // si la ligne existe déjà, holder récupère le contenu (à partir de son tag, donc son indice)
            holder = (ViewHolder)convertView.getTag();
        }
        // holder est maintenant lié à la ligne graphique
        // valorisation des propriétés de holder avec le profil de lesProfils (à un indice précis : position)
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(position).getDateMesure())) ;
        holder.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(position).getImg())) ;
        holder.imgListSuppr.setTag(position) ;
        // gestion de l'événement clic sur le bouton de suppression
        holder.imgListSuppr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // code a exécuter sur le clic d'un bouton suppr
            }
        }) ;
        return convertView ;
    }

}
