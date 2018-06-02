package com.itland.employer.api;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.itland.employer.responses.UploadImageResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Saad on 5/30/2018.
 */

public class FileUploader extends AsyncTask<Void,Void,Void> {

    private Bitmap bitmap;
    private String uploadUrl;
    private String imageUrl;
    private Gson gson;
    public String url = "http://82.137.221.168/IRecruitment.Site/Account/UploadFile";
    private OnImageUploadedListener listener;

    public static void upload(Bitmap bitmap, String uploadUrl,OnImageUploadedListener listener)
    {
        new FileUploader(bitmap,uploadUrl,listener);
    }

    public FileUploader(Bitmap bitmap, String uploadUrl,OnImageUploadedListener listener) {
        this.bitmap = bitmap;
        this.uploadUrl = uploadUrl;
        this.listener = listener;
        this.gson = new Gson();
        execute();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        imageUrl = uploadFile(bitmap,url);
        return null;
    }

    @Override
    protected void onPostExecute(Void s) {
        UploadImageResponse response = gson.fromJson(imageUrl,UploadImageResponse.class);
        if(response.IsOk) listener.onImageUploaded(response.ImageUrl);
        else listener.onImageUploaded("Error");
        super.onPostExecute(s);
    }

    public String uploadFile(Bitmap bitmap, String upLoadServerUri) {
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String attachmentName = "file";
        String attachmentFileName = "bitmap.bmp";
        String crlf = "\r\n";

        try {
            HttpURLConnection httpUrlConnection = null;
            URL url = new URL(upLoadServerUri);
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");
            httpUrlConnection.setRequestProperty("User-Agent", "Android Multipart HTTP Client 1.0");
            httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
            httpUrlConnection.setRequestProperty(
                    "Content-Type", "multipart/form-data;boundary=" + boundary);
            DataOutputStream request = new DataOutputStream(
                    httpUrlConnection.getOutputStream());
            request.writeBytes(twoHyphens + boundary + crlf);
            request.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\";filename=\"" +
                    attachmentFileName + "\"" + lineEnd);
            request.writeBytes("Content-Type: image/png" + lineEnd);
            request.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);
            request.writeBytes(lineEnd);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] pixels = bos.toByteArray();
            request.write(pixels);
            request.writeBytes(lineEnd);
            request.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            InputStream responseStream = new
                    BufferedInputStream(httpUrlConnection.getInputStream());
            BufferedReader responseStreamReader =
                    new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            responseStreamReader.close();
            String response = stringBuilder.toString();
            if(!response.contains("File")) {
                String imageURL = response.substring(0, response.length() - 1);
                return imageURL;
            }
            request.flush();
            request.close();
            responseStream.close();
            httpUrlConnection.disconnect();
            return response;
        } catch (Exception ex) {
            Log.v("File", ex.getMessage());
            ex.printStackTrace();

        } finally {

        }
        return "Error";
    }

    public interface OnImageUploadedListener
    {
        public void onImageUploaded(String imageUrl);
    }
}
