/*
 * generated by Xtext 2.12.0
 */
package org.sqlproc.meta.idea.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetTypeId;
import org.eclipse.xtext.idea.facet.AbstractFacetType;
import org.sqlproc.meta.idea.lang.ProcessorMetaLanguage;

public class ProcessorMetaFacetType extends AbstractFacetType<ProcessorMetaFacetConfiguration> {

	public static final FacetTypeId<Facet<ProcessorMetaFacetConfiguration>> TYPEID = new FacetTypeId<Facet<ProcessorMetaFacetConfiguration>>("org.sqlproc.meta.ProcessorMeta");

	public ProcessorMetaFacetType() {
		super(TYPEID, "org.sqlproc.meta.ProcessorMeta", "ProcessorMeta");
		ProcessorMetaLanguage.INSTANCE.injectMembers(this);
	}
}
