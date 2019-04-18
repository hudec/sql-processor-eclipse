sql-processor-eclipse
=====================

The SQL Processor Eclipse Plugin (SQLEP).

To import this plugin into IDE the STS 3.6.0 plus Xtext 2.6 are required.

The minimum installation is the next one:
- spring-tool-suite-3.6.0.RELEASE-e4.4-linux-gtk-x86_64.tar.gz
and the plugins:
 - Xtext SDK 2.6.0
 - MWE 2 language SDK 2.6.0
 - MWE 2 runtime SDK 2.6.0
 - EMF Eclipse Modeling Framework SDK 2.10.0
and finally Java 7.

To install SQLEP, the next P2 repos can be used
 - http://sql-processor.eu/plugins/milestones/
 - http://sql-processor.eu/plugins/releases/

To build from the command line the Maven 3 is required plus the next command:

    cd ~/git/sql-processor-eclipse
    mvn clean install -Dtycho.targetPlatform=PATH_TO_ECLIPSE

or simple (using Eclipse P2 repositories)

    mvn clean install

To install this plugin from the build, copy the next jars into eclipse/dropins directory:
 - ./org.sqlproc.dsl/target/org.sqlproc.dsl-1.9.3-SNAPSHOT.jar
 - ./org.sqlproc.dsl.ui/target/org.sqlproc.dsl.ui-1.9.3-SNAPSHOT.jar

For example

    cp ./org.sqlproc.dsl/target/org.sqlproc.dsl-1.9.3-SNAPSHOT.jar ~/springsource/sts-3.6.0.RELEASE/dropins/
    cp ./org.sqlproc.dsl.ui/target/org.sqlproc.dsl.ui-1.9.3-SNAPSHOT.jar ~/springsource/sts-3.6.0.RELEASE/dropins/


