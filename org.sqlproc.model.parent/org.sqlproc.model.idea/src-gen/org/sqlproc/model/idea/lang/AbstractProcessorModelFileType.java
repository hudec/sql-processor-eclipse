/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model.idea.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import javax.swing.Icon;
import org.eclipse.xtext.idea.Icons;
import org.jetbrains.annotations.NonNls;

public class AbstractProcessorModelFileType extends LanguageFileType {

	@NonNls 
	public static final String DEFAULT_EXTENSION = "model";

	protected AbstractProcessorModelFileType(final Language language) {
		super(language);
	}

	@Override
	public String getDefaultExtension() {
		return DEFAULT_EXTENSION;
	}

	@Override
	public String getDescription() {
		return "ProcessorModel files";
	}

	@Override
	public Icon getIcon() {
		return Icons.DSL_FILE_TYPE;
	}

	@Override
	public String getName() {
		return "ProcessorModel";
	}

}