package com.example.btlmobile.adapter;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.btlmobile.R;
import com.example.btlmobile.model.Vocabulary;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LearnWordAdapter extends RecyclerView.Adapter<LearnWordAdapter.LearnWordViewHolder> {

    private List<Vocabulary> listVocab,practiceWord;
    private ItemPracticeWordListener itemPracticeWordListener;
    private boolean isOnTextChange = false;

    public void setItemPracticeWordListener(ItemPracticeWordListener itemPracticeWordListener) {
        this.itemPracticeWordListener = itemPracticeWordListener;
    }

    public List<Vocabulary> getListVocab() {
        return listVocab;
    }

    public List<Vocabulary> getPracticeWord() {
        return practiceWord;
    }

    public String checkSubmit(){
        int cnt = 0;
        for(int i = 0 ; i < listVocab.size();i++){
            System.out.println(listVocab.get(i).toString());
            System.out.println(practiceWord.get(i).toString());
            if(listVocab.get(i).equals(practiceWord.get(i))){
                cnt ++;
            }
        }
        return String.valueOf(cnt);
    }
    public void setListVoca(List<Vocabulary> list) {
        listVocab = new ArrayList<>();
        practiceWord = new ArrayList<>();
        for(Vocabulary v : list){
            listVocab.add(v);
        }
        for(Vocabulary v : list){
            Vocabulary vocabulary = new Vocabulary(v.getId(),v.getWord(),v.getMeaning(),v.getType(),v.getAccent(),v.getImage(),v.getSentence());
            practiceWord.add(vocabulary);
        }
        System.out.println(listVocab.size());
        notifyDataSetChanged();
    }

    public void setVocabPractiveWordType(String type,int position){
        practiceWord.get(position).setType(type);
    }

    public void setVocabPractiveWordMeaning(String meaning,int id){
       for(Vocabulary v : practiceWord){
           if(v.getId() == id){
               v.setMeaning(meaning);
               break;
           }
       }
    }
    @NonNull
    @Override
    public LearnWordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.learnword_item,parent,false);
        return new LearnWordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnWordViewHolder holder, int position) {
        Vocabulary vocabulary = listVocab.get(position);
        holder.text_view_word.setText(vocabulary.getWord());
        holder.edit_text_meaning.setText("");
        holder.sp.setSelection(0);
        holder.edit_text_meaning.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isOnTextChange = true;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isOnTextChange){
                    isOnTextChange = false;
                    setVocabPractiveWordMeaning(editable.toString(),vocabulary.getId());
                }
            }
        });
//        int p = 0;
//        for(int i = 0 ; i < holder.sp.getCount(); i++){
//            if(holder.sp.getItemAtPosition(i).toString().equalsIgnoreCase(vocabulary.getType())){
//                p = i;
//                break;
//            }
//        }
        holder.sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setVocabPractiveWordType(adapterView.getItemAtPosition(i).toString(),position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listVocab.size();
    }

    public class LearnWordViewHolder extends RecyclerView.ViewHolder{
        private TextView text_view_word;
        private EditText edit_text_meaning;
        private Spinner sp;
        public LearnWordViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view_word = itemView.findViewById(R.id.word);
            edit_text_meaning = itemView.findViewById(R.id.meaning);
            sp = itemView.findViewById(R.id.spinner_learnword);
            sp.setAdapter(new ArrayAdapter<String>(itemView.getContext(), R.layout.item_spinner,itemView.getResources().getStringArray(R.array.type)));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemPracticeWordListener != null){
                        itemPracticeWordListener.onItemPracticeWordClick(view,getAdapterPosition());
                    }
                }
            });
        }
    }
    public interface ItemPracticeWordListener{
        void onItemPracticeWordClick(View view, int position);
    }

}
