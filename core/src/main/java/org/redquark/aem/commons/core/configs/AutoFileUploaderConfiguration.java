package org.redquark.aem.commons.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * @author Anirudh Sharma
 * 
 *         This interface defines the configuration for the watched folder
 *         scheduler
 */
@ObjectClassDefinition(
		name = "Red Quark Commons Auto File Uploader Configuration", 
		description = "Configure for file upload options"
	)
public @interface AutoFileUploaderConfiguration {

	@AttributeDefinition(
			name = "Scheduler Name", 
			description = "Enter the name of the Scheduler", 
			type = AttributeType.STRING
		)
	public String schedulerName() default "Auto File Uploader Scheduler";

	@AttributeDefinition(
			name = "Watcher Directory Path", 
			description = "Enter the path of the directory to watched for", 
			type = AttributeType.STRING
		)
	public String watchedDirectoryPath();

	@AttributeDefinition(
			name = "JCR Path", 
			description = "Path to save binary in the AEM JCR (DAM)", 
			type = AttributeType.STRING
		)
	public String pathToSave() default "/content/dam";

	@AttributeDefinition(
			name = "Cron Expression", 
			description = "Enter the cron expression to run the scheduler", 
			type = AttributeType.STRING
		)
	public String cronExpression() default "0 * * * * ?";

	@AttributeDefinition(
			name = "Enable Scehduler", 
			description = "If true, the scheduler will be enabled", 
			type = AttributeType.BOOLEAN
		)
	public boolean isEnabled() default false;
}
