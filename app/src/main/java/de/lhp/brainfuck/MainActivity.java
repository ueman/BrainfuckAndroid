/**
 * Copyright 2014 Jonas Uekoetter
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,software
 * distributed under the License is distributed on an"AS IS"BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package de.lhp.brainfuck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements BrainfuckEngine.BrainfuckInterface, View.OnClickListener{

    private TextView consoleOutput;
    private EditText consoleInput;
    private EditText sourceCodeEditText;
    private BrainfuckEngine brainfuckEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consoleInput = (EditText) findViewById(R.id.input_edittext);
        consoleOutput = (TextView) findViewById(R.id.output_textview);
        sourceCodeEditText = (EditText) findViewById(R.id.source_code_edittext);
        sourceCodeEditText = (EditText) findViewById(R.id.source_code_edittext);
        brainfuckEngine = new BrainfuckEngine(500, this);
        View compileButton = findViewById(R.id.compile_button);
        compileButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // must be implemented because of the brainfuckinterface
    @Override
    public char readChar() {
        // get first char of the console
        // no checks, yolo
        // todo make proper sanity checks for the input "console"
        if(consoleInput.getText().toString().isEmpty()){
            return 0;
        }
        return consoleInput.getText().charAt(0);
    }

    // must be implemented because of the brainfuckinterface
    @Override
    public void writeChar(char c) {
        consoleOutput.append(""+c);
    }

    // must be implemented because of the brainfuckinterface
    @Override
    public void error(String errorMessage){
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View origin){
        //clear console
        consoleOutput.setText("");
        try {
            brainfuckEngine.interpret(sourceCodeEditText.getText().toString());
        }catch(BrainfuckEngine.BrainfuckException e){
            // never do anything with "e" because it doesnt throw exceptions on android
        }catch(IOException e){
            // never do anything with "e" because it doesnt throw exceptions on android
        }
    }
}
