package fr.on.mange.quoi.tools;

import java.util.List;

public class StringTools {

    public static <Element> String separateWithComma(List<Element> list, ToStringFunction<Element> function) {
        String str = "";
        for (Element elt : list) {
            if (!str.isEmpty()) {
                str += ", ";
            }
            str += function.apply(elt);
        }
        return str;
    }

}
