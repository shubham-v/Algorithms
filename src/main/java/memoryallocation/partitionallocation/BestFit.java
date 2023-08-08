package memoryallocation.partitionallocation;

import java.util.ArrayList;
import java.util.Queue;

public class BestFit {
    public ArrayList<MemoryBlock> fit(final ArrayList<MemoryBlock> memoryBlocks,
                                      final Queue<Process> processes) {
        final MemoryBlock newMemoryBlock = new MemoryBlock();
        int memoryBlocksCount = memoryBlocks.size();
        int minBlockSizeIndex = -1;
        long minBlockSize = -1;
        while (!processes.isEmpty()) {
            Process process = processes.peek();
            for (int i = 0; i < memoryBlocksCount; i++) {
                MemoryBlock memoryBlock = memoryBlocks.get(i);
                if (memoryBlock.getSize() >= process.getSize()
                        && memoryBlock.getSize() < minBlockSize) {
                    minBlockSize = memoryBlock.getSize();
                    minBlockSizeIndex = i;
                }
            }

            if (minBlockSizeIndex != -1) {
                memoryBlocks.get(minBlockSizeIndex).push(process);
            } else {
                newMemoryBlock.incrementSize(process.getSize());
                newMemoryBlock.push(process);
            }
            processes.poll();
        }
        if (!newMemoryBlock.isEmpty()) {
            memoryBlocks.add(newMemoryBlock);
        }
        return memoryBlocks;
    }
}
