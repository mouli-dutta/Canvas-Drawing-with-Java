package mouli;

public class Main {

    public static void main(String[] args) {
        try{
            drawAmongUs();
            drawHome();
            drawFlower();

        } catch(IllegalArgumentException iae) {
            System.out.println("Invalid canvas width and height");
        }
    }


    private static void drawAmongUs() {
        Canvas cnv = new Canvas(30,20);
        int[][] lineCoords = {
                {2, 3, 5, 10},
                {5, 1, 21, 1}, {5, 1, 5, 16}, {5, 16, 11, 16},
                {11, 12, 11, 16}, {11, 12, 16, 12},
                {16, 12, 16, 16}, {16, 16, 21, 16},
                {22, 1, 22, 4},
                {18, 4, 26, 8},
                {22, 9, 22, 16}
        };

        for (int[] coords : lineCoords) {
            cnv.rect(coords[0], coords[1], coords[2], coords[3]);
        }

        cnv.drawCanvas();
    }

    private static void drawHome() {

        Canvas cnv = new Canvas(32, 20);

        var lineCoords = new int[][] {
                {8, 4, 20, 4}, {8, 4, 2, 10}, {2, 10, 14, 10},
                {20, 4, 14, 10}, {20, 4, 26, 10},
                {14, 16, 26, 16},
                {26, 10, 26, 16}
        };

        var rectCords = new int[][] {
                {2, 10, 14, 16}, {6, 12, 10, 14},
                {18, 11, 22, 16}
        };

        for (int[] coords : lineCoords)
            cnv.line(coords[0], coords[1], coords[2], coords[3]);

        for (int[] coords : rectCords) {
            cnv.rect(coords[0], coords[1], coords[2], coords[3]);
        }

        cnv.drawCanvas();
    }

    private static void drawFlower() {
        var cnv = new Canvas(36, 45);

        var petalCoords = new int[][] {

                {14, 22, 3, 8, 180, 0},
                {14, 10, 3, 8, 180, 180},
                {11, 16, 6, 6, 180, 90},
                {17, 16, 6, 6, 180, 270},
                {14, 16, 2, 4, 360, 0},
                {26, 23, 9, 2, 180, 0},

                {10, 15, 2, 2, 180, 0},
                {18, 17, 2, 2, 180, 180},

                {33, 22, 2, 8, 100, 140},
                {23, 30, 2, 5, 100, -140}
        };

        for (int[] coords : petalCoords)
            cnv.ellipse(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);

        var potCoords = new int[][] {

                {16, 35, 31, 35},
                {16, 35, 21, 40},
                {21, 40, 26, 40},
                {26, 40, 31, 35}
        };

        for (int[] coords : potCoords)
            cnv.line(coords[0], coords[1], coords[2], coords[3]);

        cnv.drawCanvas();

    }

}
