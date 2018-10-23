package debug;

import android.app.Activity;
import android.os.Bundle;
import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.StyledDialog;
import com.wb.baselib.BaseApplication;
import com.wb.baselib.http.HttpConfig;

public class DownsApplication extends BaseApplication {
    @Override
    public String getRootPackAge() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        registCallback();
    }
    /**
     * 全局对话框的初始化
     */
    private void registCallback( ) {
        StyledDialog.init(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityStackManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStackManager.getInstance().removeActivity(activity);
            }
        });
    }
}
