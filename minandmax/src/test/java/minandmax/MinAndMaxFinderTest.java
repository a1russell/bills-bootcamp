package minandmax;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MinAndMaxFinderTest {
    @Test
    public void findsMinimumIntegerInCollectionWhenItIsTheOnlyElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = new MinAndMaxFinder<Integer>(1);
        assertThat(minAndMaxFinder.min(), is(1));
    }

    @Test
    public void findsMinimumIntegerInCollectionWhenItIsTheLastElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = new MinAndMaxFinder<Integer>(3, 2, 1);
        assertThat(minAndMaxFinder.min(), is(1));
    }

    @Test
    public void findsMaximumIntegerInCollectionWhenItIsTheOnlyElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = new MinAndMaxFinder<Integer>(3);
        assertThat(minAndMaxFinder.max(), is(3));
    }

    @Test
    public void findsMaximumIntegerInCollectionWhenItIsTheLastElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = new MinAndMaxFinder<Integer>(1, 2, 3);
        assertThat(minAndMaxFinder.max(), is(3));
    }

    @Test
    public void findsMinimumWhenCollectionIsOfStrings() throws Exception {
        MinAndMaxFinder<String> minAndMaxFinder = new MinAndMaxFinder<String>("A", "B", "C");
        assertThat(minAndMaxFinder.min(), is("A"));
    }

    @Test
    public void findsMaximumWhenCollectionIsOfStrings() throws Exception {
        MinAndMaxFinder<String> minAndMaxFinder = new MinAndMaxFinder<String>("A", "B", "C");
        assertThat(minAndMaxFinder.max(), is("C"));
    }
}
