package com.jpii.navalbattle.renderer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.util.*;

import com.jpii.dagen.Engine;
import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.game.GameComponent;

/**
 * The OmniMap (previously known as MiniZoomMap). Essential is map of the world and binoculars.
 * @author MKirkby
 *
 */
public class OmniMap {
    Engine eng;
    int width, height;
    Random r;
    BufferedImage buffer, map;
    public boolean entireWorldMode = true;
    public int px, py;
    /**
     * Constructs a new instance of OmniMap
     * @param eng The current engine that is being used in the game.
     * @param width The width of the OmniMap.
     * @param height The height of the OmniMap.
     */
    public OmniMap(Engine eng, int width, int height) {
        this.eng = eng;
        this.width = width;
        this.height = height;
        r = new Random(Constants.MAIN_SEED);
        buffer = new BufferedImage(width, height + 25, BufferedImage.TYPE_INT_RGB);
        map = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = map.getGraphics();
        int s = 3;
        int swa = Constants.WINDOW_WIDTH / width * 10;
        int sha = Constants.WINDOW_HEIGHT / height * 10;
        for (int xt = 0; xt < eng.getWidth(); xt += swa) {
            for (int zt = 0; zt < eng.getHeight(); zt += sha) {
                double y = eng.getPoint(xt, zt);
                int x = xt / swa;
                int z = zt / sha;
                boolean flag = y <= RenderConstants.GEN_WATER_HEIGHT;
                if (flag) {
                    Color waterSample = //Constants.randomise(Constants.GEN_WATER_COLOR, Constants.GEN_COLOR_DIFF,
                    //r,false);
                    RenderConstants.adjust(RenderConstants.randomise(RenderConstants.GEN_WATER_COLOR,
                    RenderConstants.GEN_COLOR_DIFF, r, false), 1 - (y / RenderConstants.GEN_WATER_HEIGHT), 50);
                    if (y >= RenderConstants.GEN_WATER_HEIGHT - 0.05) {
                        double t = RenderConstants.GEN_WATER_HEIGHT - y;
                        waterSample = Helper.Lerp(RenderConstants.GEN_SAND_COLOR, RenderConstants.GEN_SAND_COLOR2 /*waterSample*/ , t / 0.05);
                    }
                    g.setColor(waterSample);
                    g.fillRect(x * s, z * s, s + 1, s + 1);
                }
                if (y >= RenderConstants.GEN_WATER_HEIGHT - 0.01 && y <= RenderConstants.GEN_WATER_HEIGHT + 0.05 && r.nextInt(3) == 1) {
                    flag = false;
                }

                if (!flag) {
                    Color groundSample = RenderConstants.adjust(RenderConstants.randomise(RenderConstants.GEN_GRASS_COLOR,
                    RenderConstants.GEN_COLOR_DIFF, r, false), y, 50);

                    if (y <= RenderConstants.GEN_WATER_HEIGHT + 0.1) {
                        double t = y - RenderConstants.GEN_WATER_HEIGHT;
                        groundSample = Helper.Lerp(RenderConstants.GEN_SAND_COLOR, groundSample, t / 0.1);
                    }
                    if (y >= RenderConstants.GEN_MOUNTAIN_HEIGHT) {
                        double t = y - RenderConstants.GEN_MOUNTAIN_HEIGHT;
                        groundSample = Helper.Lerp(groundSample, RenderConstants.GEN_MOUNTAIN_COLOR,
                        t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT));
                        groundSample = RenderConstants.adjust(groundSample, t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT), 30);
                    }

                    g.setColor(groundSample);
                    g.fillRect(x * s, z * s, s + 1, s + 1);
                }
            }
        }
    }
    /**
     * Returns the created buffer for the OmniMap.
     * @return
     */
    public BufferedImage getBuffer() {
        return buffer;
    }
    int mx = 0;
    int my = 0;
    /**
     * The Max variables.
     */
    public int msax, msay;
    /**
     * Is called to update the mouse coordinates.
     * @param me The MouseEvent.
     */
    public void mouse(MouseEvent me) {
        mx = me.getX();
        my = me.getY();
    }
    /**
     * Fired by the mouseClicked event in the game. Checks to see if the mode is toggled.
     * @param me
     */
    public void mouseClick(MouseEvent me) {
        if (me.getX() > px && me.getY() > py && me.getX() < px + width && me.getY() < py + height + 25) {
            entireWorldMode = !entireWorldMode;
        }
    }
    /**
     * Updates the player location in the map, and the location that the mouse is at.
     */
    public void update() {
        buffer = new BufferedImage(width, height + 25, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (entireWorldMode) {
            g.drawImage(map, 0, 0, null);
            int vx = (msax - 200) * 100 / (Constants.WINDOW_WIDTH * 4);
            int vy = (msay - 200) * 100 / (Constants.WINDOW_HEIGHT * 4);
            vx += 20;
            vy += 20;
            g.setColor(Color.red);
            g.drawRect(vx, vy, Constants.WINDOW_WIDTH / 100 * 2, Constants.WINDOW_HEIGHT / 100 * 2);
        } else {
        	EntityRenderer render = new EntityRenderer(GameComponent.game.getGrid());
        	Point p = render.screenToPoint(mx,my, GameComponent.game);
        	p.x += 250;
        	p.y += 200;
        	for (int x22 = -50; x22 < width/2; x22++) {
                for (int z22 = -50; z22 < height/2; z22++) {
                    int ttx = x22 + p.x;
                    int tty = z22 + p.y;
                    int x = x22 + 50;
                    int z = z22 + 50;
                    int s = 1;
                    if (ttx < 0 || tty < 0 || ttx > (Constants.WINDOW_WIDTH * 4) || tty > (Constants.WINDOW_HEIGHT * 4)) {
                        int rgb = r.nextInt(100);
                        g.setColor(new Color(rgb, rgb, rgb));
                        g.fillRect(x * s, z * s, s + 1, s + 1);
                    } else {
                        double y = eng.getPoint(ttx, tty);
                        boolean flag = y <= RenderConstants.GEN_WATER_HEIGHT;
                        if (flag) {
                            Color waterSample = RenderConstants.GEN_WATER_COLOR; //Constants.randomise(Constants.GEN_WATER_COLOR, Constants.GEN_COLOR_DIFF,
                            //r,false);
                            if (RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.FullSpeedAhead || RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.AtPort) waterSample = RenderConstants.adjust(RenderConstants.randomise(RenderConstants.GEN_WATER_COLOR,
                            RenderConstants.GEN_COLOR_DIFF, r, false), 1 - (y / RenderConstants.GEN_WATER_HEIGHT), 50);
                            if (y >= RenderConstants.GEN_WATER_HEIGHT - 0.05) {
                                double t = RenderConstants.GEN_WATER_HEIGHT - y;
                                waterSample = Helper.Lerp(RenderConstants.GEN_SAND_COLOR, RenderConstants.GEN_SAND_COLOR2 /*waterSample*/ , t / 0.05);
                                if (RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.FullSpeedAhead || RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.AtPort) waterSample = RenderConstants.randomise(waterSample, RenderConstants.GEN_COLOR_DIFF, r, false);
                            }
                            g.setColor(waterSample);
                            g.fillRect(x * s, z * s, s + 1, s + 1);
                        }
                        if (y >= RenderConstants.GEN_WATER_HEIGHT - 0.01 && y <= RenderConstants.GEN_WATER_HEIGHT + 0.05 && r.nextInt(3) == 1) {
                            flag = false;
                        }

                        if (!flag) {
                            Color groundSample = RenderConstants.adjust(RenderConstants.randomise(RenderConstants.GEN_GRASS_COLOR,
                            RenderConstants.GEN_COLOR_DIFF, r, false), y, 50);

                            if (y <= RenderConstants.GEN_WATER_HEIGHT + 0.1) {
                                double t = y - RenderConstants.GEN_WATER_HEIGHT;
                                groundSample = Helper.Lerp(RenderConstants.GEN_SAND_COLOR, groundSample, t / 0.1);
                                if (RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.FullSpeedAhead || RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.AtPort) groundSample = RenderConstants.randomise(groundSample, RenderConstants.GEN_COLOR_DIFF, r, false);
                            }
                            if (y >= RenderConstants.GEN_MOUNTAIN_HEIGHT) {
                                double t = y - RenderConstants.GEN_MOUNTAIN_HEIGHT;
                                groundSample = Helper.Lerp(groundSample, RenderConstants.GEN_MOUNTAIN_COLOR,
                                t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT));
                                if (RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.FullSpeedAhead || RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.AtPort) groundSample = RenderConstants.randomise(groundSample, RenderConstants.GEN_COLOR_DIFF, r, false);
                                if (RenderConstants.OPT_RENDERING_QUALITY != RenderingQuality.ShipSunk) groundSample = RenderConstants.adjust(groundSample, t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT), 30);
                            }

                            g.setColor(groundSample);
                            g.fillRect(x * s, z * s, s + 1, s + 1);
                        }
                    }
                }
        	}
        }
        g.setColor(new Color(100, 78, 47));
        g.drawRect(1, 1, width - 3, height - 3);
        g.setColor(new Color(74, 30, 3));
        g.drawRect(0, 0, width - 1, height - 1);
        g.fillRect(0, height, width, 25);

        g.setColor(new Color(100, 78, 47));
        if (entireWorldMode) {
            g.fillRect(1, height - 1, 49, 25);
            g.setColor(new Color(175, 137, 86)); //215,197,172));
            g.drawLine(1, height - 1, 1, height + 23);
            g.drawLine(2, height - 1, 2, height + 23);
            g.setColor(new Color(36, 28, 17)); //57,45,28));
            g.drawLine(1, height + 23, 49, height + 23);
            g.drawLine(2, height + 22, 49, height + 22);
            g.drawLine(50, height - 1, 50, height + 23);
            g.drawLine(49, height - 1, 49, height + 23);
        } else {
            g.fillRect(50, height - 1, 49, 25);
            g.setColor(new Color(175, 137, 86));
            g.drawLine(49, height - 1, 49, height + 23);
            g.drawLine(50, height - 1, 50, height + 23);
            g.setColor(new Color(36, 28, 17)); //57,45,28));
            g.drawLine(49, height + 23, 49 + 48, height + 23);
            g.drawLine(50, height + 22, 49 + 48, height + 22);
            g.drawLine(50 + 48, height - 1, 50 + 48, height + 23);
            g.drawLine(49 + 48, height - 1, 49 + 48, height + 23);
        }
        g.setColor(new Color(38, 65, 136));
        g.fillOval(17, height, 19, 19);
        g.setColor(Color.black);
        g.drawOval(17, height, 19, 19);
        g.setColor(new Color(90, 142, 81));
        g.drawLine(24, height + 1, 29, height + 1);
        g.drawLine(22, height + 2, 31, height + 2);
        g.drawLine(21, height + 3, 32, height + 3);
        g.drawLine(21, height + 4, 31, height + 4);
        g.drawLine(21, height + 5, 31, height + 5);
        g.drawLine(22, height + 6, 30, height + 6);
        g.drawLine(23, height + 7, 29, height + 7);
        g.drawLine(23, height + 8, 26, height + 8);
        g.drawLine(23, height + 9, 25, height + 9);
        g.drawLine(23, height + 10, 25, height + 10);
        g.drawLine(22, height + 11, 28, height + 11);
        g.drawLine(21, height + 12, 29, height + 12);
        g.drawLine(21, height + 13, 30, height + 13);
        g.drawLine(20, height + 14, 30, height + 14);
        g.drawLine(21, height + 15, 30, height + 15);
        g.drawLine(21, height + 16, 30, height + 16);
        g.drawLine(22, height + 17, 29, height + 17);
        g.drawLine(24, height + 18, 27, height + 18);

        g.setColor(new Color(122, 49, 5));
        g.fillOval(49 + 21, height, 10, 10);
        g.setColor(Color.black);
        g.drawOval(49 + 21, height, 10, 10);
        g.drawLine(49 + 23, height + 7, 49 + 15, height + 15);
    }
}