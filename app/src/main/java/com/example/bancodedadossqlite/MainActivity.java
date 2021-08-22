package com.example.bancodedadossqlite;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //Criando Banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criar tabelas
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome Varchar(50),idade INT(3))");

            //Insere dados
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('Guilherme', '26')");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES('teste', '102')");


            //Recuperar dados
            //bancoDados.execSQL("SELECT * FROM pessoas");
            String consulta = "SELECT id, nome,idade FROM pessoas where 1 = 1 ";
            Cursor cursor = bancoDados.rawQuery( consulta, null);

            //Indices da tabela

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            int indiceId = cursor.getColumnIndex("id");

            cursor.moveToFirst();
            while (cursor != null){

                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
               //
                Log.i("Resultado - id ", id + " nome: "+  nome + "/ idade: " + idade);

               //Log.i("Resultado - nome: ",  nome + "/ idade: " + idade);
               // Log.i("Resultado - idade: ", );
                cursor.moveToNext();

            }

        }catch (Exception e){

            e.printStackTrace();

        }
    }
}