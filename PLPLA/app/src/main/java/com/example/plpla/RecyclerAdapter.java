package com.example.plpla;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "RecyclerAdapter";
    //List<String> donnes;
    /////////////////////////
    List<Matieres> donnes;
    List<Matieres> toutdonnes;

    private SelectedMatiere selectedMatiere;

    ///////////////////////////
    //List<String> toutdonnes;


    public RecyclerAdapter(List<Matieres> donnes,SelectedMatiere selectedMatiere) {
        this.donnes = donnes;
        this.toutdonnes=new ArrayList<>(donnes);
        this.selectedMatiere = selectedMatiere;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder:");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {

        /////////////////////////////////////////
        Matieres matieres= donnes.get(position);
        holder.name.setText(matieres.getName());


        ///////////////////////////////////////////

        holder.rowCountTextView.setText(String.valueOf(position));
        //holder.textView.setText(donnes.get(position));

    }


    @Override
    public int getItemCount() {
        return donnes.size();
    }

    @Override
    public Filter getFilter(){
        return filter;
    }

    Filter filter=new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSaquence) {
            //List<String> filteredList = new ArrayList<>();

            //////////////////////////////////////////////
            List<Matieres> filteredList = new ArrayList<>();
            ///////////////////////////////////////////////

            if (charSaquence.toString().isEmpty())
            {
                filteredList.addAll(toutdonnes);
            }
            else
            {
                for (/*String*/Matieres matiere : toutdonnes) {
                    if (matiere.getName().toLowerCase().contains(charSaquence.toString().toLowerCase())) {
                        filteredList.add(matiere);
                    }
                }

            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            donnes.clear();
            donnes.addAll((Collection<? extends /*String*/ Matieres>)filterResults.values);
            notifyDataSetChanged();
        }
    };




    public interface SelectedMatiere{

        void selectedMatiere(Matieres userModel);

    }

    class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

        ////////////////////////////
        View view;
        TextView name;
        ///////////////////////////
        ImageView imageView;
        TextView textView, rowCountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //view.setVisibility(View.GONE);

            ////////////////////////
            name=itemView.findViewById(R.id.textView);
            ///////////////////////////////////////////

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedMatiere.selectedMatiere(donnes.get(getAdapterPosition()));
                }
            });



            //itemView.setOnClickListener(this);
        }
//        @Override
//        public void onClick(View view){
//            //Toast.makeText(view.getContext(),donnes.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();
//            /////////////////////////////////////
//            Toast.makeText(view.getContext(), (CharSequence) donnes.get(getAdapterPosition()),Toast.LENGTH_SHORT).show();
//            ///////////////////////////////////////
//
//        }
    }
}
