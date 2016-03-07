package org.sqlproc.plugin.lib.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.LazyStringInputStream;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.common.io.Files;

public class MainUtils {

    public static final int OK = 0;
    public static final int ERROR = 1;
    public static final int REFERENCE_ERROR = 2;
    public static final String REFERENCE_MSG = "Couldn't resolve reference to JvmType";

    public static int isValid(Resource resource, Set<String> failedReferences, IResourceValidator validator)
            throws IOException {
        int result = OK;
        resource.load(null);
        List<Issue> list = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        if (!list.isEmpty()) {
            for (Issue issue : list) {
                String msg = issue.toString();
                System.err.println(msg);
                if (issue.getSeverity() == Severity.ERROR) {
                    if (msg.indexOf(REFERENCE_MSG) < 0)
                        result = ERROR;
                    else {
                        if (result == OK)
                            result = REFERENCE_ERROR;
                        if (failedReferences != null) {
                            msg = msg.substring(REFERENCE_MSG.length(), msg.length());
                            int ix = msg.indexOf("'");
                            if (ix >= 0)
                                msg = msg.substring(ix + 1, msg.length());
                            ix = msg.indexOf("'");
                            if (ix >= 0)
                                msg = msg.substring(0, ix);
                            failedReferences.add(msg);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static String loadResourceToString(Resource resource) throws IOException {
        resource.load(null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        resource.save(baos, null);
        return baos.toString();
    }

    public static String handleResourceReferences(Resource resource, Set<String> failedReferences) throws IOException {
        String content = loadResourceToString(resource);
        for (String ref : failedReferences) {
            content = content.replaceAll(ref, "::" + ref);
        }
        content = content.replaceAll("::::", "::");
        content = content.replaceAll("::::", "::");
        content = content.replaceAll("::::", "::");
        return content;
    }

    public static Resource reloadResourceFromString(String content, ResourceSet resourceSet,
            IResourceFactory resourceFactory, String suffix) throws IOException {
        InputStream is = new LazyStringInputStream(content);
        Resource resource = resourceFactory.createResource(computeUnusedUri(resourceSet, suffix));
        resourceSet.getResources().add(resource);
        resource.load(is, null);
        return resource;
    }

    public static URI computeUnusedUri(ResourceSet resourceSet, String suffix) {
        String name = "__synthetic";
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            URI syntheticUri = URI.createURI(name + i + "." + suffix);
            if (resourceSet.getResource(syntheticUri, false) == null)
                return syntheticUri;
        }
        throw new IllegalStateException();
    }

    public static String getFile(String source, String file) {
        if (file.startsWith(File.separator))
            return file;
        return source + file;
    }

    public static String loadDdl(String source, String ddl) throws IOException {
        if (ddl != null) {
            File file = new File(MainUtils.getFile(source, ddl));
            return new String(Files.toByteArray(file));
        }
        return null;
    }
}
