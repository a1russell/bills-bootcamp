package shoppinglist;

import java.util.Collection;

class TextJoiner {
    public String join(Collection<? extends TextRepresentable> textRepresentables, String separator) {
        String text = "";
        for (TextRepresentable measurement : textRepresentables) {
            text += measurement.getText() + separator;
        }
        return text.substring(0, text.length() - separator.length());
    }
}