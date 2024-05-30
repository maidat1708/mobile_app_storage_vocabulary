package com.example.btlmobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlmobile.R;
import com.example.btlmobile.model.ContriesCode;
import com.example.btlmobile.model.Translate;

import java.util.List;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.TranslationViewHolder> {

    private List<Translate> translationList;
    private ContriesCode contriesCode = new ContriesCode();
    public  TranslationAdapter(){

    }

    public TranslationAdapter(List<Translate> translationList) {
        this.translationList = translationList;
    }

    @NonNull
    @Override
    public TranslationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_translate, parent, false);
        return new TranslationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TranslationViewHolder holder, int position) {
        Translate translate = translationList.get(position);
        holder.textViewFromLang.setText(ContriesCode.getKeyByValue(contriesCode.getCountryCodeMap(),translate.getFromLang()));
        holder.textViewToLang.setText(ContriesCode.getKeyByValue(contriesCode.getCountryCodeMap(),translate.getToLang()));
        holder.textViewOriginal.setText(translate.getText());
        holder.textViewTranslated.setText(translate.getTranslatedText());
    }

    @Override
    public int getItemCount() {
        return translationList.size();
    }

    public static class TranslationViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFromLang;
        TextView textViewToLang;
        TextView textViewOriginal;
        TextView textViewTranslated;

        public TranslationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFromLang = itemView.findViewById(R.id.textViewFromLang);
            textViewToLang = itemView.findViewById(R.id.textViewToLang);
            textViewOriginal = itemView.findViewById(R.id.textViewOriginal);
            textViewTranslated = itemView.findViewById(R.id.textViewTranslated);
        }
    }
}
