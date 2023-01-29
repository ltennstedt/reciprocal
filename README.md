# reciprocal: Mathematical library for the JVM

![GitHub Actions](https://github.com/ltennstedt/reciprocal/actions/workflows/maven.yml/badge.svg)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=bugs)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ltennstedt_reciprocal&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=ltennstedt_reciprocal)

reciprocal is a free software library for the [JVM](https://www.java.com/) that provides or will provide
implementations of fractions, complex numbers, vectors, matrices, polynomials and geometric shapes and their arithmetic
and properties.

What reciprocal will provide:

* Fractions and complex numbers
* Vectors and matrices
* Determinant calculation
* Polynomials
* Circles and rectangles
* Pseudorandom number generators
* [Kotlin](https://kotlinlang.org/) module (separate repository)
* [Scala](https://scala-lang.org/) module (separate repository)
* Custom assertions for [AssertJ](https://assertj.github.io/doc/)

What reciprocal will not provide:

* Solver for equations
* Matrix decomposition, reduction or factorization

## Building

A [JDK](https://adoptium.net/) in version 17 and [Maven](https://maven.apache.org) in version 3.8 is needed to
build this project.

```shell script
mvn install site
```

## Implementation details

* Java 17
* All types are immutable.
* Absolutely no null values
* Numbers extend `java.lang.Number` and fractions implements `java.lang.Comparable`
* Comparators implement `java.lang.Comparator`
* Numbers, vectors, matrices and polynomials implement `java.io.Serializable`
* Classes of numbers, vectors and matrices are final.
* Useful hashCode, equals and toString methods
* Builders for vectors and matrices
* Parameter validation and fast failing
* Nullability annotations

## Usage

```java
class Class {
    void method() {
        // arithmetic methods follow the naming scheme of BigInteger/BigDecimal
        new Fraction(2L, 3L).add(new Fraction(3L, 4L));

        // getting an element of a vector or matrix
        vector.get(1);
        matrix.get(2, 3);

        // check if an element is contained in a vector or matrix
        if (vector.contains(0L)) {
            doSomethingCool();
        }

        // classic builders for vectors and matrices with a fluent API        
        LongVector.ofSize(3)
                .computationOfAbsentees(i -> new SecureRandom().nextLong()) // default: i -> 0L
                .set(1, 1L)
                .set(3, 2L)
                .build();
    }
}
```

## Goals

* Complete Javadoc
* Clean code
* High code quality with the help of [Checkstyle](https://checkstyle.org/), [PMD](https://pmd.github.io/),
  [SpotBugs](https://spotbugs.github.io/),  [SonarLint](https://www.sonarlint.org/) and
  [SonarCloud](https://sonarcloud.io/)
* High code coverage with the help of [JUnit](https://junit.org/junit5/), [AssertJ](https://assertj.github.io/doc/),
  [Mockito](https://site.mockito.org/), [JaCoCo](https://www.jacoco.org/jacoco/) and
  [SonarCloud](https://sonarcloud.io/)
* Stay up-to-date with versions of libraries and plugins
* Object-oriented design with functional abstraction
* Being [Groovy](https://groovy-lang.org/), [Kotlin](https://www.kotlinlang.org/) and [Scala](https://scala-lang.org/)
  friendly
* Use nullability annotations as much as possible

## Links

* [Maven Site](https://ltennstedt.github.io/reciprocal/index.html)
* [reciprocal on SonarCloud](https://sonarcloud.io/project/overview?id=ltennstedt_reciprocal)
