package com.jpii.navalbattle.rendererbeta;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.jpii.navalbattle.data.Constants;
import com.jpii.navalbattle.renderer.Helper;
import com.jpii.navalbattle.renderer.RenderConstants;
import com.jpii.navalbattle.renderer.RenderingQuality;

public class ChunkRenderer {
	public static BufferedImage genChunk(byte[][] bytecode) {
		Random r = new Random(Constants.MAIN_SEED);
		BufferedImage bi = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)bi.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int x = 0; x < 100; x++) {
            for (int z = 0; z < 100; z++) {
                int ttx = x*2;
                int tty = x*2;
                int s = 3;
                if (ttx < 0 || tty < 0 || ttx > (Constants.WINDOW_WIDTH * 4) || tty > (Constants.WINDOW_HEIGHT * 4)) {
                    int rgb = r.nextInt(100);
                    g.setColor(new Color(rgb, rgb, rgb));
                    g.fillRect(x * s, z * s, s + 1, s + 1);
                } else {
                    double y = (bytecode[ttx][tty] / 255.0);
                    boolean flag = y <= RenderConstants.GEN_WATER_HEIGHT;
                    if (flag) {
                        Color waterSample = RenderConstants.GEN_WATER_COLOR; //Constants.randomise(Constants.GEN_WATER_COLOR, Constants.GEN_COLOR_DIFF,
                        //r,false);
                        if (RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.FullSpeedAhead )/*|| RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.AtPort)*/ waterSample = Helper.adjust(Helper.randomise(RenderConstants.GEN_WATER_COLOR,
                        RenderConstants.GEN_COLOR_DIFF, r, false), 1 - (y / RenderConstants.GEN_WATER_HEIGHT), 50);
                        if (y >= RenderConstants.GEN_WATER_HEIGHT - 0.05) {
                            double t = RenderConstants.GEN_WATER_HEIGHT - y;
                            waterSample = Helper.Lerp(RenderConstants.GEN_SAND_COLOR, RenderConstants.GEN_SAND_COLOR2 /*waterSample*/ , t / 0.05);
                            if (RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.FullSpeedAhead )/*|| RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.AtPort)*/ waterSample = Helper.randomise(waterSample, RenderConstants.GEN_COLOR_DIFF, r, false);
                        }
                        g.setColor(waterSample);
                        //g.fillRect(x * s, z * s, s + 1, s + 1);
                    }
                    else if (y >= RenderConstants.GEN_WATER_HEIGHT - 0.01 && y <= RenderConstants.GEN_WATER_HEIGHT + 0.05 && r.nextInt(3) == 1) {
                        flag = false;
                    }

                    else if (!flag) {
                        Color groundSample = Helper.adjust(Helper.randomise(RenderConstants.GEN_GRASS_COLOR,
                        RenderConstants.GEN_COLOR_DIFF, r, false), y, 50);

                        if (y <= RenderConstants.GEN_WATER_HEIGHT + 0.1) {
                            double t = y - RenderConstants.GEN_WATER_HEIGHT;
                            groundSample = Helper.Lerp(RenderConstants.GEN_SAND_COLOR, groundSample, t / 0.1);
                            if (RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.FullSpeedAhead/* || RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.AtPort*/) groundSample = Helper.randomise(groundSample, RenderConstants.GEN_COLOR_DIFF, r, false);
                        }
                        if (y >= RenderConstants.GEN_MOUNTAIN_HEIGHT) {
                            double t = y - RenderConstants.GEN_MOUNTAIN_HEIGHT;
                            groundSample = Helper.Lerp(groundSample, RenderConstants.GEN_MOUNTAIN_COLOR,
                            t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT));
                            if (RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.FullSpeedAhead/* || RenderConstants.OPT_RENDERING_QUALITY == RenderingQuality.AtPort*/) groundSample = Helper.randomise(groundSample, RenderConstants.GEN_COLOR_DIFF, r, false);
                            /*if (RenderConstants.OPT_RENDERING_QUALITY != RenderingQuality.ShipSunk)*/ groundSample = Helper.adjust(groundSample, t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT), 30);
                        }

                        g.setColor(groundSample);
                    }
                    g.fillRect(x * s, z * s, s + 1, s + 1);
                }
            }
        }
		return bi;
	}
}
