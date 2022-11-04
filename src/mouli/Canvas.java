package mouli;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Canvas {
    private final int width;
    private final int height;
    private final char[][] cnv;
    private final char marker = 'x';

    public Canvas(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }

        this.width = width;
        this.height = height;
        cnv = new char[height][width];

        IntStream.range(0, height).forEach(i -> Arrays.fill(cnv[i], ' '));
    }

    private void drawHorizontalLine(int x1, int x2, int y) {
        int begin = Math.min(x1, x2);
        int end = Math.max(x1, x2);
        IntStream.range(begin, end + 1).forEach(i -> cnv[y][i] = marker);
    }

    private void drawVerticalLine(int y1, int y2, int x) {
        int begin = Math.min(y1, y2);
        int end = Math.max(y1, y2);
        IntStream.range(begin, end + 1).forEach(i -> cnv[i][x] = marker);
    }

    private void drawRectangle(int x1, int y1, int x2, int y2) {
        IntStream.of(y1, y2).forEach(i -> drawHorizontalLine(x1, x2, i));
        IntStream.of(x1, x2).forEach(i -> drawVerticalLine(y1, y2, i));
    }

    public void rect(int x1, int y1, int x2, int y2) {
        if ((x1 < 0 || x1 >= width) ||
            (x2 < 0 || x2 >= width) ||
            (y1 < 0 || y1 >= height) ||
            (y2 < 0 || y2 >= height)) {

            throw new IllegalArgumentException();
        }

        drawRectangle(x1, y1, x2, y2);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        int dX = x2 - x1;
        int dY = y2 - y1;

        double distance = Math.hypot(dX, dY);
        double xShiftPerStep = dX / distance;
        double yShiftPerStep = dY / distance;

        IntStream.iterate(0, i -> i <= distance, i -> i + 1).forEach(i -> {
            int x = x1 + (int) (i * xShiftPerStep);
            int y = y1 + (int) (i * yShiftPerStep);
            cnv[y][x] = marker;
        });
    }

    public void line(int x1, int y1, int x2, int y2) {
        drawLine(x1, y1, x2, y2);
    }


    private void drawEllipse(int x, int y, int rx, int ry, int arcAngle, int rotatingAngle) {

        double sinb = Math.sin(Math.toRadians(rotatingAngle));
        double cosb = Math.cos(Math.toRadians(rotatingAngle));

        DoubleStream.iterate(0, i -> i < arcAngle, i -> i + 0.1).forEach(i -> {
            double sina = Math.sin(Math.toRadians(i));
            double cosa = Math.cos(Math.toRadians(i));
            int cx = (int) Math.round(x + rx * cosa * cosb - ry * sina * sinb);
            int cy = (int) Math.round(y + rx * cosa * sinb + ry * sina * cosb);
            safeDraw(cx, cy);
        });
    }

    private void safeDraw(int x, int y) {
        if ((x < 0 || x >= width) ||
            (y < 0 || y >= height))
            throw new IllegalArgumentException();

        cnv[x][y] = marker;
    }

    public void ellipse(int x, int y, int rx, int ry, int arcAngle, int angle) {
        drawEllipse(x, y, rx, ry, arcAngle, angle);
    }


    public void drawCanvas() {
        var res = new StringBuilder();
        char horizontalBorder = '-';
        char verticalBorder = '|';

        String border = String.valueOf(horizontalBorder).repeat(Math.max(0, width + 2));
        res.append(border)
                .append('\n');

        for (int i = 0; i < height; i++) {
            res.append(verticalBorder);

            for (int j = 0; j < width; j++) {
                res.append(cnv[i][j]);
            }

            res.append(verticalBorder)
                    .append('\n');
        }

        res.append(border);
        System.out.println(res);
    }

}
