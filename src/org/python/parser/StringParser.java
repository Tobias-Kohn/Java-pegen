package org.python.parser;

public class StringParser {

    public enum StringType {
        BYTES,
        UNICODE,
        F_STRING,
        ERROR
    }

    public static class StringValue {
        public final String value;
        public final StringType kind;

        StringValue(String value, StringType kind) {
            this.value = value;
            this.kind = kind;
        }
    }

    public static StringValue parsestr(CharSequence s) {
        return parsestr(s, false, false);
    }

    public static StringValue parsestr(CharSequence s, boolean bytes_mode, boolean raw_mode) {
        boolean f_mode = false;
        int j = 0;
        while (j < s.length() && Character.isAlphabetic(s.charAt(j))) {
            switch (s.charAt(j)) {
                case 'b', 'B' -> bytes_mode = true;
                case 'f', 'F' -> f_mode = true;
                case 'r', 'R' -> raw_mode = true;
                case 'u', 'U' -> {
                    // Ignore the old unicode marker
                }
                default -> { return string_error(); }
            }
            j++;
        }
        if (j+2 >= s.length())
            return string_error();
        char quote = s.charAt(j++);
        int end = s.length() - 1;
        if (quote != s.charAt(end))
            return string_error();
        if (end - j >= 4 && s.charAt(j) == quote && s.charAt(j+1) == quote) {
            j += 2;
            end -= 2;
            if (s.charAt(end) != quote || s.charAt(end-1) != quote)
                return string_error();
        }
        s = s.subSequence(j, end);
        String str = s.toString();
        if (f_mode)
            return new StringValue(str, StringType.F_STRING);
        raw_mode = raw_mode || (str.indexOf('\\') == -1);
        if (bytes_mode) {
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) >= 0x80)
                    return string_error("bytes can only contain ASCII literal characters.");
            if (raw_mode)
                return new StringValue(str, StringType.BYTES);
            else
                return decode_bytes_with_escapes(s);
        }
        else if (raw_mode)
            return new StringValue(str, StringType.UNICODE);
        else
            return decode_unicode_with_escapes(s);
    }

    protected static StringValue decode_bytes_with_escapes(CharSequence s) {
        return new StringValue(s.toString(), StringType.BYTES);
    }

    protected static StringValue decode_unicode_with_escapes(CharSequence s) {
        return new StringValue(s.toString(), StringType.UNICODE);
    }

    protected static StringValue string_error() {
        return new StringValue("interal error", StringType.ERROR);
    }

    protected static StringValue string_error(String msg) {
        return new StringValue(msg, StringType.ERROR);
    }
}
