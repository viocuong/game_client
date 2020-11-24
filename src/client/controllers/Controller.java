/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Bug dị : khai bao ois trước oos lỗi :)
package client.controllers;
import Models.com.Pair;
import Models.com.Request;
import Models.com.User;
import java.net.Socket;
import java.sql.*;
import client.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
/**
 *
 * @author cuongnv
 */
public class Controller implements Runnable {
    private Socket socketClient;
    private String serverHost="localhost";
    private Connection con;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int serverPort=8888;
    private LoginView loginView;
    private Game game;
    private Map<String, Pair<User,Integer>> listPlayer = new HashMap<>();
    private User myAccount;
    private boolean is_login = false;
    public Controller(){
        loginView = new LoginView();
        loginView.setVisible(true);
        loginView.addListentBtnLogin(new listentBtnLogin());
        openConnection(serverHost, serverPort);
    }
    public void run(){
        try {
            if(is_login == true){
                while(true){  
                    
                    Request respond = (Request)ois.readObject();
                    switch(respond.getRequestName()){
                        case "login":
                            System.out.println("login");
                            handleLogin(respond);
                            break;
                        case "sendListPlayer":
                            handleListPlayerOnline(respond);
                            break;
                    }   
                }
            }
        } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getUserOnline(){
        Request req = new Request("getListPlayer");
        //System.out.println(req.getRequestName());
        send(req);
        //send(new Request("getListPlayer"));
    }
    public void handleLogin(Request res){
        try{
        if(res.getObject() instanceof User){
            myAccount =(User) res.getObject();
            loginView.dispose();
            game = new Game();
            game.setActionListener(new ListentBtnPlayer());
            game.showMyAccount(myAccount);
            
            getUserOnline();
//            Request res1 = (Request)ois.readObject();
//                    //listPlayer =(Map<String, Pair<User,Integer>>) res.getObject();
//            handleListPlayerOnline(res1);
            //game.showListPlayer(listPlayer);
            //game.addListentBtnPlayer(new ListentBtnPlayer());
        }
        else loginView.showMessage("Đăng nhập thất bại");
        }
        catch(Exception ex){
            
        }
    }
    public class listentBtnLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) { 
            String s = null;
            User user = loginView.getUser();      
            send(new Request("login",(Object) user));
            int i =2;
            while((i--)>0){                   
                try {
                    Request respond = null;
                
                    respond = (Request)ois.readObject();
                
                    switch(respond.getRequestName()){
                        case "login":
                            System.out.println("login");
                            handleLogin(respond);
                            break;
                        case "sendListPlayer":
                            handleListPlayerOnline(respond);
                            break;
                    }   
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            //closeConnection();
        }
    }
    public class updatePlayerOnline extends Thread{
        public updatePlayerOnline(){
            //openConnection(serverHost, serverPort);
        }
        public void run(){
            
            while(true){
                try {
                    sleep(2000);
                    getUserOnline();
                    showListPlayer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //xu ly su kien khi click vao nguoi choi muon thach dau
    public class ListentBtnPlayer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            JButton btn = (JButton) ae.getSource();
            System.out.println(btn.getText());
            //System.out.println("fkegk");
        }
    }
    public void handleListPlayerOnline(Request res){
        listPlayer =(Map<String, Pair<User,Integer>>) res.getObject();
        for(Map.Entry<String, Pair<User, Integer>> p : listPlayer.entrySet()){
            System.out.println(p.getKey()+" "+p.getValue().getKey().getUserName()+" "+p.getValue().getValue()+" "+p.getValue().getKey().getScore());
        }
        game.showListPlayer(listPlayer); 
    }
    public void showListPlayer(){ 
        game.showListPlayer(listPlayer);
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
            System.out.println("close!");
            socketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void openConnection(String serverHost, int port){
        try {
            //System.out.println("helllllo");
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
            oos.flush();
            
            //oos = new ObjectOutputStream(socketClient.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void sendUDP(Request request){
        
    }
   
}
