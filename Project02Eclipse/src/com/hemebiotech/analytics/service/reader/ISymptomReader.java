
package com.hemebiotech.analytics.service.reader;

import java.util.List;

/**
 * Defines a contract for reading symptoms from a data source.
 */
public interface ISymptomReader {

	/**
	 * Retrieves all symptoms, including duplicates, in no guaranteed order.
	 *
	 * @return a list of symptoms, or an empty list if none are available
	 */
	List<String> getSymptoms();
}