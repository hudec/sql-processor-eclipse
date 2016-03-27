package org.sqlproc.meta.ui.templates;

import org.eclipse.jface.text.templates.ContextTypeRegistry;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ITemplateAcceptor;
import org.eclipse.xtext.ui.editor.templates.ContextTypeIdHelper;
import org.eclipse.xtext.ui.editor.templates.DefaultTemplateProposalProvider;
import org.sqlproc.meta.services.ProcessorMetaGrammarAccess;

import com.google.inject.Inject;

public class ProcessorTemplateProposalProvider extends DefaultTemplateProposalProvider {

    ContextTypeIdHelper helper;

    @Inject
    public ProcessorTemplateProposalProvider(TemplateStore templateStore, ContextTypeRegistry registry,
            ContextTypeIdHelper helper) {
        super(templateStore, registry, helper);
        this.helper = helper;
    }

    @Inject
    ProcessorMetaGrammarAccess ga;

    protected boolean validate1(Template template, ContentAssistContext context) {
        boolean result = context.getMatcher().isCandidateMatchingPrefix(template.getName(), context.getPrefix());
        return result;
    }

    protected TemplateProposal createProposal1(Template template, TemplateContext templateContext,
            ContentAssistContext context, Image image, int relevance) {
        if (!validate1(template, context))
            return null;
        return doCreateProposal(template, templateContext, context, image, relevance);
    }

