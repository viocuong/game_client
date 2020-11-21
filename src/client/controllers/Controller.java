/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;
import Models.com.*;
import java.net.Socket;
import java.sql.*;
import client.view.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author cuongnv
 */
public class Controller {
    private Socket socketClient;
    private String serverHost="localhost";
    private Connection con;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int serverPort=8888;
    private LoginView loginView;
    private Game game;
    private Map<String, Pair<User,Integer>> listPlayer;
    public Controller(){
        loginView = new LoginView();
        loginView.setVisible(true);
        loginView.addListentBtnLogin(new listentBtnLogin());
    }
    public void getUserOnline(){
        
        send(new Request("getListPlayer"));
    }
    public class listentBtnLogin implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me){
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            String s = null;
//            loginView.dispose();
//            Home h =new Home("hello  dww");
            //h.setLabel("hello");
//            h.setVisible(true);
//            h.setLabel("hello");
            openConnection(serverHost, serverPort);
            User user = loginView.getUser();
            
            send(new Request("login",(Object) user));
            s = receive();
            //loginView.showMessage(s);
            if(s.equals("success")){
                try {
                    loginView.dispose();
                    game = new Game();
                    oos.reset();
                    getUserOnline();
                    listPlayer =(Map<String, Pair<User,Integer>>) ois.readObject();
                    System.out.println(listPlayer.size());
                    showListPlayer();
                    
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
            else loginView.showMessage(s);
            closeConnection();
        }
        @Override
        public void mousePressed(MouseEvent me){
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent me){
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent me){
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public void mouseExited(MouseEvent me){
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    public void showListPlayer(){
       
        for(Map.Entry<String, Pair<User,Integer>> player: listPlayer.entrySet()){
            System.out.println(player.getKey()+" "+player.getValue().getKey().getUserName()+ " "+player.getValue().getValue());
        }
    }
    public String receive(){
        String res=null;
        try {
            
            res = (String) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
        
    }
    public void closeConnection(){
        try {
            socketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void openConnection(String serverHost, int port){
        try {
            socketClient = new Socket(serverHost, port);
            this.oos = new ObjectOutputStream(socketClient.getOutputStream());
            this.ois = new ObjectInputStream(socketClient.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void send(Request request){
        //openConnection(serverHost, serverPort);
        try {
            
            oos.writeObject(request);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   
}
