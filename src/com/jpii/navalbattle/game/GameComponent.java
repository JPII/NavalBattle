/*
 * Copyright (C) 2012 JPII and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jpii.navalbattle.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.*;
import java.beans.*;

import com.jpii.dagen.*;
import com.jpii.navalbattle.data.Helper;
import com.jpii.navalbattle.game.entity.Entity;
import com.jpii.navalbattle.gui.cotton.NavButton;
import com.jpii.navalbattle.util.Stopwatch;

/**
 * @author MKirkby
 * 
 */
@SuppressWarnings("serial")
public class GameComponent extends JComponent {
	JFrame frame;
	ArrayList<Entity> entities;
	Timer ticker;

	BufferedImage grid, shadow,map;

	int test;
	JSlider slider;
	NavButton nav0;

	Engine eng;
	
	int zoomx, zoomy;
	
	boolean invokeFlag = false;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	
	public GameComponent(JFrame frame) {
		this.frame = frame;
		
		//Lets use Nimbus for now.
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {}
		
		nav0 = new NavButton();
		nav0.setLocation(128, 159);
		nav0.setSize(100,81);
		
		slider = new JSlider();
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				onZoom();
			}
		});
		slider.setForeground(new Color(0,0,0,127));
		slider.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				onZoom();
			}
		});
		slider.setPaintTicks(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(10, 63, 31, 177);
		add(slider);
		
		add(nav0);
		
		JButton button = new JButton("►");
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				zoomx += getGridSize();
				onZoom();
			}
		});
		button.setBounds(137, 28, 54, 23);
		add(button);
		
		button_1 = new JButton("▼");
		button_1.setBounds(85, 48, 54, 23);
		button_1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				zoomy += getGridSize();
				onZoom();
			}
		});
		add(button_1);
		
		button_2 = new JButton("▲");
		button_2.setBounds(85, 11, 54, 23);
		button_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				zoomy -= getGridSize();
				onZoom();
			}
		});
		add(button_2);
		
		button_3 = new JButton("◄");
		button_3.setBounds(32, 28, 54, 23);
		button_3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				zoomx -= getGridSize();
				onZoom();
			}
		});
		add(button_3);
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}
		};
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		eng = new Engine(w,h);
		eng.generate(MapType.Hills, (int)(Math.random() * 99999999999.9), 1.0);
		
		grid = Helper.genGrid(w, h, getGridSize()*3);
		shadow = Helper.genInnerShadow(w, h);
		map = Helper.genMap(eng,0,0,w,h, w, h, 1);

		entities = new ArrayList<Entity>();

		ticker = new Timer(100, al);

		start();

	}

	public void addEntity(Entity entity) {
		if (entity != null) {
			entities.add(entity);
		}
	}

	public Entity getEntity(String tag) {
		for (int x = 0; x < entities.size(); x++) {
			if (entities.get(x).getTag().toLowerCase() == tag.toLowerCase()) {
				return entities.get(x);
			}
		}
		return null;
	}

	public Entity getEntity(int index) {
		return entities.get(index);
	}

	private void tick() {
		if (!invokeFlag) {
			for (int x = 0; x < entities.size(); x++) {
				if (entities.get(x) != null) {
					entities.get(x).tick();
				}
			}
			test += 1;
			repaint();
		}
		invokeFlag = false;
	}
	
	private void onZoom() {
		Stopwatch watch = new Stopwatch();
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		watch.start();
		grid = Helper.genGrid(w,h, getGridSize()*3);
		watch.stop();
		
		//System.out.println("rid formulation took: " + watch.getString());
		
		watch.start();
		//was zoomx and zoomy
		map = Helper.genMap(eng,0,0, w/getGridSize(), h/getGridSize(), w,h, getGridSize());
		watch.stop();
		
		//System.out.println("Map formulation took: " + watch.getString());
		
		invokeFlag = true;
		
		repaint();
	}
	public int getGridSize() {
		int c = (slider.getValue() * 40)/100;
		if (c > 5)
			return c;
		else
			return 5;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth() + 1, getHeight() + 1);
		
		g.drawImage(map,zoomx,zoomy,null);

		g.drawImage(grid, 0, 0, null);
		g.drawImage(shadow, 0, 0, null);

		g.setColor(Color.white);
		g.drawString("This is just a test.", 100, 100);
		g.drawString(
				"In the future, this is where the map, enitities, and GUI would go.",
				100, 120);
		g.drawString(
				"Just to make sure that the ticker/updater is working, here is how many ticks performed:"
						+ test, 100, 140);
	}

	public void setTimeTick(int interval) {
		ticker.setDelay(interval);
	}

	public int getTimeTick() {
		return ticker.getDelay();
	}

	public void start() {
		ticker.start();
	}

	public void stop() {
		ticker.stop();
	}

	public void repaint() {
		frame.repaint();
	}
}
