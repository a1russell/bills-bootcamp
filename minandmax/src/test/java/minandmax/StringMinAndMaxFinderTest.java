package minandmax;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import minandmax.comparison.Comparison;
import minandmax.comparison.StringGreaterThan;
import minandmax.comparison.StringLessThan;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JukitoRunner.class)
public class StringMinAndMaxFinderTest {
    @Inject
    StringMinAndMaxFinderFactory stringMinAndMaxFinderFactory;

    public static class Module extends JukitoModule {
        protected void configureTest() {
            bind(new TypeLiteral<Comparison<String>>() {})
                .annotatedWith(Names.named("Less Than"))
                .to(StringLessThan.class);
            bind(new TypeLiteral<Comparison<String>>() {})
                .annotatedWith(Names.named("Greater Than"))
                .to(StringGreaterThan.class);
            install(new FactoryModuleBuilder().build(StringMinAndMaxFinderFactory.class));
        }
    }

    @Test
    public void findsMinimumWhenCollectionIsOfStrings() throws Exception {
        StringMinAndMaxFinder minAndMaxFinder = stringMinAndMaxFinderFactory.create("A", "B", "C");
        assertThat(minAndMaxFinder.min(), is("A"));
    }

    @Test
    public void findsMaximumWhenCollectionIsOfStrings() throws Exception {
        StringMinAndMaxFinder minAndMaxFinder = stringMinAndMaxFinderFactory.create("A", "B", "C");
        assertThat(minAndMaxFinder.max(), is("C"));
    }
}
