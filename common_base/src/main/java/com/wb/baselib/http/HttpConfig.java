package com.wb.baselib.http;

import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class HttpConfig {
    private static HttpConfig httpConfig;
    private String mBaseUrl;
    private int mConnectTimeout;
    private boolean mIsUseLog;
    private boolean mIsUseCache;
    private File mCacheFolder;
    private int mCacheSize;
    private int mCacheTimeWithNet;
    private int mCacheTimeWithoutNet;
    private Map<String, String> mMapHeader;
    private boolean mIsUseRetryWhenError;
    private int mTimeRetryDelay;
    private int mMaxRetryCount;
    private boolean isUseCustGson;
    private boolean isReshConfig;

    public static HttpConfig newInstance() {

        if (httpConfig == null) {
            synchronized (HttpConfig.class) {
                httpConfig = new HttpConfig();
            }
        }
        return httpConfig;
    }

    public static HttpConfig newInstanceBuild(HttpConfigBuilder httpConfigBuilder) {
        if (httpConfig == null) {
            synchronized (HttpConfig.class) {
                httpConfig = new HttpConfig(httpConfigBuilder);
            }
        }
        if( httpConfig.mMapHeader==null){
            httpConfig.mMapHeader=new HashMap<>();
        }
        if (httpConfigBuilder.isReshConfig) {
            if (httpConfigBuilder.mBaseUrl == null || httpConfigBuilder.mBaseUrl.equals("")) {
                if (httpConfig.getmBaseUrl() == null || httpConfig.getmBaseUrl().equals("")) {
                    throw new NullPointerException("mBaseUrl is not null");
                }
            } else {
                if (!httpConfig.mBaseUrl.equals(httpConfigBuilder.mBaseUrl)) {
                    httpConfig.mBaseUrl = httpConfigBuilder.mBaseUrl;
                }
            }

            if (httpConfigBuilder.mConnectTimeout == 0) {
            } else {
                if (httpConfig.mConnectTimeout != httpConfigBuilder.mConnectTimeout) {
                    httpConfig.mConnectTimeout = httpConfigBuilder.mConnectTimeout;
                }
            }

            if (!httpConfigBuilder.mIsUseLog) {
            } else {
                if (httpConfig.mIsUseLog != httpConfigBuilder.mIsUseLog) {
                    httpConfig.mIsUseLog = httpConfigBuilder.mIsUseLog;
                }
            }

            if (!httpConfigBuilder.mIsUseCache) {
            } else {
                if (httpConfig.mIsUseCache != httpConfigBuilder.mIsUseCache) {
                    httpConfig.mIsUseCache = httpConfigBuilder.mIsUseCache;
                }
            }

            if (httpConfigBuilder.mCacheFolder == null) {
            } else {
                if (!httpConfig.mCacheFolder.getAbsolutePath().equals(httpConfigBuilder.mCacheFolder.getAbsolutePath())) {
                    httpConfig.mCacheFolder = httpConfigBuilder.mCacheFolder;
                }
            }

            if (httpConfigBuilder.mMaxRetryCount == 0) {
            } else {
                if (httpConfig.mMaxRetryCount != httpConfigBuilder.mMaxRetryCount) {
                    httpConfig.mMaxRetryCount = httpConfigBuilder.mMaxRetryCount;
                }
            }
            if (!httpConfigBuilder.isUseCustGson) {
            } else {
                if (httpConfig.isUseCustGson != httpConfigBuilder.isUseCustGson) {
                    httpConfig.isUseCustGson = httpConfigBuilder.isUseCustGson;
                }
            }

            if (httpConfigBuilder.mTimeRetryDelay == 0) {
            } else {
                if (httpConfig.mTimeRetryDelay != httpConfigBuilder.mTimeRetryDelay) {
                    httpConfig.mTimeRetryDelay = httpConfigBuilder.mTimeRetryDelay;
                }
            }

            if (!httpConfigBuilder.mIsUseRetryWhenError) {
            } else {
                if (httpConfig.mIsUseRetryWhenError != httpConfigBuilder.mIsUseRetryWhenError) {
                    httpConfig.mIsUseRetryWhenError = httpConfigBuilder.mIsUseRetryWhenError;
                }
            }

            if (httpConfigBuilder.mCacheTimeWithoutNet == 0) {
            } else {
                if (httpConfig.mCacheTimeWithoutNet != httpConfigBuilder.mCacheTimeWithoutNet) {
                    httpConfig.mCacheTimeWithoutNet = httpConfigBuilder.mCacheTimeWithoutNet;
                }
            }

            if (httpConfigBuilder.mCacheTimeWithNet == 0) {
            } else {
                if (httpConfig.mCacheTimeWithNet != httpConfigBuilder.mCacheTimeWithNet) {
                    httpConfig.mCacheTimeWithNet = httpConfigBuilder.mCacheTimeWithNet;
                }
            }
            if (httpConfigBuilder.mCacheSize == 0) {
            } else {
                if (httpConfig.mCacheSize != httpConfigBuilder.mCacheSize) {
                    httpConfig.mCacheSize = httpConfigBuilder.mCacheSize;
                }
            }
            if (!httpConfigBuilder.isReshConfig) {
            } else {
                if (httpConfig.isReshConfig != httpConfigBuilder.isReshConfig) {
                    httpConfig.isReshConfig = httpConfigBuilder.isReshConfig;
                }
            }
            if (httpConfigBuilder.mMapHeader == null || httpConfigBuilder.mMapHeader.size() == 0) {

            } else {
                for (Map.Entry<String, String> entry : httpConfigBuilder.mMapHeader.entrySet()) {
                    httpConfig.mMapHeader.put(entry.getKey(), entry.getValue());
                }

            }

        }
        return httpConfig;
    }

    public HttpConfig() {
    }

    public void setmMapHeader(Map<String, String> mMapHeader) {
        this.mMapHeader = mMapHeader;
    }

    public HttpConfig(HttpConfigBuilder httpConfigBuilder) {

        this.mBaseUrl = httpConfigBuilder.mBaseUrl;
        mConnectTimeout = httpConfigBuilder.mConnectTimeout;
        mIsUseLog = httpConfigBuilder.mIsUseLog;
        mIsUseCache = httpConfigBuilder.mIsUseCache;
        mCacheFolder = httpConfigBuilder.mCacheFolder;
        mCacheSize = httpConfigBuilder.mCacheSize;
        mCacheTimeWithNet = httpConfigBuilder.mCacheTimeWithNet;
        mCacheTimeWithoutNet = httpConfigBuilder.mCacheTimeWithoutNet;
        mMapHeader = httpConfigBuilder.mMapHeader;
        mIsUseRetryWhenError = httpConfigBuilder.mIsUseRetryWhenError;
        mTimeRetryDelay = httpConfigBuilder.mTimeRetryDelay;
        mMaxRetryCount = httpConfigBuilder.mMaxRetryCount;
        isUseCustGson = httpConfigBuilder.isUseCustGson;
        isReshConfig = httpConfigBuilder.isReshConfig;
    }

    public boolean isReshConfig() {
        return isReshConfig;
    }

    public boolean isUseCustGson() {
        return isUseCustGson;
    }

    public String getmBaseUrl() {
        return mBaseUrl;
    }

    public int getmConnectTimeout() {
        return mConnectTimeout;
    }

    public boolean ismIsUseLog() {
        return mIsUseLog;
    }

    public boolean ismIsUseCache() {
        return mIsUseCache;
    }

    public File getmCacheFolder() {
        return mCacheFolder;
    }

    public int getmCacheSize() {
        return mCacheSize;
    }

    public int getmCacheTimeWithNet() {
        return mCacheTimeWithNet;
    }

    public int getmCacheTimeWithoutNet() {
        return mCacheTimeWithoutNet;
    }

    public Map<String, String> getmMapHeader() {
        return mMapHeader;
    }

    public boolean ismIsUseRetryWhenError() {
        return mIsUseRetryWhenError;
    }

    public int getmTimeRetryDelay() {
        return mTimeRetryDelay;
    }

    public int getmMaxRetryCount() {
        return mMaxRetryCount;
    }

    public static class HttpConfigBuilder {
        private String mBaseUrl;
        private int mConnectTimeout;
        private boolean mIsUseLog;
        private boolean mIsUseCache;
        private File mCacheFolder;
        private int mCacheSize;
        private int mCacheTimeWithNet;
        private int mCacheTimeWithoutNet;
        private Map<String, String> mMapHeader;
        private boolean mIsUseRetryWhenError;
        private int mTimeRetryDelay = -1;
        private int mMaxRetryCount;
        private boolean isUseCustGson;
        private boolean isReshConfig;

        public HttpConfigBuilder setIsReshConfig(boolean is) {
            this.isReshConfig = is;
            return this;
        }

        public HttpConfigBuilder setmBaseUrl(String mBaseUrl) {
            this.mBaseUrl = mBaseUrl;
            return this;
        }

        public HttpConfigBuilder setUseCustGson(boolean useCustGson) {
            isUseCustGson = useCustGson;
            return this;
        }

        public HttpConfigBuilder setmConnectTimeout(int mConnectTimeout) {
            this.mConnectTimeout = mConnectTimeout;
            return this;
        }

        public HttpConfigBuilder setmIsUseLog(boolean mIsUseLog) {
            this.mIsUseLog = mIsUseLog;
            return this;
        }

        public HttpConfigBuilder setmIsUseCache(boolean mIsUseCache) {
            this.mIsUseCache = mIsUseCache;
            return this;
        }

        public HttpConfigBuilder setmCacheFolder(File mCacheFolder) {
            this.mCacheFolder = mCacheFolder;
            return this;
        }

        public HttpConfigBuilder setmCacheSize(int mCacheSize) {
            this.mCacheSize = mCacheSize;
            return this;
        }

        public HttpConfigBuilder setmCacheTimeWithNet(int mCacheTimeWithNet) {
            this.mCacheTimeWithNet = mCacheTimeWithNet;
            return this;
        }

        public HttpConfigBuilder setmCacheTimeWithoutNet(int mCacheTimeWithoutNet) {
            this.mCacheTimeWithoutNet = mCacheTimeWithoutNet;
            return this;
        }

        public HttpConfigBuilder setmMapHeader(Map<String, String> mMapHeader) {
            this.mMapHeader = mMapHeader;
            return this;
        }

        public HttpConfigBuilder setmIsUseRetryWhenError(boolean mIsUseRetryWhenError) {
            this.mIsUseRetryWhenError = mIsUseRetryWhenError;
            return this;
        }

        public HttpConfigBuilder setmTimeRetryDelay(int mTimeRetryDelay) {
            this.mTimeRetryDelay = mTimeRetryDelay;
            return this;
        }

        public HttpConfigBuilder setmMaxRetryCount(int mMaxRetryCount) {
            this.mMaxRetryCount = mMaxRetryCount;
            return this;
        }

        public HttpConfig build() {
            HttpConfig httpConfig = HttpConfig.newInstanceBuild(this);
            if (httpConfig.mBaseUrl == null || httpConfig.mBaseUrl.equals("")) {
                throw new IllegalMonitorStateException("BaseUrl is not null");
            }
            return httpConfig;
        }
    }
}
