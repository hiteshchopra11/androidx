/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.webkit;

import android.webkit.WebSettings;

import androidx.annotation.IntDef;
import androidx.annotation.RequiresFeature;
import androidx.annotation.RestrictTo;
import androidx.webkit.WebSettingsCompat.RequestedWithHeaderMode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Manages settings state for all Service Workers. These settings are not tied to
 * the lifetime of any WebView because service workers can outlive WebView instances.
 * The settings are similar to {@link WebSettings} but only settings relevant to
 * Service Workers are supported.
 */
public abstract class ServiceWorkerWebSettingsCompat {
    /**
     * @hide Don't allow apps to sub-class this class.
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public ServiceWorkerWebSettingsCompat() {}

    /** @hide */
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @IntDef(value = {
            WebSettings.LOAD_DEFAULT,
            WebSettings.LOAD_CACHE_ELSE_NETWORK,
            WebSettings.LOAD_NO_CACHE,
            WebSettings.LOAD_CACHE_ONLY
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface CacheMode {}

    /**
     * Overrides the way the cache is used.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#SERVICE_WORKER_CACHE_MODE}.
     *
     * @param mode the mode to use. One of {@link WebSettings#LOAD_DEFAULT},
     * {@link WebSettings#LOAD_CACHE_ELSE_NETWORK}, {@link WebSettings#LOAD_NO_CACHE}
     * or {@link WebSettings#LOAD_CACHE_ONLY}. The default value is
     * {@link WebSettings#LOAD_DEFAULT}.
     * @see WebSettings#setCacheMode
     * @see #getCacheMode
     */
    @RequiresFeature(name = WebViewFeature.SERVICE_WORKER_CACHE_MODE,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    public abstract void setCacheMode(@CacheMode int mode);

    /**
     * Gets the current setting for overriding the cache mode.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#SERVICE_WORKER_CACHE_MODE}.
     *
     * @return the current setting for overriding the cache mode
     * @see #setCacheMode
     */
    @RequiresFeature(name = WebViewFeature.SERVICE_WORKER_CACHE_MODE,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    public abstract @CacheMode int getCacheMode();

    /**
     * Enables or disables content URL access from Service Workers.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#SERVICE_WORKER_CONTENT_ACCESS}.
     *
     * @see WebSettings#setAllowContentAccess
     * @see #getAllowContentAccess
     */
    @RequiresFeature(name = WebViewFeature.SERVICE_WORKER_CONTENT_ACCESS,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    public abstract void setAllowContentAccess(boolean allow);

    /**
     * Gets whether Service Workers support content URL access.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#SERVICE_WORKER_CONTENT_ACCESS}.
     *
     * @see #setAllowContentAccess
     */
    @RequiresFeature(name = WebViewFeature.SERVICE_WORKER_CONTENT_ACCESS,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    public abstract boolean getAllowContentAccess();

    /**
     *
     * Enables or disables file access within Service Workers.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#SERVICE_WORKER_FILE_ACCESS}.
     *
     * @see WebSettings#setAllowFileAccess
     * @see #getAllowContentAccess
     */
    @RequiresFeature(name = WebViewFeature.SERVICE_WORKER_FILE_ACCESS,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    public abstract void setAllowFileAccess(boolean allow);

    /**
     * Gets whether Service Workers support file access.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#SERVICE_WORKER_FILE_ACCESS}.
     *
     * @see #setAllowFileAccess
     */
    @RequiresFeature(name = WebViewFeature.SERVICE_WORKER_FILE_ACCESS,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    public abstract boolean getAllowFileAccess();

    /**
     * Sets whether Service Workers should not load resources from the network.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#SERVICE_WORKER_BLOCK_NETWORK_LOADS}.
     *
     * @param flag {@code true} means block network loads by the Service Workers
     * @see WebSettings#setBlockNetworkLoads
     * @see #getBlockNetworkLoads
     */
    @RequiresFeature(name = WebViewFeature.SERVICE_WORKER_BLOCK_NETWORK_LOADS,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    public abstract void setBlockNetworkLoads(boolean flag);

    /**
     * Gets whether Service Workers are prohibited from loading any resources from the network.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#SERVICE_WORKER_BLOCK_NETWORK_LOADS}.
     *
     * @return {@code true} if the Service Workers are not allowed to load any resources from the
     * network
     * @see #setBlockNetworkLoads
     */
    @RequiresFeature(name = WebViewFeature.SERVICE_WORKER_BLOCK_NETWORK_LOADS,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    public abstract boolean getBlockNetworkLoads();


    /**
     * Sets how Service Workers will set the X-Requested-With header on requests.
     *
     * If you are calling this method, you may also want to call
     * {@link WebSettingsCompat#setRequestedWithHeaderMode(WebSettings, int)} with the same
     * parameter value to configure non-ServiceWorker requests.
     *
     * The default behavior may vary depending on the WebView implementation.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#REQUESTED_WITH_HEADER_CONTROL}.
     *
     * @param requestedWithHeaderMode The {@code REQUESTED_WITH_HEADER_MODE to use}
     * @see WebSettingsCompat#setRequestedWithHeaderMode(WebSettings, int)
     * @see #getBlockNetworkLoads
     * @hide
     */
    @RequiresFeature(name = WebViewFeature.REQUESTED_WITH_HEADER_CONTROL,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public abstract void setRequestedWithHeaderMode(
            @RequestedWithHeaderMode int requestedWithHeaderMode);

    /**
     * Gets how Service Workers will set the X-Requested-With header on HTTP requests.
     *
     * <p>
     * This method should only be called if
     * {@link WebViewFeature#isFeatureSupported(String)}
     * returns true for {@link WebViewFeature#REQUESTED_WITH_HEADER_CONTROL}.
     *
     * @return the currently configured {@code REQUESTED_WITH_HEADER_MODE}
     * @see #setRequestedWithHeaderMode(int)
     * @hide
     */
    @RequiresFeature(name = WebViewFeature.REQUESTED_WITH_HEADER_CONTROL,
            enforcement = "androidx.webkit.WebViewFeature#isFeatureSupported")
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    @RequestedWithHeaderMode
    public abstract int getRequestedWithHeaderMode();
}
