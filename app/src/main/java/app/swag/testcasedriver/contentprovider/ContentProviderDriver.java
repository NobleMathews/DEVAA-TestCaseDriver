package app.swag.testcasedriver.contentprovider;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContentProviderDriver {
    public void run(Context context, String uriPayload){
        if (uriPayload != null) {
            try {
                Uri uri = Uri.parse(uriPayload);
                Log.d("Driver URI", uri.getPath());
                InputStream iStream = context.getContentResolver().openInputStream(uri);
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                Log.d("data", sb.toString());

                File sdcard = Environment.getExternalStorageDirectory();
                File dir = new File(sdcard.getAbsolutePath());
                dir.mkdir();
                File file = new File(sdcard, "internal-file-data.txt");
                FileOutputStream os = new FileOutputStream(file);
                String data = sb.toString();
                os.write(data.getBytes());
                os.close();

                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
