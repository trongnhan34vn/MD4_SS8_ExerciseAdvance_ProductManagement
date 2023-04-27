package config;

import java.text.NumberFormat;
import java.util.Locale;

public class Config {
    public static Locale localeVN = new Locale("vi", "VN");
    public static NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeVN);
}
