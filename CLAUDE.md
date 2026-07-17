# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

"Investigative Code" — Tiffany's personal Java sandbox for language experiments and coding-interview
practice. There is no single application; each package under `src/main/java/com/tiffanytimbric/play/`
is an independent, self-contained exercise with its own `main()`. Code here favors trying things out
over production hardening.

## Build

The POM requires **exactly Java 26** (`maven-enforcer-plugin` enforces this) and compiles with
`--enable-preview`. The default `java`/`mvn` on PATH in this environment resolve to Java 21, which
will fail the enforcer check, so `JAVA_HOME` must be pointed at a JDK 26 install for every Maven
invocation:

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-26.jdk/Contents/Home
mvn compile
mvn test
mvn package
```

Run a single test class:

```bash
mvn test -Dtest=PrimeCheckerTest
```

Run a packaged build (built jar + `lib/` + `dist/` on the classpath):

```bash
bin/play
```

## Architecture

All Java sources live under two roots: application code in
`src/main/java/com/tiffanytimbric/play/` and `src/main/java/com/tiffanytimbric/util/`, mirrored by
tests in `src/test/java/...`. Packages are organized by *topic*, not by shared component — reading
one package tells you almost nothing about the others:

- `play/PrimeChecker.java` — standalone CLI (`main`) that checks primality of args passed on the
  command line.
- `play/hello/` — the jar's actual `Main-Class` (see `src/main/resources/META-INF/MANIFEST.MF`).
  A minimal `HelloWorldFunction` → `ResultStatus` pipeline used as the entry point for `bin/play`.
- `play/tp/` — a stdin-line-processing exercise (balanced-parens checker).
- `play/pp/` — thread-safety / singleton pattern experiments (`Individual`'s double-checked-locking
  singleton).
- `play/j26/` — Java 26 language-feature experiments, currently structured concurrency via
  `StructuredTaskScope` (`OrderService.handleOrder`, using `Joiner.awaitAllSuccessfulOrThrow()` to
  fork/join concurrent subtasks on virtual threads and fail fast).
- `play/interview/` — one subpackage per interview/company context, each an independent solution to
  a specific problem (not a shared framework):
  - `cfs/` — custom singly linked list (`MyLinkedList`) and utilities.
  - `delbridge/` — bracket-matching checker over custom bracket-type data.
  - `intuit/` — a small directed graph model (`Graph`/`Node`/`Edge`/`NodeType`) plus
    `songs/JingleUtil`/`MuzakUtil`, which build and JSON-serialize (Jackson) sample graphs
    representing radio jingle structures.
  - `rome/` — the *same* "find Rome" problem solved four independent ways for comparison:
    `FindRomeImperative`, `FindRomeFunctional`, `FindRomeReactive` (Project Reactor), and
    `FindRomeRx`. When editing one, check whether the others need the equivalent change.
  - `ware2go/` — `SummationUtil`, numeric summation exercise.
- `util/LangUtil.java` — small shared `Optional`/null-safety helpers (`opt`, `optNull`, `item`) used
  across a few of the exercises; this is the one genuinely shared piece of code in the repo.

## Conventions observed in the code

- Parameters/locals are heavily `final`, annotated `@Nonnull`/`@Nullable` (JSR-305), and guarded with
  explicit `IllegalArgumentException` checks at public entry points rather than relying on
  `Objects.requireNonNull`.
- `main` methods generally set a process exit code (see `PrimeChecker.EXIT_CODE_*`,
  `hello.Main`/`ResultStatus`) instead of throwing out of `main`.
- Tests extend JUnit 3's `TestCase` but use JUnit 4 `@Test` annotations and JUnit 4 assertions
  (mixed style already present in the repo, e.g. `PrimeCheckerTest`) — follow this existing pattern
  rather than introducing JUnit 5.
- Apache Commons (`lang3`, `collections4`) are the default toolkit for null/empty checks; Jackson is
  used where JSON output is needed (`interview/intuit/songs`).
