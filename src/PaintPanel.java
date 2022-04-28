/*
  109403055 ��ޤG ���f��
*/


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BasicStroke;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PaintPanel extends JPanel{
	
	
	/*------------   declaring area -------------*/
	ArrayList<PaintPoints> points = new ArrayList<>();
	ArrayList<LinePoints> lines = new ArrayList<>();
	ArrayList<OvalPoints> ovals = new ArrayList<>();
	ArrayList<RecPoints> recs = new ArrayList<>();
	ArrayList<PaintPoints> eraserLines = new ArrayList<>();
	
	Point startingPoint, endingPoint, endingPointTemp;
	
	Data data = new Data();
	
	/*-------------------------------*/

	PaintPanel(){
		
		startingPoint = new Point();
		endingPoint = new Point();
		endingPointTemp = new Point();
		
		setBackground(Color.white);
		
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				switch(data.paintTool){
				case "����":
					points.add(new PaintPoints(e.getPoint(), data.color, data.thicknessInt));
					repaint();
					break;
				case "���u":
					endingPointTemp = e.getPoint();
					repaint();
					break;
				case "����":
					endingPointTemp = e.getPoint();
					repaint();
					break;
				case "�x��":
					endingPointTemp = e.getPoint();
					repaint();
					break;				
				}
				if(data.eraser) {
					eraserLines.add(new PaintPoints(e.getPoint(), data.color, data.thicknessInt));
					repaint();
				}

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
				MyJFrame.statusLabel.setText(String.format("��Ц�m: [%d, %d]", e.getX() ,e.getY()));
			}
			
		});
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				startingPoint = e.getPoint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				endingPoint = e.getPoint();
				//System.out.println(endingPoint);
				switch(data.paintTool) {
				case"���u":
					lines.add(new LinePoints(startingPoint, endingPoint, data.color, data.thicknessInt));
					repaint();
					break;
				
				case"����":
					ovals.add(new OvalPoints(startingPoint, endingPoint, data.color, data.filled, data.thicknessInt));
					repaint();
					break;
					
				case"�x��":
					recs.add(new RecPoints(startingPoint, endingPoint, data.color, data.filled, data.thicknessInt));
					repaint();
					break;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(g2 != null) {
			// ����\��
			for(PaintPoints p : points) {
				g2.setStroke(new BasicStroke(p.thickness));
				g2.setColor(p.color);
				g2.drawOval(p.x, p.y, 5, 5);
				g2.fillOval(p.x, p.y, 5, 5);
			}
			
			//  drawing straight lines
			for(LinePoints l : lines) {
				
				g2.setStroke(new BasicStroke(l.thickness));
				g2.setColor(l.color);
				g2.drawLine(l.startingP.x, l.startingP.y, l.endingP.x, l.endingP.y);
			}
			
			//drawing temporary straight lines
			if(data.paintTool == "���u") {
				g2.setStroke(new BasicStroke(data.thicknessInt));
				g2.setColor(data.color);
				g2.drawLine(startingPoint.x, startingPoint.y, endingPointTemp.x, endingPointTemp.y);
			}
			
			
			//  drawing the oval
			for(OvalPoints o : ovals) {
				g2.setColor(o.color);
				g2.setStroke(new BasicStroke(o.thickness));
				g2.drawOval(Math.min(o.startingP.x, o.endingP.x), Math.min(o.startingP.y, o.endingP.y), Math.abs(o.endingP.x - o.startingP.x), Math.abs(o.endingP.y - o.startingP.y));
				if(o.filled) {
					g2.fillOval(Math.min(o.startingP.x, o.endingP.x), Math.min(o.startingP.y, o.endingP.y), Math.abs(o.endingP.x - o.startingP.x), Math.abs(o.endingP.y - o.startingP.y));
				}
			}
			
			//  drawing temporary oval
			if(data.paintTool == "����") {
				g2.setColor(data.color);
				g2.setStroke(new BasicStroke(data.thicknessInt));
				g2.drawOval(Math.min(startingPoint.x, endingPointTemp.x), Math.min(startingPoint.y, endingPointTemp.y), Math.abs(endingPointTemp.x - startingPoint.x), Math.abs(endingPointTemp.y - startingPoint.y));
				if(data.filled) {
					g2.fillOval(Math.min(startingPoint.x, endingPointTemp.x), Math.min(startingPoint.y, endingPointTemp.y), Math.abs(endingPointTemp.x - startingPoint.x), Math.abs(endingPointTemp.y - startingPoint.y));
				}
			}
			
			
			
			//  drawing the rectangle
			for(RecPoints r : recs) {
				g2.setColor(r.color);
				g2.setStroke(new BasicStroke(r.thickness));
				g2.drawRect(Math.min(r.startingP.x, r.endingP.x), Math.min(r.startingP.y, r.endingP.y), Math.abs(r.endingP.x - r.startingP.x), Math.abs(r.endingP.y - r.startingP.y));
				if(r.filled) {
					g2.fillRect(Math.min(r.startingP.x, r.endingP.x), Math.min(r.startingP.y, r.endingP.y), Math.abs(r.endingP.x - r.startingP.x), Math.abs(r.endingP.y - r.startingP.y));
				}
			}
			
			
			//  drawing temporary rectangle
			if(data.paintTool == "�x��") {
				g2.setColor(data.color);
				g2.setStroke(new BasicStroke(data.thicknessInt));
				g2.drawRect(Math.min(startingPoint.x, endingPointTemp.x), Math.min(startingPoint.y, endingPointTemp.y), Math.abs(endingPointTemp.x - startingPoint.x), Math.abs(endingPointTemp.y - startingPoint.y));
				if(data.filled) {
					g2.fillRect(Math.min(startingPoint.x, endingPointTemp.x), Math.min(startingPoint.y, endingPointTemp.y), Math.abs(endingPointTemp.x - startingPoint.x), Math.abs(endingPointTemp.y - startingPoint.y));
				}
			}
			
			// eraser
			for(PaintPoints e : eraserLines) {
				g2.setStroke(new BasicStroke(e.thickness));
				g2.setColor(Color.white);
				g2.drawOval(e.x, e.y, 5, 5);
				g2.fillOval(e.x, e.y, 5, 5);
				}

		}
		
	}
	public void clearPanel() {
		
		points.clear();
		lines.clear();
		ovals.clear();
		recs.clear();
		eraserLines.clear();
		startingPoint = new Point();
		endingPoint = new Point();
		endingPointTemp = new Point();
		repaint();

	}
	
	
	public void storeImage() {
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.paint(image.createGraphics());
		try {
			String fileName = JOptionPane.showInputDialog(null, "��J�ɮצW��:");
			
			String currentStorePath = System.getProperty("user.dir");
			
			ImageIO.write(image, "jpg", new File(String.format("%s\\%s.jpg", currentStorePath, fileName)));
			
			JOptionPane.showMessageDialog(null, "�x�s���\\n�w�x�s�b" + currentStorePath + "\\" + fileName + ".jpg");
			
			clearPanel();
			
		}
		catch(IOException e) {
			
		}
	}

	
}

class PaintPoints{
	
	int x;
	int y;
	Color color;
	int thickness;
	
	PaintPoints(Point p, Color c, int thickness){
		this.x = p.x;
		this.y = p.y;
		this.color = c;
		this.thickness = thickness;
	}

}

class LinePoints{
	
	Point startingP, endingP;
	Color color;
	int thickness;
	
	LinePoints(Point startingP, Point endingP, Color c, int thickness){
		this.startingP = startingP;
		this.endingP = endingP;
		this.color = c;
		this.thickness = thickness;
	}

}

class OvalPoints{
	
	Point startingP, endingP;
	Color color;
	boolean filled;
	int thickness;
	
	OvalPoints(Point startingP, Point endingP, Color c, boolean f, int thickness){
		this.startingP = startingP;
		this.endingP = endingP;
		this.color = c;
		this.filled = f;
		this.thickness = thickness;
	}

}

class RecPoints{
	
	Point startingP, endingP;
	Color color;
	boolean filled;
	int thickness;
	
	RecPoints(Point startingP, Point endingP, Color c, boolean f, int thickness){
		this.startingP = startingP;
		this.endingP = endingP;
		this.color = c;
		this.filled = f;
		this.thickness = thickness;
	}

}


