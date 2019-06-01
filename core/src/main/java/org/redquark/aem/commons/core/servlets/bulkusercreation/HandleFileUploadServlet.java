package org.redquark.aem.commons.core.servlets.bulkusercreation;

import static org.redquark.aem.commons.core.constants.AppConstants.EMPTY_STRING;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Anirudh Sharma
 *
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "= Handle File Upload Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST,
		"sling.servlet.paths=" + "/bin/redquarkcommons/fileupload" })
public class HandleFileUploadServlet extends SlingAllMethodsServlet {

	// Generated serial version UID
	private static final long serialVersionUID = 2800760449052052033L;

	// Logger
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handles the post request when the file is uploaded from the front end
	 */
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		log.trace("Invoking HandleFileUploadServlet...");

		// Path of the temporary file
		String tempFilePath;

		// PrintWriter instance to set response
		PrintWriter printWriter = null;

		try {

			// Check if the request is multi-part
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);

			// Setting the temporary file path - This path will be on the server from
			// where the AEM is running
			tempFilePath = System.getProperty("user.dir");

			// Getting the writer instance from the response object
			printWriter = response.getWriter();

			String createdFilePath = EMPTY_STRING;

			// Temporary file
			File file = null;

			if (isMultipart) {

				// Getting the request parameters from the request object
				Map<String, RequestParameter[]> parameters = request.getRequestParameterMap();

				// Getting the request parameters from the entry set
				for (final Map.Entry<String, RequestParameter[]> pairs : parameters.entrySet()) {

					// Getting the value of request parameter - first element only
					RequestParameter parameter = pairs.getValue()[0];

					// Checking if the posted value is a file or JCR path
					final boolean isFormField = parameter.isFormField();

					if (!isFormField) {
						// Getting the input stream object
						InputStream inputStream = parameter.getInputStream();

						// Creating a temporary file
						file = File.createTempFile("sample", ".xlsx", new File(tempFilePath));

						// Writing contents from input stream to the temporary file
						FileUtils.copyInputStreamToFile(inputStream, file);

						// Getting the absolute path of the file
						createdFilePath = file.getAbsolutePath();
						
						log.debug("Temporary file path is: {}", createdFilePath);
					}
				}
				
				// Printing the response to the screen via response object
				printWriter.println("File uploaded successfully");
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			printWriter.println(e.getMessage());
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
	}

}
