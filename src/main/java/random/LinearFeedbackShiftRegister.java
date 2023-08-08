package random;

public class LinearFeedbackShiftRegister {

    private int state = (1 << 27) | 1;

    int getNext() {
        for (int i = 1; i < 20; i++) {
            System.out.print((state & 1));
            var newBit = (state ^ (state >> 1)) & 1;
            state = (state >> 1) | (newBit << 3);
//            System.out.println(Integer.toString(state, 2));
        }
        return 0;
    }

    int get() {
        while (true) {
            System.out.print((state & 1));
            var newBit = (state ^ (state >> 1) ^ (state >> 2) ^ (state >> 7)) & 1;
            state = (state >> 1) | (newBit << 127);
        }
    }

    public static void main(String[] args) {
        new LinearFeedbackShiftRegister()
                .get();
    }

}
