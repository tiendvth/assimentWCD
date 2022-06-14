package fptaptech.assignmentwcd.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHelper {
    private static String lang = "en";
    private static String bundleFileNameDefault = "message";
    private static Locale defaultLocale = Locale.getDefault();
    private static ResourceBundle resourceBundleDefault = ResourceBundle.getBundle(bundleFileNameDefault, defaultLocale);

    static {
        Locale.setDefault(new Locale(lang));
    }

    public static void setDefaultLocale(String lang) {
        defaultLocale = new Locale(lang);
        resourceBundleDefault = ResourceBundle.getBundle(bundleFileNameDefault, defaultLocale);
    }

    public static void setBundleFileNameDefault(String bundleFileNameDefault) {
        LanguageHelper.bundleFileNameDefault = bundleFileNameDefault;
    }

    public static String getString(String key) {
        return resourceBundleDefault.getString(key);
    }
}
