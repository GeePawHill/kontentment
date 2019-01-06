package org.geepawhill.contentment.fragments;

import javafx.scene.text.Text;
import org.geepawhill.contentment.core.Context;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WipeTest {
    private Context context = new Context();

    @Test
    public void wipes() {
        Wipe wipe = new Wipe();
        context.getCanvas().getChildren().add(new Text());
        wipe.prepare(context);
        assertThat(wipe.interpolate(context, 1d)).isFalse();
        assertThat(context.getCanvas().getChildren().size()).isEqualTo(0);
    }
}
