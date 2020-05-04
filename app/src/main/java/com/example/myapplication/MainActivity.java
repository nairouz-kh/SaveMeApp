package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    String api_Link = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02";
    private static final String TAG = MainActivity.class.getSimpleName();
    Button LoadBtn;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue queue = Volley.newRequestQueue(this);
        LoadBtn = findViewById(R.id.btn);
        text = findViewById(R.id.txt);

        LoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //هنا عرفنا طلب اتصال جيسون اوبجكت ريكوست انتي كنتي مدايرة سترينق ريكوست من ناحية الفكرة مافيش فرق الزوز بيجيبولك بيانات بس الفكرة كيف بتتعاملي معاها الجيسون اوبجكت يساعدنا هلبا وتو تشوفي لوطة
                //الريكوست كان من نوع قيت وحطيناله الرابط
                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, api_Link, null, new Response.Listener<JSONObject>() {

                    //بما ان درنا ريكوست فبيكون فيه نتيجة
                    // النتيجة وحدة من الزوز ياالريكوست بينجح او بيفشل لونحج يعني الريكوست وصل للسيرفر والسيرفر رد علينا بمعلومة
                    // فالدالة هادي بتشتغل طول وحيمر من خلالها جيسون اوبجكت اللي جانا من الطلب وسميناه ريسبونس

                    @Override
                    public void onResponse(JSONObject response) {
                    //هنا درنا لوق باش نعرفو في اللوق متع البرنامج ان لو في خطا او اي شي يصير حيعرض الرسبونس في اللوق متع اندرويد ستوديو
                        VolleyLog.d(TAG, "Response: " + response.toString());
                      // هنا نتأكدو من الريسبونس مش فاضي لو فاض مش حيدير شي لو مش فاضي حينفد الفنكشن اسمها باريس جيسون اللي فيها بيفك الجيسون وبيحوله لمعلومات مفيدة
                        if (response != null) {
                         // هادي هيا الفنكشن  تو نشوفيها لوطة
                            parseJsonFeed(response);
                        }
                    }
                    // هنا في حالة في ايرور او خطا طبعا الخطا مش حيوكن من السيرفر بيكون من الجهاز يعني لومافيش نت او في حاجة مانعة التطبيق انه يتصل بالنت ف اللسينر هدا بيعرف لو في غلط وبينفد الدالة اللي فيه
                    //نفس فكرة الفوقية لو نجح الركويست حتخدم الفنكشن اون ريسبون
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                      // نديرو في لوفق الايرور
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                });
//هادي اضيف ف الركوست للطابور الركوستات
                queue.add(jsonReq);
            }
        });
    }

    //ييهنا بنديرو باريس لجيسون رسبونس اللي جانا من السيرفر

    private void parseJsonFeed(JSONObject response) {
    // فتحنا تراي كاتش لانه مرات يبدا في خطاء في الرسبونس اللي بنستلموه لوما بنجو نحولوه يصير ايرور ف تراي كاتش ماخليش البرنامج يوقف
        try {
       // هنا عرفنا اوبجكت جديد وقلناله جيبه من الرسبونس والاوبجكت اسمه coord

            JSONObject coord = response.getJSONObject("coord");
            //هنا عرفنا متغير اكس وكان انتجر رقمي وقلتله جيب قيمته الرقمية من الجيسون اوبجكت متع coord وتلقاه في اللات
            int x = coord.getInt("lat");
            //هنا عرفنا متغير سيتي وكان نصي  وقلتله جيب قيمته النصية من الجيسون اوبجكت متع الريسبونس لان الاسم موجود طول تحت القواس يعني الاب المباشر ليه هوا الرسبونس  مش زي اللات ابها المباشر هوا coord وال coord ابه هوا الرسبونس يعني علاقات اباء وابناء واحد تحت التاني هادي تركزي عليها
            String city = response.getString("name");

            // بعدين هنا عرضنا البيانات اللي خزناها في المتغيرات في توست انتي قبل كان يعطي في سترينق كامل وتوا الحاجة اللي محدددينها احني وقلناله نبوها
            Toast.makeText(MainActivity.this, String.valueOf(x), Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, city, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {

        }
    }
}