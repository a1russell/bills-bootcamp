package minandmax;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;
import minandmax.comparison.Comparison;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StringMinAndMaxFinder {
    private final Collection<String> collection;
    private final Comparison<String> lessThan;
    private final Comparison<String> greatherThan;
    
    @Inject
    public StringMinAndMaxFinder(@Named("Less Than") Comparison<String> lessThan,
                                 @Named("Greater Than") Comparison<String> greatherThan,
                                 @Assisted String... elements) {
        this.collection = new ArrayList<String>(elements.length);
        Collections.addAll(this.collection, elements);
        this.lessThan = lessThan;
        this.greatherThan = greatherThan;
    }

    public String compare(Comparison<String> comparison) {
        String champion = null;
        for (String challenger : collection) {
            if (champion == null || comparison.apply(challenger, champion)) {
                champion = challenger;
            }
        }
        return champion;
    }

    public String min() {
        return compare(lessThan);
    }

    public String max() {
        return compare(greatherThan);
    }
}
