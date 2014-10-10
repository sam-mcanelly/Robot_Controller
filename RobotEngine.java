

import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Sam McAnelly
 */
public class RobotEngine {
    
    private Socket controlSocket;
    private PrintWriter out;
    private String site;
    private int port;
    private boolean handShook = false;
    
    public static void main(String[] args){
        RobotEngine engine = new RobotEngine();
        engine.setSite("lear.cs.okstate.edu");
        engine.setPort(9095);
        engine.handshake();
    }
    
    public RobotEngine(){
        
    }
    
    public void setSite(String newSite){
        site = newSite;
    }
    public void setPort(int newPort){
        port = newPort;
    }
    
    public String getSite() { return site;};
    public int getPort() { return port; };
    
    public void handshake(){
        site = site.replaceAll("http://", "");
        site = site.replaceAll("www.", "");
        
        try {
            controlSocket = new Socket(site, port);
            out = new PrintWriter(controlSocket.getOutputStream());
            out.print("hello robot");
            out.flush();
            handShook = true;
            System.out.println("Successful Handshake!");
        } catch (java.net.UnknownHostException e){
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    
    public void takeOff(){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/ardrone/takeoff\",\"msg\":{}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void land(){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/ardrone/land\",\"msg\":{}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void brake(){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void moveXAxis(double speed){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":" + speed + ",\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void moveYAxis(double speed){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":" + speed + ",\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void moveZAxis(double speed){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":" + speed + "},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void rotateZAxis(double speed){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":" + speed + "}}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void rotateYAxis(double speed){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":" + speed + ",\"z\":0}}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void rotateXAxis(double speed){
        if (!handShook) {
            System.out.println("Not connected to anything!");
            return;
        }
        try {
            out.print("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":" + speed + ",\"y\":0,\"z\":0}}}");
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    
}
