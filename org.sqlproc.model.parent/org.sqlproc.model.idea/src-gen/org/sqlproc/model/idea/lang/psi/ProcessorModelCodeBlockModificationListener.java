/*
 * generated by Xtext 2.12.0
 */
package org.sqlproc.model.idea.lang.psi;

import com.intellij.psi.impl.PsiTreeChangeEventImpl;
import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;
import org.sqlproc.model.idea.lang.ProcessorModelLanguage;

public class ProcessorModelCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public ProcessorModelCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(ProcessorModelLanguage.INSTANCE, psiModificationTracker);
	}
	protected boolean hasJavaStructuralChanges(PsiTreeChangeEventImpl event) {
		return true;
	}

}
