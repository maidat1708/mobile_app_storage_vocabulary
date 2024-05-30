package com.example.btlmobile.api;

import com.example.btlmobile.model.Answer;
import com.example.btlmobile.model.Streak;
import com.example.btlmobile.model.Translate;
import com.example.btlmobile.model.Vocabulary;
import com.example.btlmobile.model.Statistic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("dd-MM-yyy")
            .setLenient()
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.77.147:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("/getbyword")
    Call <List<Vocabulary>> findByWord(@Query("word") String word);

    @GET("/getallansewerbyidlisten/{id}")
    Call <List<Answer>> findByWord(@Path("id") int id);

    @GET("/getbyid/{id}")
    Call <Vocabulary> findById(@Path("id") int id);
    @GET("/practicelearnword/{number}")
    Call <List<Vocabulary>> getRandWord(@Path("number") int number);
    @GET("/selectanswer")
    Call <List<Vocabulary>> getQuestion();
    @GET("/getlast10")
    Call<List<Translate>> getLast10();
    @GET("/getlast20")
    Call<List<Translate>> getLast20();
    @GET("/statistic")
    Call <List<Statistic>> getStatistic();
    @GET("/getstreak")
    Call <Streak> getStreak();

    @PUT("/updatestreak")
    Call <Streak> updateStreak(@Body Streak streak);
    @POST("/addvocabulary")
    Call <Vocabulary> addVocabulary(@Body Vocabulary vocabulary);
    @POST("/translate")
    Call <Translate> translate(@Body Translate translate);
    @PUT("/updatevocabulary")
    Call <Vocabulary> updateVocabulary(@Body Vocabulary vocabulary);

    @DELETE("/deletevocabulary/{id}")
    Call<Void> deleteVocabulary(@Path("id") int id);
}
