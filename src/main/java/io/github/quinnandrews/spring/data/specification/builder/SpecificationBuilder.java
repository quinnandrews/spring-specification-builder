package io.github.quinnandrews.spring.data.specification.builder;

import jakarta.persistence.metamodel.PluralAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.Objects;

/**
 * Generates and composes Specifications with a fluent-API that is easy to read.
 *
 * @param <T> The Entity Type to query on as an Aggregate Root.
 *
 * @author Quinn Andrews
 */
public class SpecificationBuilder<T> {

    private final SpecificationFactory specificationFactory = new SpecificationFactory();
    
    private Specification<T> specification;

    public SpecificationBuilder() {
        // no-op
    }

    public static <T> SpecificationBuilder<T> withRoot(final Class<T> root) {
        Objects.requireNonNull(root, "Argument 'root' cannot be null.");
        return new SpecificationBuilder<>();
    }

    public Specification<T> toSpecification() {
        return specification;
    }

    public SpecificationBuilder<T> where() {
        return this;
    }

    public SpecificationBuilder<T> where(final Specification<T> specification) {
        Objects.requireNonNull(specification, "Argument 'specification' cannot be null.");
        this.specification = this.specification == null ?
                Specification.where(specification) : this.specification.and(specification);
        return this;
    }

    public SpecificationBuilder<T> and() {
        return this;
    }

    public SpecificationBuilder<T> and(final Specification<T> specification) {
        return where(specification);
    }

    @Deprecated
    public SpecificationBuilder<T> andWhere(final Specification<T> specification) {
        return where(specification);
    }

    public SpecificationBuilder<T> orWhere(final Specification<T> specification) {
        Objects.requireNonNull(specification, "Argument 'specification' cannot be null.");
        this.specification = this.specification == null ?
                Specification.where(specification) : this.specification.or(specification);
        return this;
    }

    public SpecificationBuilder<T> whereEquals(final SingularAttribute<T, ?> attribute,
                                               final Object value) {
        return where(specificationFactory.equal(attribute, value));
    }

    public SpecificationBuilder<T> whereNotEquals(final SingularAttribute<T, ?> attribute,
                                                  final Object value) {
        return where(specificationFactory.notEqual(attribute, value));
    }

    public SpecificationBuilder<T> whereLike(final SingularAttribute<T, String> attribute,
                                             final String value) {
        return where(specificationFactory.like(attribute, value));
    }

    public SpecificationBuilder<T> whereNotLike(final SingularAttribute<T, String> attribute,
                                                final String value) {
        return where(specificationFactory.notLike(attribute, value));
    }

    public SpecificationBuilder<T> whereEqualsOrLike(final SingularAttribute<T, String> attribute,
                                                     final String value) {
        return where(specificationFactory.equalOrLike(attribute, value));
    }

    public SpecificationBuilder<T> whereIsNull(final SingularAttribute<T, ?> attribute) {
        return where(specificationFactory.isNull(attribute));
    }

    public SpecificationBuilder<T> whereIsNotNull(final SingularAttribute<T, ?> attribute) {
        return where(specificationFactory.isNotNull(attribute));
    }

    public SpecificationBuilder<T> whereIsTrue(final SingularAttribute<T, Boolean> attribute) {
        return where(specificationFactory.isTrue(attribute));
    }

    public SpecificationBuilder<T> whereIsFalse(final SingularAttribute<T, Boolean> attribute) {
        return where(specificationFactory.isFalse(attribute));
    }

    public <V extends Comparable<? super V>> SpecificationBuilder<T> whereGreaterThan(final SingularAttribute<T, V> attribute,
                                                                                      final V value) {
        return where(specificationFactory.greaterThan(attribute, value));
    }

    public <V extends Comparable<? super V>>  SpecificationBuilder<T> whereGreaterThanOrEqualTo(final SingularAttribute<T, V> attribute,
                                                                                                final V value) {
        return where(specificationFactory.greaterThanOrEqualTo(attribute, value));
    }

    public <V extends Comparable<? super V>> SpecificationBuilder<T> whereLessThan(final SingularAttribute<T, V> attribute,
                                                                                   final V value) {
        return where(specificationFactory.lessThan(attribute, value));
    }

    public <V extends Comparable<? super V>> SpecificationBuilder<T> whereLessThanOrEqualTo(final SingularAttribute<T, V> attribute,
                                                                                            final V value) {
        return where(specificationFactory.lessThanOrEqualTo(attribute, value));
    }

    public <V extends Comparable<? super V>> SpecificationBuilder<T> whereBetween(final SingularAttribute<T, V> attribute,
                                                                                  final V firstValue,
                                                                                  final V secondValue) {
        return where(specificationFactory.between(attribute, firstValue, secondValue));
    }

    public SpecificationBuilder<T> whereIn(final SingularAttribute<T, ?> attribute,
                                           final Collection<?> collection) {
        return where(specificationFactory.in(attribute, collection));
    }

    public SpecificationBuilder<T> whereIn(final SingularAttribute<T, ?> attribute,
                                           final Object... values) {
        return where(specificationFactory.in(attribute, values));
    }

    public SpecificationBuilder<T> withFetch(final SingularAttribute<T, ?> attribute) {
        return and(specificationFactory.fetch(attribute));
    }

    public SpecificationBuilder<T> withFetch(final PluralAttribute<T, ?, ?> attribute) {
        return and(specificationFactory.fetch(attribute));
    }
}
