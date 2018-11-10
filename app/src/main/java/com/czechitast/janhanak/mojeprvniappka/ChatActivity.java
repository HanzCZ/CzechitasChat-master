package com.czechitast.janhanak.mojeprvniappka;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    private FirebaseListAdapter<ChatMessage> adapter;
    private String name;
    private String barva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FloatingActionButton fab = findViewById(R.id.fab);

        name = getIntent().getStringExtra(Constants.USER_NAME);
        barva = getIntent().getStringExtra("barva");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.input);
                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                // if input is not empty
                String text = input.getText().toString();
                if (!text.isEmpty()) {
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .push()
                            .setValue(new ChatMessage(text,
                                    name,
                                    barva
                            ));
                    // Clear the input
                    input.setText("");
                }
            }
        });

        ListView listOfMessages = findViewById(R.id.list_of_messages);
        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

                Resources resources = v.getResources();
                switch(model.getMessageColor()) {
                    case "modra":
                        messageUser.setTextColor(resources.getColor(R.color.modra));
                        break;
                    case "cervena":
                        messageUser.setTextColor(resources.getColor(R.color.cervena));
                        break;
                    case "zelena":
                        messageUser.setTextColor(resources.getColor(R.color.zelena));
                        break;
                    case "zluta":
                        messageUser.setTextColor(resources.getColor(R.color.zluta));
                        break;
                    case "ruzova":
                        messageUser.setTextColor(resources.getColor(R.color.ruzova));
                        break;
                    default:
                        messageUser.setTextColor(resources.getColor(R.color.cerna));
                        break;
                }

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("HH:mm:ss", //dd-MM-yyyy
                        model.getMessageTime()));
            }
        };
        listOfMessages.setAdapter(adapter);
    }
}
