
package com.hemebiotech.analytics.service.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Writes symptom analysis results to a text file.
 */
public class SymptomsFileWriter implements ISymptomsWriter {

    private final String filePath;

    /**
     * Creates a writer for the specified output file.
     *
     * @param filePath the path to the output file
     */
    public SymptomsFileWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) throws IOException {

        try (FileWriter writer = new FileWriter(filePath)) {

            for (Map.Entry<String, Integer> entry : symptoms.entrySet()) {
                writer.write(
                        entry.getKey() + ": " + entry.getValue() + "\n"
                );
            }
        }
    }
}