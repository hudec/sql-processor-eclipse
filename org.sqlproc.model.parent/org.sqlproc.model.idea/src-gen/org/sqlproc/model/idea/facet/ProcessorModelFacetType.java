/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model.idea.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetTypeId;
import org.eclipse.xtext.idea.facet.AbstractFacetType;
import org.sqlproc.model.idea.lang.ProcessorModelLanguage;

public class ProcessorModelFacetType extends AbstractFacetType<ProcessorModelFacetConfiguration> {

	public static final FacetTypeId<Facet<ProcessorModelFacetConfiguration>> TYPEID = new FacetTypeId<Facet<ProcessorModelFacetConfiguration>>("org.sqlproc.model.ProcessorModel");

	public ProcessorModelFacetType() {
		super(TYPEID, "org.sqlproc.model.ProcessorModel", "ProcessorModel");
		ProcessorModelLanguage.INSTANCE.injectMembers(this);
	}
}