    @Override
    protected void createTemplates(TemplateContext templateContext, ContentAssistContext context,
            ITemplateAcceptor acceptor) {
        // "regular templates"
        super.createTemplates(templateContext, context, acceptor);

        if (templateContext.getContextType().getId().equals(helper.getId(ga.getSqlValueRule()))) {

            Template template = new Template("ins", "CRUD insert statement", "insertTemplateID",
                    "\n  insert into ${dbTable}\n  (${dbInsertColumn})\n  {= values (${pojoColumn}) }\n", false);// auto-insertable?
            TemplateProposal tp = createProposal1(template, templateContext, context, getImage(template),
                    getRelevance(template));
            acceptor.accept(tp);
            template = new Template("get", "CRUD get statement", "getTemplateID",
                    "\n  select ${dbSelectColumn}\n  from ${dbTable}\n  {= where${dbCondColumn}\n  }\n", false);// auto-insertable?
            tp = createProposal1(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("upd", "CRUD update statement", "updateTemplateID",
                    "\n  update ${dbTable}\n  {= set (${dbUpdateColumn})}\n  {= where${dbCondColumn}\n  }\n", false);// auto-insertable?
            tp = createProposal1(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template(
                    "upd-opt",
                    "CRUD optimistic update statement",
                    "updateOptTemplateID",
                    "\n  update ${dbTable}\n  {= set (${dbVerUpdateColumn}${dbOptUpdateColumn})}\n  {= where${dbOptCondColumn}\n  }\n",
                    false);// auto-insertable?
            tp = createProposal1(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("del", "CRUD delete statement", "deleteTemplateID",
                    "\n  delete from ${dbTable}\n  {= where${dbCondColumn}\n  }\n", false);// auto-insertable?
            tp = createProposal1(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);

            template = new Template("select", "QRY select statement", "selectTemplateID",
                    "\n  select ${dbSelectColumn}\n  from ${dbTable}\n  {= where${dbCondColumn}\n  }\n", false);// auto-insertable?
            tp = createProposal1(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
        }

        if (templateContext.getContextType().getId().equals(helper.getId(ga.getPropertyRule()))) {

            Template template = new Template("pojos", "Pojos definitions", "pojosTemplateID", "${pojoDefinitions}",
                    false);// auto-insertable?
            TemplateProposal tp = createProposal(template, templateContext, context, getImage(template),
                    getRelevance(template));
            acceptor.accept(tp);
            template = new Template("tables", "Tables definitions", "uniqueTemplateID", "${tableDefinitions}", false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("metagen", "Meta SQL generator", "metagenTemplateID", "${metaGenerator}", false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("procedures", "Procedures definitions", "uniqueTemplateID",
                    "${procedureDefinitions}", false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("functions", "Functions definitions", "uniqueTemplateID", "${functionDefinitions}",
                    false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("hsqldb", "HSQLDB initial control directives", "uniqueTemplateID", HSQLDB, false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("postgresql", "PostgreSQL initial control directives", "uniqueTemplateID",
                    POSTGRESQL, false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("mysql", "MySQL initial control directives", "uniqueTemplateID", MYSQL, false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("oracle", "Oracle initial control directives", "uniqueTemplateID", ORACLE, false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("db2", "DB2 initial control directives", "uniqueTemplateID", DB2, false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
            template = new Template("informix", "Informix initial control directives", "uniqueTemplateID", INFORMIX,
                    false);// auto-insertable?
            tp = createProposal(template, templateContext, context, getImage(template), getRelevance(template));
            acceptor.accept(tp);
        }
    }

    static final String HSQLDB = "resolve-pojo-on;\n" + "database-is-online;\n"
            + "database-jdbc-driver :org.hsqldb.jdbcDriver;\n" + "database-has-url jdbc:hsqldb:mem:hsqldb;\n"
            + "database-login-username sa;\n" + "database-login-password \"\";\n" + "database-take-comments;\n" + "\n"
            + "pojogen-generate-wrappers;\n" + "pojogen-generate-methods toString equals hashCode isDef toInit;\n"
            + "pojogen-implements-interfaces java.io.Serializable;\n" + "pojogen-generate-operators;\n"
            + "pojogen-generate-validation-annotations;\n" + "\n" + "metagen-global-sequence TODO_SEQUENCE_NAME;\n";

    static final String POSTGRESQL = "resolve-pojo-on;\n" + "database-is-online;\n"
            + "database-jdbc-driver :org.postgresql.Driver;\n"
            + "database-has-url jdbc:postgresql:\\//TODO_DB_SERVER:5432/TODO_DB_NAME;\n"
            + "database-login-username TODO_DB_USER;\n" + "database-login-password TODO_DB_PASSWORD;\n"
            + "database-in-catalog TODO_DB_CATALOG;\n" + "database-take-comments;\n" + "\n"
            + "pojogen-generate-wrappers;\n" + "pojogen-generate-methods toString equals hashCode isDef toInit;\n"
            + "pojogen-implements-interfaces java.io.Serializable;\n" + "pojogen-generate-operators;\n"
            + "pojogen-generate-validation-annotations;\n" + "\n"
            + "metagen-table-sequence TODO_TABLE_X TODO_TABLE_X_id_seq;\n";

    static final String MYSQL = "resolve-pojo-on;\n" + "database-is-online;\n"
            + "database-jdbc-driver :com.mysql.jdbc.Driver;\n"
            + "database-has-url jdbc:mysql:\\//TODO_DB_SERVER:3306/TODO_DB_NAME?zeroDateTimeBehavior=convertToNull;\n"
            + "database-login-username TODO_DB_USER;\n" + "database-login-password TODO_DB_PASSWORD;\n"
            + "database-in-catalog TODO_DB_CATALOG;\n" + "database-take-comments;\n" + "\n"
            + "pojogen-generate-wrappers;\n" + "pojogen-generate-methods toString equals hashCode isDef toInit;\n"
            + "pojogen-implements-interfaces java.io.Serializable;\n" + "pojogen-generate-operators;\n"
            + "pojogen-generate-validation-annotations;\n" + "\n" + "metagen-global-identity;\n";

    static final String ORACLE = "resolve-pojo-on;\n" + "database-is-online;\n"
            + "database-jdbc-driver :oracle.jdbc.OracleDriver;\n"
            + "database-has-url jdbc:oracle:thin:@TODO_DB_SERVER:1521:TODO_SID;\n"
            + "database-login-username TODO_DB_USER;\n" + "database-login-password TODO_DB_PASSWORD;\n"
            + "database-active-schema TODO_DB_SCHEMA;\n" + "database-take-comments;\n" + "\n"
            + "pojogen-generate-wrappers;\n" + "pojogen-generate-methods toString equals hashCode isDef toInit;\n"
            + "pojogen-implements-interfaces java.io.Serializable;\n" + "pojogen-generate-operators;\n"
            + "pojogen-generate-validation-annotations;\n" + "\n" + "metagen-global-sequence TODO_SEQUENCE_NAME;\n";

    static final String DB2 = "resolve-pojo-on;\n"
            + "database-is-online;\n"
            + "database-jdbc-driver :com.ibm.db2.jcc.DB2Driver;\n"
            + "database-has-url jdbc:db2:\\//TODO_DB_SERVER:50001/TODO_DB_NAME:deferPrepares=0\\;progressiveStreaming=2\\;;\n"
            + "database-login-username TODO_DB_USER;\n" + "database-login-password TODO_DB_PASSWORD;\n"
            + "database-active-schema TODO_DB_SCHEMA;\n" + "database-take-comments;\n" + "\n"
            + "pojogen-generate-wrappers;\n" + "pojogen-generate-methods toString equals hashCode isDef toInit;\n"
            + "pojogen-implements-interfaces java.io.Serializable;\n" + "pojogen-generate-operators;\n"
            + "pojogen-generate-validation-annotations;\n" + "\n" + "metagen-global-sequence TODO_SEQUENCE_NAME;\n";

    static final String INFORMIX = "resolve-pojo-on;\n"
            + "database-is-online;\n"
            + "database-jdbc-driver :com.informix.jdbc.IfxDriver;\n"
            + "database-has-url jdbc:informix-sqli:\\//TODO_DB_SERVER:1551/TODO_DB_NAME:informixserver=TODO_IFX_NAME\\;DB_LOCALE=en_US\\.57372;\n"
            + "database-login-username TODO_DB_USER;\n" + "database-login-password TODO_DB_PASSWORD;\n"
            + "database-in-catalog TODO_DB_CATALOG;\n" + "database-take-comments;\n" + "\n"
            + "pojogen-generate-wrappers;\n" + "pojogen-generate-methods toString equals hashCode isDef toInit;\n"
            + "pojogen-implements-interfaces java.io.Serializable;\n" + "pojogen-generate-operators;\n"
            + "pojogen-generate-validation-annotations;\n" + "\n" + "metagen-global-sequence TODO_SEQUENCE_NAME;\n";
}
