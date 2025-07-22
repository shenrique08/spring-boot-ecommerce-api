package org.api.springbootapiumlcase.resources.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
    public static List<Long> decodeLongList(String s) {
        if (s == null || s.isEmpty()) {
            return List.of(); // Return an empty list if the string is empty
        }
        return Arrays.stream(s.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
