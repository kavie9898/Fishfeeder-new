package com.fishfeeder_new;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

import com.fishfeeder_new.user.HttpServicesClass;
import com.fishfeeder_new.user.ListAdapterClass;
import com.fishfeeder_new.user.User;

public class AdminManageUser extends AppCompatActivity {
    ListView StudentListView;
    ProgressBar progressBar;
    //String HttpUrl = "http://192.168.0.130/android/crud_user/AllUserData.php";
    //String HttpUrl = "http://192.168.0.125/android/crud_user/AllUserData.php";
    String HttpUrl = "http://10.0.2.2/android/crud_user/AllUserData.php";
    List<String> IdList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_user);
        StudentListView = (ListView)findViewById(R.id.listview1);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        new GetHttpResponse(AdminManageUser.this).execute();

        //Adding ListView Item click Listener.
        StudentListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(AdminManageUser.this,AdminShowUser.class);

                // Sending ListView clicked value using intent.
                intent.putExtra("ListViewValue", IdList.get(position).toString());

                startActivity(intent);

                //Finishing current activity after open next activity.
                finish();

            }
        });
    }

    // JSON parse class started from here.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String JSonResult;

        List<User> userList;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            // Passing HTTP URL to HttpServicesClass Class.
            HttpServicesClass httpServicesClass = new HttpServicesClass(HttpUrl);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(JSonResult != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            User user;

                            userList = new ArrayList<User>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                user = new User();

                                jsonObject = jsonArray.getJSONObject(i);

                                // Adding User Id TO IdList Array.
                                IdList.add(jsonObject.getString("id").toString());

                                //Adding User Name.
                                user.UserName = jsonObject.getString("name").toString();

                                userList.add(user);

                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

       @Override
        protected void onPostExecute(Void result)

        {
            progressBar.setVisibility(View.GONE);

            StudentListView.setVisibility(View.VISIBLE);

            ListAdapterClass adapter = new ListAdapterClass(userList, context);

            StudentListView.setAdapter(adapter);

        }
    }
}