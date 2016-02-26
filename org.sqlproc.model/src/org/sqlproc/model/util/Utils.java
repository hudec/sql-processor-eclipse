package org.sqlproc.model.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.sqlproc.model.processorModel.Package;
import org.sqlproc.model.processorModel.PojoType;
import org.sqlproc.model.processorModel.ValueType;
import org.sqlproc.plugin.lib.util.CommonUtils;

public class Utils extends CommonUtils {

    // public static List<String> findTables(IQualifiedNameConverter qualifiedNameConverter, Artifacts artifacts,
    // IScope scope) {
    // List<String> names = new ArrayList<String>();
    // Iterable<IEObjectDescription> iterable = scope.getAllElements();
    // for (Iterator<IEObjectDescription> iter = iterable.iterator(); iter.hasNext();) {
    // IEObjectDescription description = iter.next();
    // EObject obj = artifacts.eResource().getResourceSet().getEObject(description.getEObjectURI(), true);
    // if (obj instanceof TableDefinitionImpl)
    // names.add(((TableDefinitionImpl) obj).getTable());
    // }
    // return names;
    // }
    //
    // public static List<String> findFunctions(IQualifiedNameConverter qualifiedNameConverter, Artifacts artifacts,
    // IScope scope) {
    // List<String> names = new ArrayList<String>();
    // Iterable<IEObjectDescription> iterable = scope.getAllElements();
    // for (Iterator<IEObjectDescription> iter = iterable.iterator(); iter.hasNext();) {
    // IEObjectDescription description = iter.next();
    // EObject obj = artifacts.eResource().getResourceSet().getEObject(description.getEObjectURI(), true);
    // if (obj instanceof FunctionDefinitionImpl)
    // names.add(((FunctionDefinitionImpl) obj).getTable());
    // }
    // return names;
    // }
    //
    // public static List<String> findProcedures(IQualifiedNameConverter qualifiedNameConverter, Artifacts artifacts,
    // IScope scope) {
    // List<String> names = new ArrayList<String>();
    // Iterable<IEObjectDescription> iterable = scope.getAllElements();
    // for (Iterator<IEObjectDescription> iter = iterable.iterator(); iter.hasNext();) {
    // IEObjectDescription description = iter.next();
    // EObject obj = artifacts.eResource().getResourceSet().getEObject(description.getEObjectURI(), true);
    // if (obj instanceof ProcedureDefinitionImpl)
    // names.add(((ProcedureDefinitionImpl) obj).getTable());
    // }
    // return names;
    // }

    public static Set<String> getImports(Package packagex, ISerializer serializer) {
        Set<String> imports = new HashSet<String>();
        if (packagex.getImportSection() != null && packagex.getImportSection().getImportDeclarations() != null) {
            for (XImportDeclaration imp : packagex.getImportSection().getImportDeclarations()) {
                imports.add(CommonUtils.removeBlankLines(serializer.serialize(imp)).substring(7));
            }
        }
        return imports;
    }

    public static String getPropertyValue(ValueType pv) {
        String s = _getPropertyValue(pv);
        if (s == null)
            return null;
        if (s.startsWith("$$")) {
            String ss = System.getenv(s.substring(2));
            if (ss != null)
                s = ss;
        }
        return s;
    }

    public static String _getPropertyValue(ValueType pv) {
        if (pv == null)
            return null;
        String s = pv.getValue();
        if (s != null) {
            s = s.trim();
            if (s.startsWith("\""))
                s = s.substring(1);
            if (s.endsWith("\""))
                s = s.substring(0, s.length() - 1);
            return s;
        } else if (pv.getId() != null)
            return pv.getId();
        else
            return "" + pv.getNumber();
    }

    public static String getPropertyValueRegex(ValueType pv) {
        String s = getPropertyValue(pv);
        if (s == null)
            return null;
        // String s2 = s.replaceAll("\\\\", "\\");
        return s;
    }

    public static String getPropertyValue(PojoType pv) {
        if (pv == null)
            return null;
        if (pv.getType() != null) {
            String sType = pv.getType().getQualifiedName();
            if (pv.isArray())
                sType = sType + "[]";
            return sType;
        }
        if (pv.getIdent() != null)
            return getPropertyValue(pv.getIdent());
        if (pv.getRef() != null) {
            if (pv.getRef().getClassx() != null)
                return pv.getRef().getClassx();
            return pv.getRef().getClass_();
        }
        return null;
    }

}
