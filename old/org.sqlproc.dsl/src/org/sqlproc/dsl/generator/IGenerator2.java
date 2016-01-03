package org.sqlproc.dsl.generator;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;

public interface IGenerator2 extends IGenerator {
    public void doGenerate(ResourceSet input, IFileSystemAccess fsa);
}