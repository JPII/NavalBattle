package com.jpii.navalbattle.renderer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.util.*;

import com.jpii.dagen.Engine;
import com.jpii.navalbattle.data.Constants;

public class OmniMap {
    Engine eng;
    int width, height;
    Random r;
    BufferedImage buffer, map;
    public boolean entireWorldMode = false;
    public OmniMap(Engine eng, int width, int height) {
        this.eng = eng;
        this.width = width;
        this.height = height;
        r = new Random(Constants.MAIN_SEED);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        map = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
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
                        waterSample = RenderConstants.randomise(waterSample,
                        RenderConstants.GEN_COLOR_DIFF, r, false);
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
                        groundSample = RenderConstants.randomise(groundSample,
                        RenderConstants.GEN_COLOR_DIFF, r, false);
                    }
                    if (y >= RenderConstants.GEN_MOUNTAIN_HEIGHT) {
                        double t = y - RenderConstants.GEN_MOUNTAIN_HEIGHT;
                        groundSample = Helper.Lerp(groundSample, RenderConstants.GEN_MOUNTAIN_COLOR,
                        t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT));
                        groundSample = RenderConstants.randomise(groundSample,
                        RenderConstants.GEN_COLOR_DIFF, r, false);
                        groundSample = RenderConstants.adjust(groundSample, t / (1.0 - RenderConstants.GEN_MOUNTAIN_HEIGHT), 30);
                    }

                    g.setColor(groundSample);
                    g.fillRect(x * s, z * s, s + 1, s + 1);
                }
            }
        }
    }
    public BufferedImage getBuffer() {
        return buffer;
    }
    int mx = 0;
    int my = 0;
    public int msax,msay;
    public void mouse(MouseEvent me) {
    	mx = me.getX();
    	my = me.getY();
    }
    public void mouseClick(MouseEvent me)
    {
    	if (me.getX() > 20 && me.getY() > 20 && me.getX() < 20 + width && me.getY() < 20 + height)
    	{
    		entireWorldMode = !entireWorldMode;
    	}
    }
    public void update() {
        Graphics g = buffer.getGraphics();
        if (entireWorldMode) {
            g.drawImage(map, 0, 0, null);
            int vx = (msax-200) * 100 / (Constants.WINDOW_WIDTH*4);
			int vy = (msay-200) * 100 / (Constants.WINDOW_HEIGHT*4);
			vx+=20;
			vy+=20;
			g.setColor(Color.red);
			g.drawRect(vx,vy,Constants.WINDOW_WIDTH/100*2,Constants.WINDOW_HEIGHT/100*2);
        }
        else
        {
        	for (int xt = 0; xt < width; xt++) {
                for (int zt = 0; zt < height; zt++) {
                    double y = eng.getPoint(msax-50+xt,msay-50+zt);
                    int rgb = (int)(y * 255);
                    g.setColor(new Color(rgb,rgb,rgb));
                    g.drawRect(xt,zt,1,1);
                }
        	}
        }
        g.setColor(new Color(74, 30, 3));
        g.drawRect(0,0,width-1,height-1);
        g.setColor(new Color(100, 78, 47));
        g.drawRect(1,1, width-3, height-3);
    }
}