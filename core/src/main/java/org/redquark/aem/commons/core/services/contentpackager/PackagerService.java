package org.redquark.aem.commons.core.services.contentpackager;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.redquark.aem.commons.core.models.contentpackager.ContentFilters;

/**
 * @author Anirudh Sharma
 *
 */
public interface PackagerService {

	/**
	 * This method creates the package in the AEM's package manager and returns true
	 * if successful
	 * 
	 * @return {@link Boolean}
	 */
	boolean createPackage(String packageName, String groupName, List<ContentFilters> contentFilters,
			SlingHttpServletRequest request);
}
