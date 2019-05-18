package com.purewhite.ywc.purewhitelibrary.config;

import android.os.Bundle;
import android.os.Parcelable;

import io.reactivex.annotations.Nullable;

/**
 * @author yuwenchao
 */
public class BundleUtils {

    public static BBuidler buidler()
    {
        return new BBuidler();
    }

    static class BBuidler
    {
        private Bundle bundle;
        public BBuidler() {
            bundle = new Bundle();
        }

        public BBuidler put(@Nullable String key,@Nullable Object object)
        {
            if (object instanceof Integer)
            {
                bundle.putInt(key, ((Integer) object));
            }
            else if (object instanceof String)
            {
                bundle.putString(key, ((String) object));
            }
            else if (object instanceof Parcelable)
            {
                bundle.putParcelable(key, ((Parcelable) object));
            }
            else if (object instanceof Float)
            {
                bundle.putFloat(key, ((Float) object));
            }
            return this;
        }

        public Bundle build()
        {
            return bundle;
        }

    }

}
