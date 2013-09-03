package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isOneOf;

@RunWith(JukitoRunner.class)
public class ShoppingListTest {
    ShoppingList shoppingList;

    @Inject
    ShoppingListItemFactory itemFactory;

    @Inject
    CompositeMeasurementFactory measurementFactory;

    public static class Module extends JukitoModule {
        protected void configureTest() {
            install(new FactoryModuleBuilder().build(SingleMeasurementFactory.class));
            install(new FactoryModuleBuilder().build(CompositeMeasurementFactory.class));
            install(new FactoryModuleBuilder().build(ShoppingListItemFactory.class));
        }
    }

    @Before
    public void setUp() {
        shoppingList = new ShoppingList();
    }

    @Test
    public void hasMeasurementAndProductInTextRepresentation() {
        CompositeMeasurement measurement = measurementFactory.create(1, "cup");
        ShoppingListItem item = itemFactory.create(measurement, "sugar");

        shoppingList.add(item);

        assertThat(shoppingList.getText(), is("1 cup sugar"));
    }

    @Test
    public void addsItemWhenProductIsDifferent() {
        CompositeMeasurement oneCup = measurementFactory.create(1, "cup");
        ShoppingListItem cupOfSugar = itemFactory.create(oneCup, "sugar");
        CompositeMeasurement twoTsp = measurementFactory.create(2, "tsp");
        ShoppingListItem tspOfFlour = itemFactory.create(twoTsp, "flour");

        shoppingList.add(cupOfSugar);
        shoppingList.add(tspOfFlour);

        assertThat(shoppingList.getText(),
                isOneOf("1 cup sugar\n2 tsp flour",
                        "2 tsp flour\n1 cup sugar"));
    }

    @Test
    public void addsAndCoalescesItemWhenProductsAreTheSame() {
        String product = "sugar";
        CompositeMeasurement oneCup = measurementFactory.create(1, "cup");
        ShoppingListItem cupOfSugar = itemFactory.create(oneCup, product);
        CompositeMeasurement twoCups = measurementFactory.create(2, "cup");
        ShoppingListItem twoCupsSugar = itemFactory.create(twoCups, product);

        shoppingList.add(cupOfSugar);
        shoppingList.add(twoCupsSugar);

        assertThat(shoppingList.getText(), is("3 cup sugar"));
    }
}
