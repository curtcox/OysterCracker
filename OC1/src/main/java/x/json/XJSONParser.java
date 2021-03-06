package x.json;

import x.io.IO;
import x.parse.Lexer;
import x.parse.Tokenizer;

import java.io.IOException;
import java.io.Reader;

/**
 * For parsing into Json.
 */
public final class XJSONParser {

    private static final String[] separators = new String[] {
            "{","}", "[","]", ":",",","\"", "\\", " "
    };

    public static Json parse(Reader reader) throws IOException {
        String[] tokens = tokens(reader);
        if (tokens[0].equals("{")) {
            JsonMapParser parser = new JsonMapParser(tokens,0);
            return parser.parse();
        } else {
            JsonListParser parser = new JsonListParser(tokens,0);
            return parser.parse();
        }
    }

    static String unquoted(String value) {
        String trimmed = value.trim();
        if (trimmed.startsWith("\"")) {
            return trimmed.substring(1, trimmed.length() - 1);
        } else {
            return trimmed;
        }
    }

    private static String[] tokens(Reader reader) {
        return split(IO.stringOrEmptyFrom(reader));
    }

    static String[] split(String string) {
        return Lexer.transform(parts(string));
    }

    private static String[] parts(String string) {
        return Tokenizer.tokenize(string, separators);
    }

}
