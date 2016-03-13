package org.sqlproc.meta.ui;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;

public class ProcessorMetaResourceSetProvider extends XtextResourceSetProvider {

    protected Logger LOGGER = Logger.getLogger(ProcessorMetaResourceSetProvider.class);

    @Override
    public ResourceSet get(IProject project) {
        LOGGER.info("Openning " + project + "...");
        ResourceSet set = super.get(project);
        // System.out.println(set);
        return set;
    }
}
