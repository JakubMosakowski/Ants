
class Max {
    static int MAX_X;
    static int MAX_Y;
    static int SIZE;
    static int MAX_ANTS_USER;
    static int DELAY_USER;
    static int MOVES;
    static int LEAVES_USER;
    static int FIELDS;

    static void setMax(int X, int Y, int ants, int moves, int size, int leaves, int delay) {
        MAX_X = X;
        MAX_Y = Y;
        MOVES = moves;
        SIZE = size;
        LEAVES_USER = leaves;
        FIELDS = SIZE * SIZE;
        DELAY_USER = delay;
        MAX_ANTS_USER = ants;
    }

    static void setDelayUser(int delay) {
        DELAY_USER = delay;
    }

    static void setMaxAntsUser(int maxAnts) {
        MAX_ANTS_USER = maxAnts;
    }

    static void setLeavesUser(int leavesUser) {
        LEAVES_USER = leavesUser;
    }

}
