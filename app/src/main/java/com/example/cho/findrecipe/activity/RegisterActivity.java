package com.example.cho.findrecipe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.cho.findrecipe.Dto.Having;
import com.example.cho.findrecipe.R;
import com.example.cho.findrecipe.repository.CookingPrepareRepository;
import com.example.cho.findrecipe.service.CookingPrepareService;

/**
 * Created by cho on 2018-01-19.
 */

public class RegisterActivity extends AppCompatActivity {

    private CookingPrepareService cookingPrepareService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        cookingPrepareService = new CookingPrepareService(new CookingPrepareRepository());

        Intent intent = getIntent();
        String message = intent.getStringExtra("having");

        Having selected = Having.findByName(message);

        Button saveButton = findViewById(R.id.register_save_button);

        EditText inputEditText = findViewById(R.id.register_input_edit_text);

        registerSaveButtonClickListener(saveButton, selected, inputEditText);

        Button cancelButton = findViewById(R.id.register_cancel_button);
        registerCancelButtonClickListener(cancelButton);

    }

    private void registerCancelButtonClickListener(Button cancelButton) {
        cancelButton.setOnClickListener(view -> finish());
    }

    private void registerSaveButtonClickListener(Button saveButton, Having having, EditText inputEditText) {
        saveButton.setOnClickListener(view -> {

            String input = inputEditText.getText().toString();

            cookingPrepareService.save(input, having, false);

            finish();
        });
    }
}
