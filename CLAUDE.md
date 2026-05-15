# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

The **SQL Processor Eclipse Plugin (SQLEP)** — two Xtext-based DSLs packaged as Eclipse plugins and as standalone fat JARs:

- `.meta` files — the META SQL DSL, defined by `org.sqlproc.meta`
- `.qry` / model files — the Processor Model DSL, defined by `org.sqlproc.model`

Build is Tycho + Maven, the languages are Xtext, code is Java + Xtend, target runtime is Java 25.

## Project layout (the big picture)

Two **parallel** Xtext language stacks live side by side, each with the standard Xtext module layout:

```
org.sqlproc.meta.parent/      org.sqlproc.model.parent/
  org.sqlproc.{name}             — runtime: grammar (.xtext), generator Main, scoping, validation
  org.sqlproc.{name}.ide         — generic IDE infrastructure
  org.sqlproc.{name}.ui          — Eclipse UI plugin
  org.sqlproc.{name}.tests       — runtime tests + JDBC integration tests
  org.sqlproc.{name}.ui.tests
  org.sqlproc.{name}.sdk         — feature
  org.sqlproc.{name}.updatesite  — P2 site
```

The two stacks are **independent Maven reactors** (each `*.parent/pom.xml` is its own root). The top-level `pom.xml` aggregates both.

`org.sqlproc.plugin.lib/` is a shared Eclipse bundle of common code (resolvers, property beans, `MainUtils`). It is **not** a Maven module — both `org.sqlproc.meta` and `org.sqlproc.model` re-export its packages via their `MANIFEST.MF`, so they consume it as an OSGi/Eclipse bundle dependency rather than a Maven dep.

## Build & test commands

From the repo root:

```bash
mvn clean install                                     # both meta + model
```

From a single parent (faster iterate):

```bash
cd org.sqlproc.meta.parent && mvn clean install
cd org.sqlproc.model.parent && mvn clean install
```

Run only one module's tests with deps:

```bash
cd org.sqlproc.meta.parent
mvn clean verify -pl org.sqlproc.meta.tests -am          # see ./test script at repo root
```

Maven repos are pinned in each parent `pom.xml` to **Eclipse 2026-03 P2**, **Xtext 2.42.0**, and **Orbit 2024-12**. Tycho is `5.0.2`. `maven.compiler.source/target = 25`; Xtend `javaSourceVersion = 21`.

## Xtext code generation (important)

`generate-sources` runs `org.eclipse.emf.mwe2.launch.runtime.Mwe2Launcher` against:

- `org.sqlproc.meta/src/org/sqlproc/meta/GenerateProcessorMeta.mwe2`
- `org.sqlproc.model/src/org/sqlproc/model/GenerateProcessorModel.mwe2`

These workflows write generated Java into the sibling projects' `src-gen/` (`*.ide`, `*.ui`, `*.tests`, `*.ui.tests` all receive generated sources from the central `.mwe2`). Xtend sources also generate into `xtend-gen/`.

- **Do not edit `src-gen/` or `xtend-gen/`** — they are wiped by `mvn clean` (see the `maven-clean-plugin` config in `org.sqlproc.meta/pom.xml`) and regenerated from `.xtext` + `.mwe2` + `.xtend`.
- If grammar (`.xtext`) or workflow (`.mwe2`) changes don't take effect, run `mvn generate-sources` in the runtime project (e.g. `org.sqlproc.meta`) — Eclipse incremental builders do **not** re-run mwe2.

The mwe2 launcher needs many `--add-opens` flags on Java 25 (already wired in the runtime project's `pom.xml`); preserve them when editing the exec-maven-plugin config.

## Fat JAR (CLI generator) builds

`org.sqlproc.meta` and `org.sqlproc.model` produce **`sqlmeta.jar` / `sqlmodel.jar`** during the Maven `package` phase via `maven-antrun-plugin`. The packaging:

- uses Eclipse's `jar-in-jar-loader.zip` (at the repo root) so dependency JARs are nested inside the fat jar
- main class: `org.sqlproc.{meta,model}.generator.Main` (run via `org.eclipse.jdt.internal.jarinjarloader.JarRsrcLoader`)
- pulls dependency JARs from Tycho-resolved `maven.compile.classpath`

The legacy hand-curated `sqlmeta.xml` / `sqlmodel.xml` ANT files at the repo root are **not** part of the Maven build; they were the old Eclipse "Export → Runnable JAR" workflow and reference user-specific paths (`/home/hudec/eclipse/plugins`). Treat them as historical — the Maven antrun execution is the source of truth.

## Tests against real databases

`org.sqlproc.{meta,model}.tests/` ship JUnit tests that connect to live databases:

- `MainH2IntegrationTest.java` — embedded H2, no external setup
- `MainPostgresIntegrationTest.java` — requires a running PostgreSQL; skips if driver/connection unavailable

Both PostgreSQL and H2 JDBC drivers are declared as **Maven `test`-scoped deps** and as **Eclipse Orbit OSGi bundles**. Consequence: inside Eclipse, these tests must be run as **JUnit Plug-in Test**, not as plain JUnit Test — only the Plug-in Test runtime puts the Orbit driver bundle on the classpath. The pre-configured launch configs (`*.launch` files in `org.sqlproc.meta.tests/`) and the README files there document this; the Maven `verify` build always works.

## Version bumps

The `UPD` script at the repo root encodes the version-bump ritual — `sed`s across all `pom.xml`, `MANIFEST.MF`, and `feature.xml` to replace the SNAPSHOT version (Maven `X.Y.Z-SNAPSHOT`) and the Tycho qualifier (`X.Y.Z.qualifier`). Edit `UPD` to the new from/to versions, run it, and verify with the trailing `grep -R` lines.

## Things to know when editing

- Grammar lives in `*.xtext`; semantic helpers live in `scoping/`, `validation/`, `generator/`, `property/`, `util/` under each runtime project (`.xtend` is common).
- `org.sqlproc.{meta,model}.generator.Main` is the CLI entry point used by `sqlmeta.jar` / `sqlmodel.jar`; it wires Guice via the runtime module and uses `org.sqlproc.plugin.lib.util.MainUtils` for shared CLI plumbing.
- The `pkg/` directories under each parent are intentionally empty (just `.gitkeep`); they're staging dirs.
- The TravisCI config (`.travis.yml`) targets `openjdk11` and is stale relative to the current Java 25 build — do not treat it as authoritative.
