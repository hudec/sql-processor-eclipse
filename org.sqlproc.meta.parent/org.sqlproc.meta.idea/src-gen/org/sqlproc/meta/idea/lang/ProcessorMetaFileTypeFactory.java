/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.meta.idea.lang;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

public class ProcessorMetaFileTypeFactory extends FileTypeFactory {

	@Override
	public void createFileTypes(@NotNull FileTypeConsumer consumer) {
		consumer.consume(ProcessorMetaFileType.INSTANCE, AbstractProcessorMetaFileType.DEFAULT_EXTENSION);
	}

}
