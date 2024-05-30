package com.example.btlmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlmobile.R;
import com.example.btlmobile.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class VocabularyAdapter extends  RecyclerView.Adapter<VocabularyAdapter.VocabularyHolder> {
    private List<Vocabulary> listVoca;
    private ItemVocaListener itemVocaListener;


    public void setItemVocaListener(ItemVocaListener itemVocaListener) {
        this.itemVocaListener = itemVocaListener;
    }

    public VocabularyAdapter() {
    }

    public void setListVoca(List<Vocabulary> list) {
        listVoca = new ArrayList<>();
        for(Vocabulary v : list){
            listVoca.add(v);
        }
        notifyDataSetChanged();
    }

    public Vocabulary getVocab(int position){
        return listVoca.get(position);
    }
    @NonNull
    @Override
    public VocabularyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vocabulary_item,parent,false);
        return new VocabularyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyHolder holder, int position) {
        Vocabulary vocabulary = listVoca.get(position);
        holder.tv_word.setText(vocabulary.getWord());
        holder.tv_meaning.setText(vocabulary.getMeaning());
        holder.tv_type.setText(vocabulary.getType());
        holder.tv_accent.setText(vocabulary.getAccent());
    }

    @Override
    public int getItemCount() {
        return listVoca.size();
    }


    public class VocabularyHolder extends RecyclerView.ViewHolder{
        private TextView tv_word,tv_meaning, tv_type,tv_accent;

        public VocabularyHolder(@NonNull View itemView) {
            super(itemView);
            tv_word = itemView.findViewById(R.id.word);
            tv_meaning = itemView.findViewById(R.id.meaning);
            tv_type = itemView.findViewById(R.id.type);
            tv_accent = itemView.findViewById(R.id.accent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemVocaListener != null){
                        itemVocaListener.onItemVocaClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface ItemVocaListener{
        void onItemVocaClick(View view, int position);
    }
}
