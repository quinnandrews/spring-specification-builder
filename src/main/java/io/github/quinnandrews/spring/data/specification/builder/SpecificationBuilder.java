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
    
    private Specification<T> specification;

    public SpecificationBuilder() {
        // no-op
    }

    public static <T> SpecificationBuilder<T> from(final Class<T> root) {
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

    public SpecificationBuilder<T> or(final Specification<T> specification) {
        Objects.requireNonNull(specification, "Argument 'specification' cannot be null.");
        this.specification = this.specification == null ?
                Specification.where(specification) : this.specification.or(specification);
        return this;
    }

    public SpecificationBuilder<T> with() {
        return this;
    }

    public SpecificationBuilder<T> isEqualTo(final SingularAttribute<T, ?> attribute,
                                             final Object value) {
        return where(SpecificationFactory.isEqualTo(attribute, value));
    }

    public SpecificationBuilder<T> isNotEqualTo(final SingularAttribute<T, ?> attribute,
                                                final Object value) {
        return where(SpecificationFactory.isNotEqualTo(attribute, value));
    }

    public SpecificationBuilder<T> isLike(final SingularAttribute<T, String> attribute,
                                          final String value) {
        return where(SpecificationFactory.isLike(attribute, value));
    }

    public SpecificationBuilder<T> isNotLike(final SingularAttribute<T, String> attribute,
                                             final String value) {
        return where(SpecificationFactory.isNotLike(attribute, value));
    }

    public SpecificationBuilder<T> isEqualToOrLike(final SingularAttribute<T, String> attribute,
                                                   final String value) {
        return where(SpecificationFactory.isEqualToOrLike(attribute, value));
    }

    public SpecificationBuilder<T> isNull(final SingularAttribute<T, ?> attribute) {
        return where(SpecificationFactory.isNull(attribute));
    }

    public SpecificationBuilder<T> isNotNull(final SingularAttribute<T, ?> attribute) {
        return where(SpecificationFactory.isNotNull(attribute));
    }

    public SpecificationBuilder<T> isTrue(final SingularAttribute<T, Boolean> attribute) {
        return where(SpecificationFactory.isTrue(attribute));
    }

    public SpecificationBuilder<T> isFalse(final SingularAttribute<T, Boolean> attribute) {
        return where(SpecificationFactory.isFalse(attribute));
    }

    public <V extends Comparable<? super V>> SpecificationBuilder<T> isGreaterThan(final SingularAttribute<T, V> attribute,
                                                                                   final V value) {
        return where(SpecificationFactory.isGreaterThan(attribute, value));
    }

    public <V extends Comparable<? super V>>  SpecificationBuilder<T> isGreaterThanOrEqualTo(final SingularAttribute<T, V> attribute,
                                                                                             final V value) {
        return where(SpecificationFactory.isGreaterThanOrEqualTo(attribute, value));
    }

    public <V extends Comparable<? super V>> SpecificationBuilder<T> isLessThan(final SingularAttribute<T, V> attribute,
                                                                                final V value) {
        return where(SpecificationFactory.isLessThan(attribute, value));
    }

    public <V extends Comparable<? super V>> SpecificationBuilder<T> isLessThanOrEqualTo(final SingularAttribute<T, V> attribute,
                                                                                         final V value) {
        return where(SpecificationFactory.isLessThanOrEqualTo(attribute, value));
    }

    public <V extends Comparable<? super V>> SpecificationBuilder<T> isBetween(final SingularAttribute<T, V> attribute,
                                                                               final V firstValue,
                                                                               final V secondValue) {
        return where(SpecificationFactory.isBetween(attribute, firstValue, secondValue));
    }

    public SpecificationBuilder<T> isIn(final SingularAttribute<T, ?> attribute,
                                        final Collection<?> collection) {
        return where(SpecificationFactory.isIn(attribute, collection));
    }

    public SpecificationBuilder<T> isIn(final SingularAttribute<T, ?> attribute,
                                        final Object... values) {
        return where(SpecificationFactory.isIn(attribute, values));
    }

    public SpecificationBuilder<T> fetchOf(final SingularAttribute<T, ?> attribute) {
        return and(SpecificationFactory.fetchOf(attribute));
    }

    public SpecificationBuilder<T> fetchOf(final PluralAttribute<T, ?, ?> attribute) {
        return and(SpecificationFactory.fetchOf(attribute));
    }
}
