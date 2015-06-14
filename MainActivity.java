package com.example.android.spidertask3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {

    public void Timer(final int timeinsec)
    {
        final TextView t=(TextView)findViewById(R.id.textView);
        t.setText("" + timeinsec);
        CountDownTimer countDownTimer=new CountDownTimer(timeinsec*1000,1000){
            public void onTick(long millsuntilfinished)
            {
                t.setText(""+millsuntilfinished/1000);
            }
            public void onFinish(){
                OpenWebPage openWebPage=new OpenWebPage();
                openWebPage.execute("http://spider.nitt.edu/~vishnu/time.php" );
                return;
            }

        };
        countDownTimer.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer(10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public class OpenWebPage extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... urls)
    {
        String response="";
        for(String url:urls){
            DefaultHttpClient httpClient=new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(url);
            try{
                HttpResponse execute=httpClient.execute(httpGet);
                InputStream content=execute.getEntity().getContent();
                BufferedReader buffer=new BufferedReader(new InputStreamReader(content));
                String s="";
                while((s=buffer.readLine())!=null){
                    response+=s;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        return response;
    }
        @Override
        protected void onPostExecute(String result){
            TextView t2=(TextView)findViewById(R.id.textView2);
            String temp=result.substring(result.length()-1);
            int l=Integer.parseInt(temp);
            t2.setText(result);
            Timer(l);
        }


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
    public void ExitApp(View view)
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
