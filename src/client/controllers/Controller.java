/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Bug khai bao ois trước oos lỗi
package client.controllers;
import Models.com.Pair;
import Models.com.*;
import Models.com.Question;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    private int cur = 0;
    
    public Controller(){
        loginView = new LoginView();
        loginView.setVisible(true);
        loginView.addListentBtnLogin(new listentBtnLogin());
        openConnection(serverHost, serverPort);
        Thread threadUpdate = new Thread(new updatePlayerOnline());
        threadUpdate.setDaemon(true);
        threadUpdate.start();
    }
    public synchronized Map<String, Pair<User, Integer>> getlistPlayer(){
        return this.listPlayer;
    }
    public void run(){
        while(true){
            try {
                Request respond =(Request)ois.readObject();
                if(respond.getObject() == null) continue;
                switch(respond.getRequestName()){
                    //Nhận kết quả login trả về từ server
                    case "login":
                        handleLogin(respond);
                        break;
                    //Nhận danh sách người dùng từ server trả về
                    case "sendListPlayer":
                        handleListPlayerOnline(respond);
                        break; 
                    //Nhận lời thách đấu
                    case "challange":
                        //System.out.println("da ----nhan loi moi thach dau tu user 1");
                        acceptInvite(respond);
                        break;
                    //Nhận từ chối thách đấu của đối thủ
                    case "refuse":
                        handleRefuse(respond);
                        break;
                    //Nhận danh sách câu hỏi
                    case "sendListQuestion":
                        showQuestion(respond);
                        break;
                }
            } catch (IOException ex) {
                //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    class Timer {
        public int time;
        public boolean stop =false;
        public Timer(int t){
            this.time = t;
        }
    }
    // Nhận được danh sách câu hỏi, tạo cửa sổ để làm
    public void showQuestion(Request res){
        try {
            //Đối thủ
            User u = (User) res.getObject2();
            System.out.println(u.getUserName());
            VS vs = new VS(myAccount.getUserName(), u.getUserName());
            vs.setVisible(true);
            Thread.sleep(3000);
            vs.dispose();
            int AnsTime = 0;
            Timer time = new Timer(30);
            ArrayList<Question> listQuestion = (ArrayList<Question>)res.getObject();
            QuestionsForm questionF = new QuestionsForm();
            questionF.addActionListentBtnNextOrExit(new ListenBtnNext(listQuestion, questionF,time, u));
            questionF.setVisible(true);
            questionF.setNumAns(cur+1);
            questionF.showQuestionI(listQuestion.get(cur), cur);
            // Đếm giờ làm bài
            
            while((--time.time)>=0 && time.stop == false){
                Thread.sleep(1000);
                AnsTime = time.time;
                questionF.setTime(time.time);
            }
            System.out.println("time la: "+ (30-AnsTime));
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void handlePlay(ArrayList<Question> questions, QuestionsForm f){
        if(cur <10){
            f.setNumAns(cur+1);
            f.showQuestionI(questions.get(this.cur),cur);
        }
    }
    public void submitAns(QuestionsForm f, User user,int time,ArrayList<Question> questions){
        int[] ans  = f.getAns();
        PostSubmitted submit = new PostSubmitted(user, ans, time,questions);
        Request req = new Request("submit",(Object) submit);// câu trả lời- đối thủ - questions 
        send(req);
    }
    public void handleAns(int[] listAns){
        for(int i =0;i <listAns.length;i++){
            System.out.println(listAns[i]);
        }
    }
    class ListenBtnNext implements MouseListener{
        private ArrayList<Question> questions;
        private QuestionsForm f;
        private Timer time;
        private User user;
        public ListenBtnNext(ArrayList<Question> q , QuestionsForm f, Timer time, User u){
            this.f = f;
            this.questions = questions;
            this.questions = q;
            this.time = time;
            this.user = u;
        }
        @Override
        public void mouseClicked(MouseEvent me) {
            JLabel btn = (JLabel) me.getComponent();
            if(btn.getName().equals("next")){
                this.f.resetAllTick();
                cur++;
                if(cur<10){
                    handlePlay(this.questions,this.f);
                    if(cur==9){
                        f.setBtnSubmit();
                        f.setBtnNext("Nộp bài");
                    }
                }
                else{
                    //Khi đã trả lời đủ 10 câu hỏi, nhận kết câu trả lời của người chơi gửi lên server
                    this.time.stop = true;
                    cur=0;
                    submitAns(f,this.user, 30 - this.time.time, this.questions);
                    f.dispose();
                }
            }
            //Khi đang làm bài mà thoát, vẫn gửi kết quả những câu đã làm được lên server
            else{
                this.time.stop = true;
                cur=0;
                submitAns(f,this.user,30 - this.time.time,this.questions);
                f.dispose();
            }
        // * Phai reset var cur khi ket thuc game
        }
        @Override
        public void mousePressed(MouseEvent me) {}
        @Override
        public void mouseReleased(MouseEvent me) {}
        @Override
        public void mouseEntered(MouseEvent me) {}
        @Override
        public void mouseExited(MouseEvent me) {}
        
    }
    // Nhận lời mời thách đấu từ người chơi khác 
    public void acceptInvite(Request res){
        User user = (User) res.getObject();
        //System.out.println(user.getUserName());
       
        //System.out.println("User co ip "+ip+" muon thach dau");
        showMessage(user.getUserName()+" muốn thách đấu bạn, chiến?" , "accept",user.getIp());
        
    }
    // gửi yêu cầu lấy người dùng đang online 
    public void getUserOnline(){
        
        Request req = new Request("getListPlayer");
        send(req);
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
        }
    }
    public class updatePlayerOnline implements Runnable{
        public updatePlayerOnline(){
            //openConnection(serverHost, serverPort);
        }
        public void run(){
            
            while(true){
                try {
                    sleep(10000);
                    getUserOnline();
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
            JButton btn = (JButton) ae.getSource();
            
            String[] sp = btn.getText().split(" ");
            System.out.println(myAccount.getUserName());
            String[] str = sp[1].split(":");
            String ip = str[1];
            System.out.println(ip);
            if(myAccount.getUserName().equals(sp[0])){
                showMessage("Bạn không thể thách đấu với chính mình", "close",null);
            }
            else if(btn.getName().equals("2")){
                showMessage("Người chơi đang trong trận","close", null);
            }
            else showMessage("Bạn có muốn gửi lời thách đấu tới "+ sp[0],"challange",ip);
            //System.out.println(btn.getText());
        }
    }
    // Hiện khung thông báo 
    public void showMessage(String content, String action, String ip){
        MessageDialog m = new MessageDialog(content);
        m.addListenerBtnCancel(new ListenActionCancel(m,action,ip));
        m.addListentBtnOk(new ListenActionOk(m,action,ip));
        m.setVisible(true);
    }
    // Xử lý sự kiện khi chọn OK trong khung thông báo 
    public class ListenActionOk implements ActionListener{
        private MessageDialog m ;
        private String action;
        private String ip;
        private User user;
        public ListenActionOk(MessageDialog m, String action, String ip){
            this.m = m;
            this.action = action;
            this.ip = ip;
        }
        public ListenActionOk(MessageDialog m, String action, User user){
            this.m = m;
            this.user = user;
            this.action = action;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            switch(this.action){
                case "close":
                    this.m.dispose();
                    break;
                case "challange":
                    sendMatch(this.ip);
                    this.m.dispose();
                    break;
                case "accept":
                    //System.out.println("chap nhan dau voi "+this.user.getUserName());
                    acceptChellange(ip);
                    this.m.dispose();
                    break;
                case "refuse":
                    this.m.dispose();
                    break;
            }
        }
    }
    // chấp nhận lời thách đấu, gửi thông tin ip người thách đấu lên server;
    public void acceptChellange(String ip){
        Request req = new Request("acceptChallange",(Object)ip);
        send(req);
        
    }
    // đối thủ từ chối lời mời
    public void handleRefuse(Request res){
        String userName = (String) res.getObject();
        showMessage(userName+" đã từ chối, chắc do sợ bạn đó:)", "refuse", userName);
    }
    // gửi lời mời thách đấu
    public void sendMatch(String ip){
        Request req = new Request("match", (Object)ip);
        send(req);
    }
    // lắng nghe sự kiện hủy bỏ trong showmessage
    public class ListenActionCancel implements ActionListener{
        private MessageDialog m ;
        private String action;
        private String ip;
        public ListenActionCancel(MessageDialog m, String action, String ip){
            this.m = m;
            this.action = action;
            this.ip = ip;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            if(this.action.equals("accept")){
                sendRefuse(ip);
            }
            m.dispose();
        }   
    }
    // nhận list người chơi online gửi từ server và hiển thị
    public void handleListPlayerOnline(Request res){
        try{
            if(res.getObject() == null) return;
            Map<String, Pair<User, Integer>> listPlayer=getlistPlayer();
            listPlayer =(Map<String, Pair<User,Integer>>) res.getObject();
//            for(Map.Entry<String, Pair<User, Integer>> p : listPlayer.entrySet()){
//                System.out.println(p.getKey()+" "+p.getValue().getKey().getUserName()+" "+p.getValue().getValue()+" "+p.getValue().getKey().getScore());
//            }
            game.showListPlayer(listPlayer); 
            
        }
        catch(Exception ex){
            return;
        }
    }
    //tu choi yeu cau thach dau
    public void sendRefuse(String ip){
        Request req = new Request("refuse",(Object)ip);
        send(req);
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
        try {
            oos.writeObject(request);
            //oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
