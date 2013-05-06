package minandmax;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MinAndMaxFinderTest {
    @Test
    public void findsMinimumIntegerInCollectionWhenItIsTheFirstElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = new MinAndMaxFinder<Integer>(1, 2, 3);
        assertThat(minAndMaxFinder.min(), is(1));
    }

    @Test
    public void findsMinimumIntegerInCollectionWhenItIsTheLastElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = new MinAndMaxFinder<Integer>(3, 2, 1);
        assertThat(minAndMaxFinder.min(), is(1));
    }

    @Test
    public void findsMaximumIntegerInCollectionWhenItIsTheFirstElement() throws Exception {
        MinAndMaxFinder<Integer> minAndMaxFinder = new MinAndMaxFinder<Integer>(3, 2, 1);
        assertThat(minAndMaxFinder.max(), is(3));
    }
}
