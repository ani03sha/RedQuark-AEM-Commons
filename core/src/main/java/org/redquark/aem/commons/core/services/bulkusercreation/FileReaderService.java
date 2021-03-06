package org.redquark.aem.commons.core.services.bulkusercreation;

import java.util.List;

import org.redquark.aem.commons.core.models.bulkusercreation.UserDetails;

/**
 * @author Anirudh Sharma
 *
 */
public interface FileReaderService {

	/**
	 * This method reads the file uploaded by the user. 
	 * The file should be an excel file (.xls or .xlsx)
	 */
	List<UserDetails> readExcel(String filePath);
}
