package org.sqlproc.model.scoping;

import org.eclipse.xtext.common.types.access.ClasspathTypeProviderFactory;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.access.impl.ClasspathTypeProvider;
import org.eclipse.xtext.common.types.xtext.AbstractConstructorScope;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider;
import org.eclipse.xtext.common.types.xtext.ClasspathBasedConstructorScope;
import org.eclipse.xtext.common.types.xtext.ClasspathBasedTypeScope;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

public class ProcessorModelClasspathBasedTypeScopeProvider extends AbstractTypeScopeProvider {

    @Inject
    private ProcessorModelClasspathTypeProviderFactory typeProviderFactory;

    @Inject
    private IQualifiedNameConverter qualifiedNameConverter;

    @Override
    public ClasspathBasedTypeScope createTypeScope(IJvmTypeProvider typeProvider,
            Predicate<IEObjectDescription> filter) {
        return new ClasspathBasedTypeScope((ClasspathTypeProvider) typeProvider, qualifiedNameConverter, filter);
    }

    @Override
    public AbstractConstructorScope createConstructorScope(IJvmTypeProvider typeProvider,
            Predicate<IEObjectDescription> filter) {
        ClasspathBasedTypeScope typeScope = createTypeScope(typeProvider, filter);
        return new ClasspathBasedConstructorScope(typeScope);
    }

    public void setTypeProviderFactory(ProcessorModelClasspathTypeProviderFactory typeProviderFactory) {
        this.typeProviderFactory = typeProviderFactory;
    }

    @Override
    public ClasspathTypeProviderFactory getTypeProviderFactory() {
        return typeProviderFactory;
    }

}
