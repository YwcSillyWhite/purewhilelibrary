package com.purewhite.ywc.purewhitelibrary.config.bundle;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * @author yuwenchao
 */
public class BundleUtils {

    public static Builder buidler()
    {
        return new Builder();
    }

    public static class  Builder
    {
        private Bundle bundle;
        public Builder() {
            bundle = new Bundle();
        }

        public Builder put(@Nullable String key,@Nullable Object object)
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
            else if (object instanceof Serializable)
            {
                bundle.putSerializable(key, ((Serializable) object));
            }

            return this;
        }

        public Builder putStringArrayList(@Nullable String key,@Nullable List<String> list)
        {
            bundle.putSerializable(key, ((ArrayList<String>) list));
            return this;
        }


        public Bundle build()
        {
            return bundle;
        }

    }

}
