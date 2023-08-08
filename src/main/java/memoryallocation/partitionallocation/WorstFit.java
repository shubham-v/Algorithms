package memoryallocation.partitionallocation;

import java.util.ArrayList;
import java.util.Queue;

public class WorstFit {

    public ArrayList<MemoryBlock> fit(final ArrayList<MemoryBlock> memoryBlocks, final Queue<Process> processes) {
        final MemoryBlock newMemoryBlock = new MemoryBlock();
        int memoryBlocksCount = memoryBlocks.size();
        int maxBlockSizeIndex = -1;
        long maxBlockSize = -1;
        while (!processes.isEmpty()) {
            Process process = processes.peek();
            for (int i = 0; i < memoryBlocksCount; i++) {
                MemoryBlock memoryBlock = memoryBlocks.get(i);
                if (memoryBlock.getSize() >= process.getSize()
                        && memoryBlock.getSize() > maxBlockSize) {
                    maxBlockSize = memoryBlock.getSize();
                    maxBlockSizeIndex = i;
                }
            }

            if (maxBlockSizeIndex != -1) {
                memoryBlocks.get(maxBlockSizeIndex).push(process);
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
