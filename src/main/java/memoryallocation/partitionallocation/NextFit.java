package memoryallocation.partitionallocation;

import java.util.ArrayList;
import java.util.Queue;

public class NextFit {
    public ArrayList<MemoryBlock> fit(final ArrayList<MemoryBlock> memoryBlocks, final Queue<Process> processes) {
        final MemoryBlock newMemoryBlock = new MemoryBlock();
        int blockIndex = 0, memoryBlocksCount = memoryBlocks.size();
        while (!processes.isEmpty()) {
            final Process process = processes.peek();
            boolean isAllocatedMemory = false;
            int triedToFitBlockMemoryCount = 0;
            while (blockIndex < memoryBlocksCount && triedToFitBlockMemoryCount < memoryBlocksCount) {
                final MemoryBlock memoryBlock = memoryBlocks.get(blockIndex);
                if (isAllocatedMemory = memoryBlock.push(process)) {
                    break;
                }
                triedToFitBlockMemoryCount++;
                blockIndex++;
                blockIndex %= memoryBlocksCount;
            }

            if (!isAllocatedMemory) {
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
