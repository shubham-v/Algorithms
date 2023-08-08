package memoryallocation.partitionallocation;

import java.util.ArrayList;
import java.util.Queue;

public class FirstFit {
    public ArrayList<MemoryBlock> fit(final ArrayList<MemoryBlock> memoryBlocks, final Queue<Process> processes) {
        final MemoryBlock newMemoryBlock = new MemoryBlock();
        while (!processes.isEmpty()) {
            final Process process = processes.peek();
            boolean isAllocatedMemory = false;
            for (final MemoryBlock memoryBlock : memoryBlocks) {
                if (isAllocatedMemory = memoryBlock.push(process)) {
                    break;
                }
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
