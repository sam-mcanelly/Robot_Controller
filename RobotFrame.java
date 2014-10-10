

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Sam McAnelly
 */
public class RobotFrame extends JFrame {
    
    private JButton forward;
    private JButton reverse;
    private JButton left;
    private JButton right;
    private JButton rotateLeft;
    private JButton rotateRight;
    private JButton takeOff;
    private JButton land;
    private JButton up;
    private JButton down;
    private JLabel speed;
    private JSlider speedControl;
    
    private JButton reload;
    private JTextField urlField;
    private JTextField portField;
    
    private String url;
    private int port;
    private double speedMovement = 0.25;
    private double speedRotation = 1.0;
    
    private RobotEngine controller;
    
    public RobotFrame(){
        //Initializing engine
        controller = new RobotEngine();
        controller.setPort(9095);
        controller.setSite("lear.cs.okstate.edu");
        controller.handshake();
        
        //Setting dimensions and default stuff
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Robot Controller");
        this.setSize(500, 550);
        this.setResizable(false);
        this.setLayout(null);
        
        //Reload Button
        reload = new JButton("connect");
        reload.setSize(60, 20);
        reload.setLocation(10, 10);
        reload.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                controller.setSite(urlField.getText());
                try {
                        int newPort = Integer.parseInt(portField.getText());
                        controller.setPort(newPort);
                        boolean test = controller.handshake();
                        if (test)
                            JOptionPane.showMessageDialog(null, "Connection Created!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null, "Connection Failed!", "Failure!", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException a){
                        a.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(reload);
        
        
        //URL text field
        urlField = new JTextField("lear.cs.okstate.edu");
        urlField.setSize(350, 20);
        urlField.setLocation(80, 10);
        urlField.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    controller.setSite(urlField.getText());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
            
        });
        this.add(urlField);
        
        //Port text field
        portField = new JTextField("9095");
        portField.setSize(60, 20);
        portField.setLocation(430, 10);
        portField.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        int newPort = Integer.parseInt(portField.getText());
                        controller.setPort(newPort);
                    } catch (NumberFormatException a){
                        a.printStackTrace();
                    }
                    
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        
        });
        this.add(portField);
        
        //Speed Label
        speed = new JLabel("Speed");
        speed.setSize(40, 20);
        speed.setLocation(15, 480);
        this.add(speed);
        
        //Speed Control
        speedControl = new JSlider(JSlider.HORIZONTAL, 0, 10, 2);
        speedControl.setMajorTickSpacing(10);
        speedControl.setMinorTickSpacing(1);
        speedControl.setPaintTicks(true);
        speedControl.setPaintLabels(true);
        speedControl.addChangeListener(new ChangeListener(){
            
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                speedMovement = source.getValue();
            }
            
        });
        speedControl.setSize(400, 50);
        speedControl.setLocation(70, 470);
        this.add(speedControl);
        
        //Forward Button
        forward = new JButton("forward");
        forward.setSize(150, 50);
        forward.setLocation(175, 50);
        forward.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                controller.moveXAxis(speedMovement);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.brake();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(forward);
        
        //Reverse Button
        reverse = new JButton("reverse");
        reverse.setSize(150, 50);
        reverse.setLocation(175, 150);
        reverse.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                controller.moveXAxis(-speedMovement);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.brake();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(reverse);
        
        //Left Button
        left = new JButton("left");
        left.setSize(150, 50);
        left.setLocation(10, 100);
        left.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                controller.moveYAxis(speedMovement);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.brake();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(left);
        
        //Right Button
        right = new JButton("right");
        right.setSize(150, 50);
        right.setLocation(340, 100);
        right.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                controller.moveYAxis(-speedMovement);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.brake();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(right);
        
        //Rotate Left Button
        rotateLeft = new JButton("rotate left");
        rotateLeft.setSize(150, 50);
        rotateLeft.setLocation(10, 200);
        rotateLeft.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                controller.rotateZAxis(speedRotation);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.brake();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(rotateLeft);
        
        //Rotate Right Button
        rotateRight = new JButton("rotate right");
        rotateRight.setSize(150, 50);
        rotateRight.setLocation(340, 200);
        rotateRight.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                controller.rotateZAxis(-speedRotation);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.brake();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(rotateRight);
        
        //Up button
        up = new JButton("ascend");
        up.setSize(150, 50);
        up.setLocation(175, 250);
        up.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                controller.moveZAxis(speedMovement);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.brake();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(up);
        
        //Down button
        down = new JButton("descend");
        down.setSize(150, 50);
        down.setLocation(175, 350);
        down.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                controller.moveZAxis(-speedMovement);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.brake();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(down);
        
        //Takeoff button
        takeOff = new JButton("take off");
        takeOff.setSize(150, 50);
        takeOff.setLocation(10, 400);
        takeOff.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.takeOff();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
            
            
        });
        this.add(takeOff);
        
        //land button
        land = new JButton("land");
        land.setSize(150, 50);
        land.setLocation(340, 400);
        land.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.land();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
            
            
        });
        this.add(land);
    }
    
}
