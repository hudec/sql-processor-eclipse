package org.sqlproc.dsl;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static org.eclipse.xtext.util.Strings.equal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmArrayType;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmPrimitiveType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmVoid;
import org.sqlproc.dsl.processorDsl.EnumEntity;
import org.sqlproc.dsl.processorDsl.Package;
import org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty;
import org.sqlproc.dsl.processorDsl.PojoDao;
import org.sqlproc.dsl.processorDsl.PojoEntity;

public class ImportManager {

    private Map<String, String> imports = newHashMap();

    private boolean organizeImports;

    private String thisTypeSimpleName;
    private String thisTypeQualifiedName;

    private final char innerTypeSeparator;

    public ImportManager(boolean organizeImports, char innerTypeSeparator) {
        this(organizeImports, null, innerTypeSeparator);
    }

    public ImportManager(boolean organizeImports) {
        this(organizeImports, null);
    }

    public ImportManager(boolean organizeImports, JvmDeclaredType thisType) {
        this(organizeImports, thisType, '.');
    }

    public ImportManager(boolean organizeImports, JvmDeclaredType thisType, char innerTypeSeparator) {
        this.organizeImports = organizeImports;
        this.innerTypeSeparator = innerTypeSeparator;
        if (thisType != null) {
            thisTypeSimpleName = thisType.getSimpleName();
            thisTypeQualifiedName = thisType.getQualifiedName(innerTypeSeparator);
            thisCollidesWithJavaLang = CodeGenUtil.isJavaLangType(thisTypeSimpleName);
        }
    }

    public CharSequence serialize(JvmType type) {
        StringBuilder sb = new StringBuilder();
        appendType(type, sb);
        return sb;
    }

    private Pattern JAVA_LANG_PACK = Pattern.compile("java\\.lang\\.[\\w]+");

    private boolean thisCollidesWithJavaLang;

    public void appendType(final JvmType type, StringBuilder builder) {
        if (type instanceof JvmPrimitiveType || type instanceof JvmVoid || type instanceof JvmTypeParameter) {
            builder.append(type.getQualifiedName(innerTypeSeparator));
        } else if (type instanceof JvmArrayType) {
            appendType(((JvmArrayType) type).getComponentType(), builder);
            builder.append("[]");
        } else {
            final String qualifiedName = type.getQualifiedName(innerTypeSeparator);
            final String simpleName = type.getSimpleName();
            if (allowsSimpleName(qualifiedName, simpleName)) {
                builder.append(simpleName);
            } else if (needsQualifiedName(qualifiedName, simpleName)) {
                builder.append(qualifiedName);
            } else {
                if (imports.containsKey(simpleName)) {
                    if (qualifiedName.equals(imports.get(simpleName))) {
                        builder.append(simpleName);
                    } else {
                        builder.append(qualifiedName);
                    }
                } else {
                    imports.put(simpleName, qualifiedName);
                    builder.append(simpleName);
                }
            }
        }
    }

    protected boolean allowsSimpleName(String qualifiedName, String simpleName) {
        return equal(qualifiedName, thisTypeQualifiedName)
                || (!thisCollidesWithJavaLang && JAVA_LANG_PACK.matcher(qualifiedName).matches())
                || equal(qualifiedName, simpleName);
    }

    protected boolean needsQualifiedName(String qualifiedName, String simpleName) {
        return !organizeImports
                || (equal(simpleName, thisTypeSimpleName) && !equal(qualifiedName, thisTypeQualifiedName))
                || CodeGenUtil.isJavaLangType(simpleName);
    }

    public boolean addImportFor(JvmType type) {
        final String qualifiedName = type.getQualifiedName(innerTypeSeparator);
        final String simpleName = type.getSimpleName();
        if (!allowsSimpleName(qualifiedName, simpleName) && !needsQualifiedName(qualifiedName, simpleName)
                && !imports.containsKey(simpleName)) {
            imports.put(simpleName, qualifiedName);
            return true;
        }
        return false;
    }

    public boolean addImportFor(PojoEntity type) {
        final Package packageDeclaration = EcoreUtil2.getContainerOfType(type, Package.class);
        final String simpleName = type.getName();
        final String qualifiedName = packageDeclaration.getName() + "." + simpleName;
        if (!allowsSimpleName(qualifiedName, simpleName) && !needsQualifiedName(qualifiedName, simpleName)
                && !imports.containsKey(simpleName)) {
            imports.put(simpleName, qualifiedName);
            return true;
        }
        return false;
    }

    public boolean addImportFor(EnumEntity type) {
        final Package packageDeclaration = EcoreUtil2.getContainerOfType(type, Package.class);
        final String simpleName = type.getName();
        final String qualifiedName = packageDeclaration.getName() + "." + simpleName;
        if (!allowsSimpleName(qualifiedName, simpleName) && !needsQualifiedName(qualifiedName, simpleName)
                && !imports.containsKey(simpleName)) {
            imports.put(simpleName, qualifiedName);
            return true;
        }
        return false;
    }

    public boolean addImportFor(PojoDao type) {
        final Package packageDeclaration = EcoreUtil2.getContainerOfType(type, Package.class);
        final String simpleName = type.getName();
        final String qualifiedName = packageDeclaration.getName() + "." + simpleName;
        if (!allowsSimpleName(qualifiedName, simpleName) && !needsQualifiedName(qualifiedName, simpleName)
                && !imports.containsKey(simpleName)) {
            imports.put(simpleName, qualifiedName);
            return true;
        }
        return false;
    }

    public boolean addImportFor(PojoAnnotatedProperty type, String name) {
        final Package packageDeclaration = EcoreUtil2.getContainerOfType(type, Package.class);
        final String simpleName = name;
        final String qualifiedName = packageDeclaration.getName() + "." + simpleName;
        if (!allowsSimpleName(qualifiedName, simpleName) && !needsQualifiedName(qualifiedName, simpleName)
                && !imports.containsKey(simpleName)) {
            imports.put(simpleName, qualifiedName);
            return true;
        }
        return false;
    }

    public List<String> getImports() {
        ArrayList<String> result = newArrayList(imports.values());
        Collections.sort(result);
        return result;
    }
}
