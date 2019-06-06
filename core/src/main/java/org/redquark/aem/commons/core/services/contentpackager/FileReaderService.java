package org.redquark.aem.commons.core.services.contentpackager;

import java.util.List;

import org.redquark.aem.commons.core.models.contentpackager.ContentFilters;

/**
 * @author Anirudh Sharma
 *
 */
public interface FileReaderService {

	List<ContentFilters> readData(String filePath);
}
