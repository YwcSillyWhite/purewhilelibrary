package com.purewhite.ywc.purewhitelibrary.config.share;

import android.content.Context;
import android.content.SharedPreferences;

import com.purewhite.ywc.purewhitelibrary.app.AppUtils;

import java.util.Map;
import java.util.Set;

/**
 * sharedPreferences工具类
 * @author yuwenchao
 */
public class SpUtils {

    private static final String default_name="pureWhite";


    public static SBuilder builder(boolean gain)
    {
        return builder(gain,default_name);
    }

    public static SBuilder builder(boolean gain,String content)
    {
        return new SBuilder(gain,content);
    }

    public static class  SBuilder<B extends SBuilder>
    {
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;
        public SBuilder(boolean gain,String content) {
            sharedPreferences=AppUtils.getApplication().getSharedPreferences(content, Context.MODE_PRIVATE);
            if (gain)
            {
                editor=sharedPreferences.edit();
            }
        }

        public B put(String key, Object object)
        {
            if (editor!=null)
            {
                if (object instanceof Boolean)
                {
                    editor.putBoolean(key, ((Boolean) object));
                }
                else if (object instanceof Float)
                {
                    editor.putFloat(key, ((Float) object));
                }
                else if (object instanceof Integer)
                {
                    editor.putInt(key, ((Integer) object));
                }
                else if (object instanceof Long)
                {
                    editor.putLong(key, ((Long) object));
                }
                else if (object instanceof String)
                {
                    editor.putString(key, ((String) object));
                }
                else if (object instanceof Set)
                {
                    editor.putStringSet(key, ((Set<String>) object));
                }
            }
            return (B)this;
        }


        public B clear()
        {
            if (editor!=null)
                editor.clear();
            return (B)this;
        }

        public B remove(String key)
        {
            if (editor!=null)
                editor.remove(key);
            return (B)this;
        }


        /**
         * 取值
         * @return
         */
        public Map<String, ?> getAll()
        {
            return sharedPreferences.getAll();
        }


        public float getFloat(String key)
        {
            return sharedPreferences.getFloat(key,0);
        }

        public int getInt(String key)
        {
            return sharedPreferences.getInt(key,0);
        }

        public Long getLong(String key)
        {
            return sharedPreferences.getLong(key,0);
        }

        public String getString(String key)
        {
            return sharedPreferences.getString(key,"");
        }

        public Set<String> getStringSet(String key)
        {
            return sharedPreferences.getStringSet(key,null);
        }
        public boolean getBoolean()
        {
            return getBoolean();
        }

        /**
         * 提交
         */
        public void build(boolean commit)
        {
            if (editor!=null)
            {
                if (commit)
                {
                    editor.commit();
                }
                else
                {
                    editor.apply();
                }
            }

        }
    }
}
