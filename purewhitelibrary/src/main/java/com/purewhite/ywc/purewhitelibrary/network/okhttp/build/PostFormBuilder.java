package com.purewhite.ywc.purewhitelibrary.network.okhttp.build;

import android.text.TextUtils;

import com.purewhite.ywc.purewhitelibrary.network.okhttp.bean.FileInput;
import com.purewhite.ywc.purewhitelibrary.network.okhttp.build.base.OkRequestParamBuilder;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author yuwenchao
 */
public class PostFormBuilder extends OkRequestParamBuilder<PostFormBuilder> {

    private List<FileInput> fileInputList=new ArrayList<>();

    @Override
    public PostFormBuilder addParam(String key, String value) {
        paramsRequest.put(key,value);
        return this;
    }

    @Override
    public PostFormBuilder addParams(Map<String, String> paramsRequest) {
        if (paramsRequest!=null&&paramsRequest.size()>0)
        {
           for (String key:paramsRequest.keySet())
           {
               addParam(key,paramsRequest.get(key));
           }
        }
        return this;
    }


    public PostFormBuilder addFile(@NonNull String key,@NonNull String filename,@NonNull File file)
    {
        fileInputList.add(new FileInput(key,filename,file));
        return this;
    }

    public PostFormBuilder addFiles(@NonNull String key, Map<String,File> files)
    {
        if (files!=null&&files.size()>0)
        {
            for (String filename:files.keySet())
            {
                addFile(key,filename,files.get(filename));
            }
        }
        return this;
    }


    @Override
    public void build() {
        builder.post(obtianBody());
        builder.url(url);
    }


    private RequestBody obtianBody()
    {
        if (fileInputList.size()>0)
        {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            if (paramsRequest.size()>0)
            {
                for (String key:paramsRequest.keySet())
                {
                    builder.addFormDataPart(key,paramsRequest.get(key));
                }
            }
            for (int i = 0; i < fileInputList.size(); i++) {
                FileInput fileInput = fileInputList.get(i);
                RequestBody fileBody = RequestBody.create(fileInput.getFile(),MediaType.parse(getMediaType(fileInput.getFilename())));
                builder.addFormDataPart(fileInput.getKey(),fileInput.getFilename(),fileBody);
            }
            return builder.build();
        }
        else
        {
            FormBody.Builder builder = new FormBody.Builder();
            if (paramsRequest.size()>0)
            {
                for (String key:paramsRequest.keySet())
                {
                    builder.add(key,paramsRequest.get(key));
                }
            }
            return builder.build();
        }

    }



    /**
     * 根据文件的名称判断文件的Mine值
     */
    private String getMediaType(String mediaType)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor=null;
        try {
            contentTypeFor=fileNameMap.getContentTypeFor(URLEncoder.encode(mediaType,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        finally {
            if (TextUtils.isEmpty(contentTypeFor))
            {
                contentTypeFor="application/octet-stream";
            }
            return contentTypeFor;
        }
    }

}
