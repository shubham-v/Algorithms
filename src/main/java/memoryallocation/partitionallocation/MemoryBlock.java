package memoryallocation.partitionallocation;

import java.util.LinkedList;
import java.util.Queue;

public class MemoryBlock {

    private long size;
    private int processCount;
    private final Queue<Process> occupiedSpaces = new LinkedList<>();

    public boolean push(final Process process) {
        if (process.getSize() <= this.size) {
            this.occupiedSpaces.offer(process);
            this.size -= process.getSize();
            return true;
        }
        return false;
    }

    public Process pop() {
        if (!this.occupiedSpaces.isEmpty()) {
            Process process = this.occupiedSpaces.poll();
            this.size += process.getSize();
            return process;
        }
        return null;
    }

    public void incrementSize(long size) {
        this.size += size;
    }

    public boolean isEmpty() {
        return this.occupiedSpaces.isEmpty();
    }

    public long getSize() {
        return size;
    }
}
