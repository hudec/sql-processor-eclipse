/*
 * generated by Xtext
 */
package org.sqlproc.dsl.ui.labeling;

import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.ui.label.DefaultDescriptionLabelProvider;

/**
 * Provides labels for a IEObjectDescriptions and IResourceDescriptions.
 * 
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class ProcessorDslDescriptionLabelProvider extends DefaultDescriptionLabelProvider {

    // TODO: when it is used?

    public String text(IEObjectDescription ele) {
        return "" + ele.getName();
    }

    // public String image(IEObjectDescription ele) {
    // return ele.getEClass().getName() + ".gif";
    // }
}
