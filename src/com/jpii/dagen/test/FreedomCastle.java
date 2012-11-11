package com.jpii.dagen.test;

import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.*;
import java.util.*;

import javax.swing.*;
import com.jpii.dagen.*;

public class FreedomCastle extends JFrame
{
	public static boolean BLUR_ENGINE_ENABLED = false;
	public static boolean MULITHREAD_ENABLED = true;
	public static boolean DAYTIME = true;
	
	
	
	
	
	
	
	
	
	
	private static final long serialVersionUID = -6355735728325427702L;
	public static void main(String[] args)
    {
        new FreedomCastle().setVisible(true);
    }

    Engine eng, cloudGen;
    ArrayList < SimpleBrownian > rivers;
    BufferedImage terrain, treeBuffer, nickiMinaj, treeShadow, map, all, clouds;
    public static int pixelSize = 2;
    public static Random rand;
    ArrayList < TreeData > trees;
    Color[][] grassRand;
    public static int SEEDER = 0;
    long miliStart;
    long miliTotal;
    long miliDStart;
    long miliDEnd;
    public BufferedImage shadowOuter;
    public FreedomCastle()
    {
    	setTitle("FreedomCastle");
        addMouseMotionListener(new MouseAdapter()
        {
            public void mouseMoved(MouseEvent e)
            {
                mouse(e.getX(), e.getY());
            }
        });
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                init();
            }
        });
        setResizable(false);
        setSize(800, 600);
        initInnerShadow();
        init();
    }
	private void initInnerShadow() {
		shadowOuter = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics g = shadowOuter.getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		Point2D center = new Point2D.Float(getWidth()/2, getHeight()/2);
        float radius = getWidth();
        Point2D focus = new Point2D.Float(getWidth()/2, getHeight()/2);
        float[] dist = {0.0f,0.3f, 1.0f};
        Color[] colors = {new Color(0,0,0,0),new Color(0,0,0,0), new Color(0,0,0,255)};
        RadialGradientPaint p = new RadialGradientPaint(center, radius, focus, dist, colors, CycleMethod.NO_CYCLE);
        g2.setPaint(p);
        g2.fillRect(0, 0, getWidth(), getHeight());
	}
    public void init()
    {
    	miliStart = System.currentTimeMillis();
        SEEDER = 129;//(int)(Math.random() * 255);
        if (trees != null)
        {
            trees.clear();
        }
        trees = new ArrayList < TreeData > ();
        grassRand = new Color[801][601];
        eng = new Engine(800, 600);
        eng.generate(SEEDER, 0.6);
        cloudGen = new Engine(800,600);
        cloudGen.generate(SEEDER+1, 0.4);
        clouds = new BufferedImage(800,600,BufferedImage.TYPE_INT_ARGB);
        System.out.println("Seed: " + SEEDER);
        rivers = new ArrayList < SimpleBrownian > ();
        for (int v = 0; v < (int)(Math.random() * 7) + 2; v++)
        {
            SimpleBrownian river = new SimpleBrownian(400, 300);
            river.setMaxIterations(1000);
            for (int x = (int)(Math.random() * 300); x < 400; x++)
            {
                for (int y = 0; y < 300; y++)
                {
                    if (eng.getPoint(x, y) < 0.4)
                    {
                        river.setStartPoint(x, y);
                        x = 400;
                        y = 300;
                    }
                }
            }
            if (Math.random() < 0.1)
            {
                for (int c = 0; c < rivers.size(); c++)
                {
                    SimpleBrownian b = rivers.get(c);
                    if (b.ppx != 0 && b.ppy != 0)
                    {
                        river.setStartPoint(b.ppx, b.ppy);
                        break;
                    }
                }
            }
            river.generate((int)(Math.random() * 255), 1.0);
            rivers.add(river);
        }
        terrain = new BufferedImage(400 * pixelSize, 300 * pixelSize, BufferedImage.TYPE_INT_RGB);
        treeBuffer = new BufferedImage(400 * pixelSize, 300 * pixelSize, BufferedImage.TYPE_INT_ARGB);
        nickiMinaj = new BufferedImage(400 * pixelSize, 300 * pixelSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gt = (Graphics2D) terrain.getGraphics();
        gt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D gtree = (Graphics2D) treeBuffer.getGraphics();
        gtree.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D gw = (Graphics2D) nickiMinaj.getGraphics();
        gw.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics gcl = clouds.getGraphics();
        rand = new Random();

        for (int xtt = 0; xtt < 800; xtt += 2)
        {
            for (int ytt = 600; ytt > 0; ytt -= 2)
            {
            	
                double pt = eng.getPoint(xtt, ytt);
                int x = xtt / 2;
                int y = ytt / 2;
                grassRand[xtt][ytt] = Color.black;
                
                double ptc = cloudGen.getPoint(xtt,ytt);
                int rgbe = (int)(ptc * 127)+127;
                int ale = (int)(ptc * 127) - 40;
                if (ale < 0)
                	ale = 0;
                gcl.setColor(new Color(rgbe,rgbe,rgbe,ale));
                gcl.fillRect(x*pixelSize,y*pixelSize,pixelSize,pixelSize);
                if (pt < 0.4)
                {
                    int rwc = r(rand, - 5, 5) + (int)(pt * 61) + 41;
                    int gwc = r(rand, - 5, 5) + (int)(pt * 54) + 34;
                    int bwc = r(rand, - 5, 5) + (int)(pt * 188) + 60;
                    Color c = new Color(rwc, gwc, bwc);
                    if (!DAYTIME)
                    {
                    	int rwc2 = rwc - 40;
                    	int gwc2 = gwc - 40;
                    	int bwc2 = bwc - 40;
                    	if (rwc2 < 0)
                    		rwc2 = 0;
                    	if (gwc2 < 0)
                    		gwc2 = 0;
                    	if (bwc2 < 0)
                    		bwc2 = 0;
                    	c = new Color(rwc2,gwc2,bwc2);
                    }
                    gw.setColor(c);
                    grassRand[x][y] = new Color(rwc, gwc, bwc);
                    gw.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                }
                else
                {
                    int rtc = r(rand, - 5, 5) + (int)(pt * 60) + 33;
                    int gtc = r(rand, - 5, 5) + (int)(pt * 101) + 51;
                    int btc = r(rand, - 5, 5) + (int)(pt * 42) + 21;
                    Color c = new Color(rtc, gtc, btc);
                    if (!DAYTIME)
                    {
                    	c = c.darker().darker();
                    }
                    if (pt > 0.49)
                    {
                    	//c = Lerp(c,new Color(57,84,18),(pt - 0.7)/0.3);
                    	c = Lerp(c,new Color(56+r(rand,-7,7),43+r(rand,-7,7),10+r(rand,-7,7)),(pt-0.5)/0.5);
                    }
                    gt.setColor(c);
                    grassRand[x][y] = c;
                    gt.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                }
                /*if (pt > 0.38 && pt < 0.43 && rand.nextInt(10) == 1)
				{
					gw.setColor(new Color(24+r(rand,-5,5),50+r(rand,-5,5),28+r(rand,-5,5)));
					gw.fillRect((x-1)*pixelSize,(y-2)*pixelSize,1,4*pixelSize);
					gw.setColor(new Color(95+r(rand,-5,5),29+r(rand,-5,5),7+r(rand,-5,5)));
					gw.fillRect((x-1)*pixelSize,(y-2)*pixelSize,1,4);
				}*/
                if (pt > 0.55)
                {
                    if (rand.nextInt(300) == 1)
                    {
                        int tx = (x * pixelSize) - (2 * pixelSize);
                        int ty = (y * pixelSize) - (5 * pixelSize);
                        trees.add(new TreeData(tx, ty));
                    }
                }
                for (int c = 0; c < rivers.size(); c++)
                {
                    SimpleBrownian river = rivers.get(c);
                    if (river.getPoint(x, y) == 1.0)
                    {
                        int rwc = r(rand, - 5, 5) + (int)(pt * 41) + 41;
                        int gwc = r(rand, - 5, 5) + (int)(pt * 34) + 34;
                        int bwc = r(rand, - 5, 5) + (int)(pt * 168) + 60;
                        gw.setColor(new Color(rwc, gwc, bwc));
                        grassRand[x][y] = new Color(rwc, gwc, bwc);
                        gw.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                    }
                }
            }
        }
        Graphics treeB = treeBuffer.getGraphics();
        for (int c = trees.size() - 1; c > 0; c--)
        {
            TreeData tree = trees.get(c);
            if (tree != null)
            {
                treeB.drawImage(tree.shadowMap, tree.x, tree.y, null);
                treeB.drawImage(tree.tree, tree.x, tree.y, null);
            }
        }
        map = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        mouse(50,50);
        miliTotal = System.currentTimeMillis() - miliStart;
    }
    public void mouse(int x, int y)
    {
    	miliDStart = System.currentTimeMillis();
        if (eng == null || map == null || grassRand == null) return;
        mx = x;
        my = y;
        Graphics g = map.getGraphics();
        int left = x - 25;
        int right = y - 25;
        int transparency = 100;
        for (int cx = 0; cx < 100; cx++)
        {
            for (int cy = 0; cy < 100; cy++)
            {
                double pt = eng.getPoint(cx + left, cy + right);
                x = cx;
                y = cy;
                g.setColor(Color.black);
                int xxx = cx + left;
                int yyy = cy + right;
                if (xxx > 799) xxx = 799;
                if (yyy > 799) yyy = 799;
                if (xxx < 0) xxx = 0;
                if (yyy < 0) yyy = 0;
                g.setColor(grassRand[xxx / 2][yyy / 2]);
                g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
                if (pt > 0.38 && pt < 0.43 && rand.nextInt(10) == 1)
                {
                    g.setColor(new Color(24 + r(rand, - 5, 5), 50 + r(rand, - 5, 5), 28 + r(rand, - 5, 5), transparency));
                    g.fillRect((x - 1) * pixelSize, (y - 2) * pixelSize, 2, 4 * pixelSize);
                    g.setColor(new Color(95 + r(rand, - 5, 5), 29 + r(rand, - 5, 5), 7 + r(rand, - 5, 5), transparency));
                    g.fillRect((x - 1) * pixelSize, (y - 2) * pixelSize, 2, 4);
                }
            }
        }
        for (int c = trees.size() - 1; c > 0; c--)
		{
			TreeData tree = trees.get(c);
			if (tree != null)
			{
				int tx = ((tree.x) - (mx-25-10))*2;
				int ty = ((tree.y) - (my-25-10))*2;
				g.drawImage(tree.shadowMap,tx,ty,null);
				g.drawImage(tree.tree,tx,ty,null);
			}
		}
        g.setColor(new Color(74, 30, 3));
        g.drawRect(0, 0, 99, 99);
        g.setColor(new Color(100, 78, 47));
        g.drawRect(1, 1, 97, 97);
        if (BLUR_ENGINE_ENABLED)
        {
        	map = Blurs.getBlurFilter(0.9f).filter(map, null);
        }
        repaint();
    }
    int mx = 0;
    int my = 0;
    public static int r(Random inst, int min, int max)
    {
        return inst.nextInt(max - min) + min;
    }
    public double Lerp(int num0, int num1, double amount)
    {
    	return num0 + (amount*(num1-num0));
    }
    public Color Lerp(Color color0, Color color1, double amount)
    {
	    int r = (int)Lerp(color0.getRed(), color1.getRed(), amount);
	    int g = (int)Lerp(color0.getGreen(), color1.getGreen(), amount);
	    int b = (int)Lerp(color0.getBlue(), color1.getBlue(), amount);
	    int a = (int)Lerp(color0.getAlpha(), color1.getAlpha(), amount);
	    return new Color(r,g,b,a);
    }
    public void paint(Graphics g2)
    {
    	long miniMapEnd = System.currentTimeMillis() - miliDStart;
    	BufferedImage buffer = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = (Graphics2D)buffer.getGraphics();
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g.drawImage(terrain, 0, 0, null);
        g.drawImage(treeBuffer, 0, 0, null);
        g.drawImage(nickiMinaj, 0, 0, null);
        //g.drawImage(clouds, 0, 0, null);
        g.drawImage(shadowOuter, 0,0,null);
        g.drawImage(map, 8, 30, null);
        g.setColor(Color.red);
        g.fillRect(mx - 1, my - 1, 2, 2);
        
        g.setFont(new Font("Gabriola",Font.BOLD,24));
        g.setColor(new Color(194,184,50));//(47,37,19));
        g.drawString("Total calculation time: " + miliTotal,5, 156);
        g.drawString("Total draw time: " + (System.currentTimeMillis() - miliDStart),5, 180);
        g.drawString("Total MiniZoomMap draw time: " + (miniMapEnd),5, 204);
        
        g2.drawImage(buffer,0,0,null);
    }
}
class TreeData implements Runnable
{
    int x, y;
    public BufferedImage tree, shadowMap;
    public TreeData(int x, int y)
    {
        this.x = x;
        this.y = y;
        if (FreedomCastle.MULITHREAD_ENABLED)
        	run();
        else
        	init();
    }
    public void run()
    {
    	init();
    }
    public void init()
    {
        boolean isAutumn = FreedomCastle.rand.nextBoolean();
        tree = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        shadowMap = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics gtree = tree.getGraphics();
        Graphics shad = shadowMap.getGraphics();
        gtree.setColor(new Color(80 + r(FreedomCastle.rand, - 12, 12), 76 + r(FreedomCastle.rand, - 12, 12), 40 + r(FreedomCastle.rand, - 12, 12)));
        gtree.fillRect(9, 6, 1 * FreedomCastle.pixelSize, 7 * FreedomCastle.pixelSize);
        for (int c = 0; c < FreedomCastle.r(FreedomCastle.rand, 60, 100); c++)
        {
            int trans = r(FreedomCastle.rand, 60, 150);
            Color leaf;
            if (!isAutumn) leaf = new Color(62 + r(FreedomCastle.rand, - 6, 6), 140 + r(FreedomCastle.rand, - 6, 6), 9 + r(FreedomCastle.rand, - 6, 6), trans);
            else
            {
                leaf = new Color(50 + r(FreedomCastle.rand, - 6, 6), 75 + r(FreedomCastle.rand, - 6, 6), 35 + r(FreedomCastle.rand, - 6, 6), trans);
                //else
                int dark = r(FreedomCastle.rand,-40,-20);
                leaf = new Color(126+r(FreedomCastle.rand, -10, 6)+dark,101+r(FreedomCastle.rand, -10, 6)+dark,
                		52+r(FreedomCastle.rand, -5, 6)+dark,trans);
                
            }

            gtree.setColor(leaf);
            int rpx = r(FreedomCastle.rand, 0 * FreedomCastle.pixelSize, 7 * FreedomCastle.pixelSize);
            int rpy = r(FreedomCastle.rand, 1 * FreedomCastle.pixelSize, 4 * FreedomCastle.pixelSize);
            int rad = r(FreedomCastle.rand, 1 * FreedomCastle.pixelSize, 3 * FreedomCastle.pixelSize);
            if (trans > 75) trans -= 70;
            shad.setColor(new Color(0, 0, 0, trans));
            gtree.fillOval(rpx, rpy, rad, rad);
            if (FreedomCastle.rand.nextInt(4) == 1)
            	shad.fillOval(rpx + (5 - rpy)+7, rpy + 3+5, (int)(rad * 1.75), rad);
        }
    }
    public static int r(Random inst, int min, int max)
    {
        return inst.nextInt(max - min) + min;
    }
}
class Blurs
{
	public static ConvolveOp getBlurFilter(float radius) {
        float size = radius * 2 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[(int)((size * size)+2)];
        
        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }
        
        Kernel kernel = new Kernel((int)(size+1), (int)(size+1), data);
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }
}