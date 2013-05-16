package minandmax;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import minandmax.comparison.Comparison;
import minandmax.comparison.IntGreaterThan;
import minandmax.comparison.IntGreaterThanBelowLimitFactory;
import minandmax.comparison.IntLessThan;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JukitoRunner.class)
public class IntMinAndMaxFinderTest {
    @Inject
    IntMinAndMaxFinderFactory intMinAndMaxFinderFactory;

    public static class Module extends JukitoModule {
        protected void configureTest() {
            bind(new TypeLiteral<Comparison<Integer>>() {})
                .annotatedWith(Names.named("Less Than"))
                .to(IntLessThan.class);
            bind(new TypeLiteral<Comparison<Integer>>() {})
                .annotatedWith(Names.named("Greater Than"))
                .to(IntGreaterThan.class);
            install(new FactoryModuleBuilder().build(IntGreaterThanBelowLimitFactory.class));
            install(new FactoryModuleBuilder().build(IntMinAndMaxFinderFactory.class));
        }
    }

    @Test
    public void findsMinimumIntegerInCollectionWhenItIsTheOnlyElement() throws Exception {
        IntMinAndMaxFinder minAndMaxFinder = intMinAndMaxFinderFactory.create(1);
        assertThat(minAndMaxFinder.min(), is(1));
    }

    @Test
    public void findsMinimumIntegerInCollectionWhenItIsTheLastElement() throws Exception {
        IntMinAndMaxFinder minAndMaxFinder = intMinAndMaxFinderFactory.create(3, 2, 1);
        assertThat(minAndMaxFinder.min(), is(1));
    }

    @Test
    public void findsMaximumIntegerInCollectionWhenItIsTheOnlyElement() throws Exception {
        IntMinAndMaxFinder minAndMaxFinder = intMinAndMaxFinderFactory.create(3);
        assertThat(minAndMaxFinder.max(), is(3));
    }

    @Test
    public void findsMaximumIntegerInCollectionWhenItIsTheLastElement() throws Exception {
        IntMinAndMaxFinder minAndMaxFinder = intMinAndMaxFinderFactory.create(1, 2, 3);
        assertThat(minAndMaxFinder.max(), is(3));
    }

    @Test
    public void findsMaximumIntegerBelowLimitInCollectionWhenItIsTheOnlyElement() throws Exception {
        IntMinAndMaxFinder minAndMaxFinder = intMinAndMaxFinderFactory.create(3);
        assertThat(minAndMaxFinder.maxBelow(4), is(3));
    }

    @Test
    public void findsMaximumIntegerBelowLimitInCollectionWhenItNotTheFirstElement() throws Exception {
        IntMinAndMaxFinder minAndMaxFinder = intMinAndMaxFinderFactory.create(1, 2, 3);
        assertThat(minAndMaxFinder.maxBelow(3), is(2));
    }
}
