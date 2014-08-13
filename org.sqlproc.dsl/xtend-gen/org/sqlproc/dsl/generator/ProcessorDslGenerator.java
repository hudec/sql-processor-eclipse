/**
 * generated by Xtext
 */
package org.sqlproc.dsl.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.sqlproc.dsl.generator.IGenerator2;
import org.sqlproc.dsl.generator.ProcessorDaoGenerator;
import org.sqlproc.dsl.generator.ProcessorPojoGenerator;
import org.sqlproc.dsl.processorDsl.AnnotatedEntity;
import org.sqlproc.dsl.processorDsl.PojoDao;

/**
 * Generates code from your model files on save.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
@SuppressWarnings("all")
public class ProcessorDslGenerator implements IGenerator2 {
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  @Inject
  @Extension
  private ProcessorPojoGenerator _processorPojoGenerator;
  
  @Inject
  @Extension
  private ProcessorDaoGenerator _processorDaoGenerator;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<AnnotatedEntity> _filter = Iterables.<AnnotatedEntity>filter(_iterable, AnnotatedEntity.class);
    for (final AnnotatedEntity e : _filter) {
      EObject _eContainer = e.eContainer();
      QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(_eContainer);
      String _string = _fullyQualifiedName.toString("/");
      String _plus = (_string + "/");
      QualifiedName _fullyQualifiedName_1 = this._iQualifiedNameProvider.getFullyQualifiedName(e);
      String _plus_1 = (_plus + _fullyQualifiedName_1);
      String _plus_2 = (_plus_1 + ".java");
      CharSequence _compile = this._processorPojoGenerator.compile(e);
      fsa.generateFile(_plus_2, _compile);
    }
    TreeIterator<EObject> _allContents_1 = resource.getAllContents();
    Iterable<EObject> _iterable_1 = IteratorExtensions.<EObject>toIterable(_allContents_1);
    Iterable<PojoDao> _filter_1 = Iterables.<PojoDao>filter(_iterable_1, PojoDao.class);
    for (final PojoDao d : _filter_1) {
      String _implPackage = this._processorDaoGenerator.getImplPackage(d);
      boolean _notEquals = (!Objects.equal(_implPackage, null));
      if (_notEquals) {
        EObject _eContainer_1 = d.eContainer();
        QualifiedName _fullyQualifiedName_2 = this._iQualifiedNameProvider.getFullyQualifiedName(_eContainer_1);
        String _string_1 = _fullyQualifiedName_2.toString("/");
        String _plus_3 = (_string_1 + "/");
        QualifiedName _fullyQualifiedName_3 = this._iQualifiedNameProvider.getFullyQualifiedName(d);
        String _plus_4 = (_plus_3 + _fullyQualifiedName_3);
        String _plus_5 = (_plus_4 + ".java");
        CharSequence _compileIfx = this._processorDaoGenerator.compileIfx(d);
        fsa.generateFile(_plus_5, _compileIfx);
        EObject _eContainer_2 = d.eContainer();
        QualifiedName _fullyQualifiedName_4 = this._iQualifiedNameProvider.getFullyQualifiedName(_eContainer_2);
        String _string_2 = _fullyQualifiedName_4.toString("/");
        String _plus_6 = (_string_2 + "/");
        String _implPackage_1 = this._processorDaoGenerator.getImplPackage(d);
        String _plus_7 = (_plus_6 + _implPackage_1);
        String _plus_8 = (_plus_7 + "/");
        QualifiedName _fullyQualifiedName_5 = this._iQualifiedNameProvider.getFullyQualifiedName(d);
        String _plus_9 = (_plus_8 + _fullyQualifiedName_5);
        String _plus_10 = (_plus_9 + "Impl.java");
        CharSequence _compile_1 = this._processorDaoGenerator.compile(d);
        fsa.generateFile(_plus_10, _compile_1);
      } else {
        EObject _eContainer_3 = d.eContainer();
        QualifiedName _fullyQualifiedName_6 = this._iQualifiedNameProvider.getFullyQualifiedName(_eContainer_3);
        String _string_3 = _fullyQualifiedName_6.toString("/");
        String _plus_11 = (_string_3 + "/");
        QualifiedName _fullyQualifiedName_7 = this._iQualifiedNameProvider.getFullyQualifiedName(d);
        String _plus_12 = (_plus_11 + _fullyQualifiedName_7);
        String _plus_13 = (_plus_12 + ".java");
        CharSequence _compile_2 = this._processorDaoGenerator.compile(d);
        fsa.generateFile(_plus_13, _compile_2);
      }
    }
  }
  
  public void doGenerate(final ResourceSet resource, final IFileSystemAccess fsa) {
    TreeIterator<Notifier> _allContents = resource.getAllContents();
    Iterable<Notifier> _iterable = IteratorExtensions.<Notifier>toIterable(_allContents);
    Iterable<AnnotatedEntity> _filter = Iterables.<AnnotatedEntity>filter(_iterable, AnnotatedEntity.class);
    for (final AnnotatedEntity e : _filter) {
      EObject _eContainer = e.eContainer();
      QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(_eContainer);
      String _string = _fullyQualifiedName.toString("/");
      String _plus = (_string + "/");
      QualifiedName _fullyQualifiedName_1 = this._iQualifiedNameProvider.getFullyQualifiedName(e);
      String _plus_1 = (_plus + _fullyQualifiedName_1);
      String _plus_2 = (_plus_1 + ".java");
      CharSequence _compile = this._processorPojoGenerator.compile(e);
      fsa.generateFile(_plus_2, _compile);
    }
    TreeIterator<Notifier> _allContents_1 = resource.getAllContents();
    Iterable<Notifier> _iterable_1 = IteratorExtensions.<Notifier>toIterable(_allContents_1);
    Iterable<PojoDao> _filter_1 = Iterables.<PojoDao>filter(_iterable_1, PojoDao.class);
    for (final PojoDao d : _filter_1) {
      String _implPackage = this._processorDaoGenerator.getImplPackage(d);
      boolean _notEquals = (!Objects.equal(_implPackage, null));
      if (_notEquals) {
        EObject _eContainer_1 = d.eContainer();
        QualifiedName _fullyQualifiedName_2 = this._iQualifiedNameProvider.getFullyQualifiedName(_eContainer_1);
        String _string_1 = _fullyQualifiedName_2.toString("/");
        String _plus_3 = (_string_1 + "/");
        QualifiedName _fullyQualifiedName_3 = this._iQualifiedNameProvider.getFullyQualifiedName(d);
        String _plus_4 = (_plus_3 + _fullyQualifiedName_3);
        String _plus_5 = (_plus_4 + ".java");
        CharSequence _compileIfx = this._processorDaoGenerator.compileIfx(d);
        fsa.generateFile(_plus_5, _compileIfx);
        EObject _eContainer_2 = d.eContainer();
        QualifiedName _fullyQualifiedName_4 = this._iQualifiedNameProvider.getFullyQualifiedName(_eContainer_2);
        String _string_2 = _fullyQualifiedName_4.toString("/");
        String _plus_6 = (_string_2 + "/");
        String _implPackage_1 = this._processorDaoGenerator.getImplPackage(d);
        String _plus_7 = (_plus_6 + _implPackage_1);
        String _plus_8 = (_plus_7 + "/");
        QualifiedName _fullyQualifiedName_5 = this._iQualifiedNameProvider.getFullyQualifiedName(d);
        String _plus_9 = (_plus_8 + _fullyQualifiedName_5);
        String _plus_10 = (_plus_9 + "Impl.java");
        CharSequence _compile_1 = this._processorDaoGenerator.compile(d);
        fsa.generateFile(_plus_10, _compile_1);
      } else {
        EObject _eContainer_3 = d.eContainer();
        QualifiedName _fullyQualifiedName_6 = this._iQualifiedNameProvider.getFullyQualifiedName(_eContainer_3);
        String _string_3 = _fullyQualifiedName_6.toString("/");
        String _plus_11 = (_string_3 + "/");
        QualifiedName _fullyQualifiedName_7 = this._iQualifiedNameProvider.getFullyQualifiedName(d);
        String _plus_12 = (_plus_11 + _fullyQualifiedName_7);
        String _plus_13 = (_plus_12 + ".java");
        CharSequence _compile_2 = this._processorDaoGenerator.compile(d);
        fsa.generateFile(_plus_13, _compile_2);
      }
    }
  }
}
