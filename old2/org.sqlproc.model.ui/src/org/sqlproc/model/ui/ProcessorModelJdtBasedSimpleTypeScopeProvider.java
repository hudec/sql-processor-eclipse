package org.sqlproc.model.ui;

import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.IJdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;
import org.eclipse.xtext.common.types.xtext.AbstractConstructorScope;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider;
import org.eclipse.xtext.common.types.xtext.ui.JdtBasedConstructorScope;
import org.eclipse.xtext.common.types.xtext.ui.JdtBasedSimpleTypeScope;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;
import com.google.inject.Inject;

public class ProcessorModelJdtBasedSimpleTypeScopeProvider extends AbstractTypeScopeProvider {

    @Inject
    private ProcessorModelJdtTypeProviderFactory typeProviderFactory;

    @Inject
    private IQualifiedNameConverter qualifiedNameConverter;

    @Inject
    public ProcessorModelJdtBasedSimpleTypeScopeProvider() {
    }

    @Override
    public AbstractConstructorScope createConstructorScope(IJvmTypeProvider typeProvider,
            Predicate<IEObjectDescription> filter) {
        JdtBasedSimpleTypeScope typeScope = createTypeScope(typeProvider, filter);
        return new JdtBasedConstructorScope(typeScope);
    }

    public ProcessorModelJdtBasedSimpleTypeScopeProvider(ProcessorModelJdtTypeProviderFactory typeProviderFactory,
            IQualifiedNameConverter qualifiedNameConverter) {
        this.typeProviderFactory = typeProviderFactory;
        this.qualifiedNameConverter = qualifiedNameConverter;
    }

    @Override
    public JdtBasedSimpleTypeScope createTypeScope(IJvmTypeProvider typeProvider,
            Predicate<IEObjectDescription> filter) {
        return new JdtBasedSimpleTypeScope((IJdtTypeProvider) typeProvider, qualifiedNameConverter, filter);
    }

    @Override
    public JdtTypeProviderFactory getTypeProviderFactory() {
        return typeProviderFactory;
    }

}
