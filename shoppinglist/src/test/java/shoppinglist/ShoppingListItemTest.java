package shoppinglist;

import com.google.inject.Inject;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JukitoRunner.class)
public class ShoppingListItemTest {
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

    @Test
    public void hasMeasurementAndProductInTextRepresentation() {
        CompositeMeasurement measurement = measurementFactory.create(1, Unit.CUP);
        ShoppingListItem item = itemFactory.create(measurement, "sugar");
        assertThat(item.getText(), is("1 cup sugar"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddItemOfAnotherProduct() {
        CompositeMeasurement measurement = measurementFactory.create(1, Unit.CUP);
        ShoppingListItem baseItem = itemFactory.create(measurement, "sugar");
        ShoppingListItem itemToAdd = itemFactory.create(measurement, "flour");
        baseItem.add(itemToAdd);
    }

    @Test
    public void addsToMeasurement() {
        String product = "sugar";
        CompositeMeasurement baseMeasurement = measurementFactory.create(1, Unit.CUP);
        ShoppingListItem baseItem = itemFactory.create(baseMeasurement, product);
        CompositeMeasurement measurementToAdd = measurementFactory.create(2, Unit.CUP);
        ShoppingListItem itemToAdd = itemFactory.create(measurementToAdd, product);

        baseItem.add(itemToAdd);

        assertThat(baseItem.getText(), is("3 cup sugar"));
    }
}
