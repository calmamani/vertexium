package org.vertexium.accumulo.iterator.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum FetchHint {
    PROPERTIES,
    PROPERTY_METADATA,
    IN_EDGE_REFS,
    OUT_EDGE_REFS,
    INCLUDE_HIDDEN,
    IN_EDGE_LABELS,
    OUT_EDGE_LABELS;

    public static final EnumSet<FetchHint> ALL = EnumSet.of(PROPERTIES, PROPERTY_METADATA, IN_EDGE_REFS, OUT_EDGE_REFS);

    public static String toString(EnumSet<FetchHint> fetchHints) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (FetchHint fetchHint : fetchHints) {
            if (!first) {
                result.append(",");
            }
            result.append(fetchHint.name());
            first = false;
        }
        return result.toString();
    }

    public static EnumSet<FetchHint> parse(String fetchHintsString) {
        if (fetchHintsString == null) {
            throw new NullPointerException("fetchHintsString cannot be null");
        }
        String[] parts = fetchHintsString.split(",");
        List<FetchHint> results = new ArrayList<>();
        for (String part : parts) {
            String name = part.toUpperCase();
            if (name.trim().length() == 0) {
                continue;
            }
            try {
                results.add(FetchHint.valueOf(name));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Could not find enum value: '" + name + "'", ex);
            }
        }
        return create(results);
    }

    public static EnumSet<FetchHint> create(List<FetchHint> results) {
        if (results.size() == 0) {
            return EnumSet.noneOf(FetchHint.class);
        } else {
            return EnumSet.copyOf(results);
        }
    }
}
