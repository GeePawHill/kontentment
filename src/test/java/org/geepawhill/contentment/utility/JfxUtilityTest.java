package org.geepawhill.contentment.utility;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class JfxUtilityTest {

    ArrayList<Node> calls;
    private Group root;
    private Group parentWithChildren;
    private Group parentWithoutChildren;
    private Node leafOffRoot;
    private Node leaf2;
    private Node leaf3;

    @BeforeEach
    public void before() {
        calls = new ArrayList<>();
        root = new Group();
        parentWithChildren = new Group();
        parentWithoutChildren = new Group();
        leafOffRoot = new Text();
        root.getChildren().addAll(parentWithChildren, parentWithoutChildren, leafOffRoot);
        leaf2 = new Text();
        leaf3 = new Text();
        parentWithChildren.getChildren().addAll(leaf2, leaf3);
    }

    public boolean process(Node node) {
        calls.add(node);
        return true;
    }

    @Test
    public void descendant() {
        JfxUtility.INSTANCE.forEachDescendant(root, this::process);
        assertThat(calls).containsExactly(root, parentWithChildren, leaf2, leaf3, parentWithoutChildren, leafOffRoot);
    }

}
