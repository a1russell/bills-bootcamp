package minandmax;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import minandmax.comparison.Comparison;
import minandmax.comparison.GreaterThan;
import minandmax.comparison.GreaterThanBelowLimitFactory;
import minandmax.comparison.LessThan;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JukitoRunner.class)
public class MinAndMaxFinderTest {
    @Inject
    MinAndMaxFinderFactory<Integer> minAndMaxFinderIntegerFactory;

    @Inject
    MinAndMaxFinderFactory<String> minAndMaxFinderStringFactory;

    public static class Module extends JukitoModule {
        protected void configureTest() {
            bind(new TypeLiteral<Comparison<Integer>>() {})
                .annotatedWith(Names.named("Less Than"))
                .to(new TypeLiteral<LessThan<Integer>>() {});
            bind(new TypeLiteral<Comparison<Integer>>() {})
                .annotatedWith(Names.named("Greater Than"))
                .to(new TypeLiteral<GreaterThan<Integer>>() {});
            install(new FactoryModuleBuilder().build(new TypeLiteral<GreaterThanBelowLimitFactory<Integer>>() {}));
            install(new FactoryModuleBuilder().build(new TypeLiteral<MinAndMaxFinderFactory<Integer>>() {}));
            
            bind(new TypeLiteral<Comparison<String>>() {})
                .annotatedWith(Names.named("Less Than"))
                .to(new TypeLiteral<LessThan<String>>() {});
            bind(new TypeLiteral<Comparison<String>>() {})
                .annotatedWith(Names.named("Greater Than"))
                .to(new TypeLiteral<GreaterThan<String>>() {});
            install(new FactoryModuleBuilder().build(new TypeLiteral<GreaterThanBelowLimitFactory<String>>() {}));
            install(new FactoryModuleBuilder().build(new TypeLiteral<MinAndMaxFinderFactory<String>>() {}));
        }
    }

    @Test
    public void findsMinimumIntegerInCollectionWhenItIsTheOnlyElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = minAndMaxFinderIntegerFactory.create(1);
        assertThat(minAndMaxFinder.min(), is(1));
    }

    @Test
    public void findsMinimumIntegerInCollectionWhenItIsTheLastElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = minAndMaxFinderIntegerFactory.create(3, 2, 1);
        assertThat(minAndMaxFinder.min(), is(1));
    }

    @Test
    public void findsMaximumIntegerInCollectionWhenItIsTheOnlyElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = minAndMaxFinderIntegerFactory.create(3);
        assertThat(minAndMaxFinder.max(), is(3));
    }

    @Test
    public void findsMaximumIntegerInCollectionWhenItIsTheLastElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = minAndMaxFinderIntegerFactory.create(1, 2, 3);
        assertThat(minAndMaxFinder.max(), is(3));
    }

    @Test
    public void findsMinimumWhenCollectionIsOfStrings() throws Exception {
        MinAndMaxFinder<String> minAndMaxFinder = minAndMaxFinderStringFactory.create("A", "B", "C");
        assertThat(minAndMaxFinder.min(), is("A"));
    }

    @Test
    public void findsMaximumWhenCollectionIsOfStrings() throws Exception {
        MinAndMaxFinder<String> minAndMaxFinder = minAndMaxFinderStringFactory.create("A", "B", "C");
        assertThat(minAndMaxFinder.max(), is("C"));
    }

    @Test
    public void findsMaximumIntegerBelowLimitInCollectionWhenItIsTheOnlyElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = minAndMaxFinderIntegerFactory.create(3);
        assertThat(minAndMaxFinder.maxBelow(4), is(3));
    }

    @Test
    public void findsMaximumIntegerBelowLimitInCollectionWhenItNotTheFirstElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = minAndMaxFinderIntegerFactory.create(1, 2, 3);
        assertThat(minAndMaxFinder.maxBelow(3), is(2));
    }
}
