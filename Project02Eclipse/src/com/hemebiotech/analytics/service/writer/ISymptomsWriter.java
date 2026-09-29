
package com.hemebiotech.analytics.service.writer;

import java.io.IOException;
import java.util.Map;

/**
 * Defines a contract for writing symptom analysis results.
 */
public interface ISymptomsWriter {

    /**
     * Writes symptom counts to an output destination.
     *
     * @param symptoms the symptoms and their occurrence counts
     * @throws IOException if writing the results fails
     */
    void writeSymptoms(Map<String, Integer> symptoms) throws IOException;
}