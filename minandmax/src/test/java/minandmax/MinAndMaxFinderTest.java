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
}
