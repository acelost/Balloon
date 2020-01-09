package com.acelost.balloon.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityResumed(final Activity activity) { /*no-op*/ }

    @Override
    public void onActivityPaused(final Activity activity) { /*no-op*/ }

    @Override
    public void onActivityCreated(final Activity activity, final Bundle savedInstanceState) { /*no-op*/ }

    @Override
    public void onActivityStarted(final Activity activity) { /*no-op*/ }

    @Override
    public void onActivityStopped(final Activity activity) { /*no-op*/ }

    @Override
    public void onActivitySaveInstanceState(final Activity activity, final Bundle outState) { /*no-op*/ }

    @Override
    public void onActivityDestroyed(final Activity activity) { /*no-op*/ }
}