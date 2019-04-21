package com.effective.apackage.bspackageeffective.utilities;

import com.effective.apackage.bspackageeffective.ApplicationContext;
import com.effective.apackage.packageeffective.R;

public class NetworkUtil {

    public static String getHostUrl() {
        return ApplicationContext.getAppContext().getString(R.string.base_url) + ApplicationContext.getAppContext().getString(R.string.context_path);
    }

    public static String getDownloadUrl() {
        return getHostUrl() + ApplicationContext.getAppContext().getString(R.string.download_api);
    }

    public static String getChecksumUrl() {
        return getHostUrl() + ApplicationContext.getAppContext().getString(R.string.checksum_api);
    }
}
