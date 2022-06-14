package fptaptech.assignmentwcd.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class StringHelper {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static AtomicLong idCounter = new AtomicLong();

    public static String toSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

    public static String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }

    public static String generateOrderCode() {
        return "ORDER" + String.valueOf(System.currentTimeMillis());
    }
}
