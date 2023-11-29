package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        String splitString = getSplitString(delimiters);

        return getList(source, splitString);
    }

    private List<String> getList(String source, String splitString){

        String[] names = source.split(splitString);

        List<String> list = new ArrayList<>();

        for (String s: names) {
            if (s.isEmpty())
                continue;

            list.add(s);
        }

        return list;

    }

    private String getSplitString(Collection<String> delimiters){

        if (delimiters.isEmpty())
            return null;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (String d: delimiters ) {
            stringBuilder.append(d);
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
