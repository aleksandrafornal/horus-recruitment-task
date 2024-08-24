package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class WallTest {

    @Test
    void testFindBlockByColorWithoutCompositeBlocks() {
        //Given
        Wall wall = new Wall(generateBlocks(10));

        //When
        Optional<Block> block = wall.findBlockByColor("testColor1");

        //Then
        Assertions.assertTrue(block.isPresent(), "Block should be found");
        Assertions.assertEquals("testColor1", block.get().getColor(), "Color of found block should be testColor1");
    }

    @Test
    void testFindBlockByColorWithCompositeBlocks() {
        //Given
        Wall wall = new Wall(generateBlocksWithCompositeBlocks(5,5, 2));

        //When
        Optional<Block> block = wall.findBlockByColor("testColor1");

        //Then
        Assertions.assertTrue(block.isPresent(), "Block should be found");
        Assertions.assertEquals("testColor1", block.get().getColor(), "Color of found block should be testColor1");
    }

    @Test
    void findBlocksByMaterialWithoutCompositeBlocks() {
        //Given
        Wall wall = new Wall(generateBlocks(5));

        //When
        List<Block> blocks = wall.findBlocksByMaterial("testMaterial");

        //Then
        Assertions.assertFalse(blocks.isEmpty(), "List shouldn't be empty");
        Assertions.assertEquals(5, blocks.size(), "There should be 5 blocks in the list");

    }

    @Test
    void findBlocksByMaterialWithCompositeBlocks() {
        //Given
        Wall wall = new Wall(generateBlocksWithCompositeBlocks(5, 5, 2));

        //When
        List<Block> blocks = wall.findBlocksByMaterial("testMaterial");

        //Then
        Assertions.assertFalse(blocks.isEmpty(), "List shouldn't be empty");
        Assertions.assertEquals(15, blocks.size(), "There should be 15 blocks in the list");
    }

    @Test
    void countWithoutCompositeBlocks() {
        //Given
        Wall wall = new Wall(generateBlocks(10));

        //When
        int blocksCount = wall.count();

        //Then
        Assertions.assertEquals(10, blocksCount, "There should be 10 blocks in the wall");
    }

    @Test
    void countWithCompositeBlocks() {
        //Given
        Wall wall = new Wall(generateBlocksWithCompositeBlocks(5, 5, 2));

        //When
        int blocksCount = wall.count();

        //Then
        Assertions.assertEquals(15, blocksCount, "There should be 15 blocks in the wall");
    }

    private List<Block> generateBlocks(int numberOfBlocks) {
        List<Block> testBlocks = new ArrayList<>();
        for (int i = 0; i < numberOfBlocks; i++) {
            testBlocks.add(createBlock("testColor" + i));
        }
        return testBlocks;
    }

    private List<Block> generateBlocksWithCompositeBlocks(int numberOfBlocks, int numberOfCompositeBlocks, int numberOfBlocksInsideComposite) {
        List<Block> testBlocks = new ArrayList<>();
        for (int i = 0; i < numberOfBlocks; i++) {
            testBlocks.add(createBlock("testColor" + i));
        }

        for (int i = 0; i < numberOfCompositeBlocks; i++) {
            testBlocks.add(createBlockWithCompositeBlocks("testColor" + i, numberOfBlocksInsideComposite));
        }
        return testBlocks;
    }

    private static Block createBlock(String testColor) {
        return new Block() {
            @Override
            public String getColor() {
                return testColor;
            }

            @Override
            public String getMaterial() {
                return "testMaterial";
            }
        };
    }

    private static CompositeBlock createBlockWithCompositeBlocks(String testColor, int numberOfBlocks) {
        return new CompositeBlock() {

            @Override
            public String getColor() {
                return testColor;
            }

            @Override
            public String getMaterial() {
                return "";
            }

            @Override
            public List<Block> getBlocks() {
                List<Block> testBlocks = new ArrayList<>();
                for (int i = 0; i < numberOfBlocks; i++) {
                    testBlocks.add(createBlock(testColor));
                }
                return testBlocks;
            }
        };
    }
}