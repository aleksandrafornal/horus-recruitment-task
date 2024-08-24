package org.example;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .flatMap(block -> block instanceof CompositeBlock
                        ? ((CompositeBlock) block).getBlocks().stream()
                        : Stream.of(block))
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .flatMap(block -> block instanceof CompositeBlock
                        ? ((CompositeBlock) block).getBlocks().stream()
                        : Stream.of(block))
                .filter(block -> block.getMaterial().equals(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return blocks.stream()
                .mapToInt(
                        block -> block instanceof CompositeBlock
                                ? ((CompositeBlock) block).getBlocks().size()
                                : 1
                )
                .sum();
    }
}