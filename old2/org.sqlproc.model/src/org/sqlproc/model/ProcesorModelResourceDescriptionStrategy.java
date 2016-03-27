package org.sqlproc.model;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescriptionStrategy;

public class ProcesorModelResourceDescriptionStrategy extends XbaseResourceDescriptionStrategy {

    private static final Logger LOG = Logger.getLogger(ProcesorModelResourceDescriptionStrategy.class);

    @Override
    protected boolean isResolvedAndExternal(EObject from, EObject to) {
        if (to == null)
            return false;
        if (!to.eIsProxy()) {
            if (to.eResource() == null) {
                // Workaround
                if (isGeneratedDaoCode(from)) {
                    return false;
                }
                LOG.error("Reference from " + EcoreUtil.getURI(from) + " to " + to
                        + " cannot be exported as the target is not contained in a resource.");
                return false;
            }
            return from.eResource() != to.eResource();
        }
        return !getLazyURIEncoder()
                .isCrossLinkFragment(from.eResource(), ((InternalEObject) to).eProxyURI().fragment());
    }

    private boolean isGeneratedDaoCode(EObject from) {
        // from instanceof JvmParameterizedTypeReference
        // from.eContainer() instanceof JvmFormalParameter
        // from.eContainer().eContainer() instanceof JvmOperation
        // from.eContainer().eContainer().eContainer() instanceof JvmGenericType
        if (from == null || !(from instanceof JvmParameterizedTypeReference))
            return false;
        for (EObject cont = from.eContainer(); cont != null; cont = cont.eContainer()) {
            if (cont instanceof JvmGenericType
                    && (((JvmGenericType) cont).getSimpleName().endsWith("Dao") || ((JvmGenericType) cont)
                            .getSimpleName().endsWith("DaoImpl")))
                return true;
        }
        return false;
    }

    // private void info(EObject eo) {
    // if (eo == null)
    // return;
    // System.out.println(eo.eClass());
    // System.out.println(eo.eContainer());
    // System.out.println(eo.eContainingFeature());
    // System.out.println(eo.eContainmentFeature());
    // System.out.println(eo.eResource());
    // info(eo.eContainer());
    // }
}
