package cn.cgt;

import cn.uti.variate.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Carculator extends JFrame implements ActionListener {
    /***************north botton****************/
    private JPanel jp_north = new JPanel();
    private JTextField input_text = new JTextField();
    private JButton c_Btn = new JButton("AC");
    /***************center botton****************/
    private JPanel jp_center = new JPanel();

    public Carculator() throws HeadlessException{
        this.init_windows();
        this.init_north_botton();
        this.addCenterBottom();
    }

    public void init_windows(){
        this.setTitle(Const.TITLE);
        this.setSize(Const.FRAME_W, Const.FRAME_H);
        this.setLayout(new BorderLayout());
        this.setResizable(false);//fix the windows
        this.setLocation(Const.FRAME_X, Const.FRAME_Y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.input_text.setPreferredSize(new Dimension(230 ,30));

    }

    public void init_north_botton(){
        jp_north.add(input_text);//AC botton
        jp_north.add(c_Btn);
        this.c_Btn.setForeground(Color.RED);
        c_Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_text.setText("");
            }
        });
        this.add(jp_north,BorderLayout.NORTH);
    }

    public void addCenterBottom(){
        String tmp1 = null;
        String tmp2 = null;
        String btn_text_1 = "1234567890";
        String btn_text_2 = "*+.-/=";
        String btn_text_3 = "logtansincos";
        String regex = "[\\+\\-*/.=]";
        this.jp_center.setLayout(new GridLayout(5,5));//grid layout

        for(int i = 0; i < 10; i++){
            tmp1 = btn_text_1.substring(i, i + 1);
            JButton btn = new JButton();
            btn.setText(tmp1);
            btn.addActionListener(this);
            jp_center.add(btn);
        }
        for(int j = 0; j < 6; j++){
            tmp2 = btn_text_2.substring(j, j + 1);
            JButton btn = new JButton();
            btn.setText(tmp2);
            btn.addActionListener(this);
            jp_center.add(btn);
            if(tmp2.matches(regex)) {//set typeface of the operator
                btn.setFont(new Font("粗体", Font.BOLD, 20));
            }
        }
        for(int extra_btn_num = 0; extra_btn_num < 12; extra_btn_num+=3) {//sincon
            String tmp3 = btn_text_3.substring(extra_btn_num, extra_btn_num + 3);
            JButton btn = new JButton();
            btn.setText(tmp3);
            btn.addActionListener(this);
            jp_center.add(btn);
        }
        //pow function add
        JButton btn = new JButton();
        btn.setText("^");
        btn.addActionListener(this);
        jp_center.add(btn);


        this.add(jp_center, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        Carculator cal = new Carculator();
        cal.setVisible(true);
    }

    private String firstInput = null;
    private String operator = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        String clickStr = e.getActionCommand();
        /******************show the numbers************************/
        if(".0123456789".indexOf(clickStr) != -1){      //ignore the opertator
            this.input_text.setText(input_text.getText() + clickStr);
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);
        }else if (clickStr.matches("[\\+\\-*/^]{1}")) {
            operator = clickStr;
            firstInput = this.input_text.getText();
            this.input_text.setText("");
        }else if(clickStr.equals("log")){//press log
            operator = clickStr;
            firstInput = this.input_text.getText();
            this.input_text.setText("");
        }else if(   clickStr.equals("sin") ||
                    clickStr.equals("cos") ||
                    clickStr.equals("tan")  ) {
            operator = clickStr;
            firstInput = this.input_text.getText();
        }

        else if(clickStr.equals("=")){
            Double firstNum = Double.valueOf(firstInput);
            Double secondNum = Double.valueOf(this.input_text.getText());
            Double result = null;
            switch (operator){
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "*":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    if(secondNum != 0) {
                        result = firstNum / secondNum;
                    }else {
                        this.input_text.setText("error");
                    }
                    break;
                case "log":
                    if(secondNum != 0) {
                        result = Math.log(firstNum) / Math.log(secondNum);
                    }else {
                        this.input_text.setText("error");
                    }
                    break;
                case "^":
                    result = Math.pow(firstNum,secondNum);
                    break;
                case "sin"://只需要一个参数时 输出结果
                    result = Math.sin(firstNum);
                    break;
                case "cos":
                    result = Math.cos(firstNum);
                    break;
                case "tan":
                    result = Math.tan(firstNum);
                    break;

            }
            this.input_text.setText(result.toString());
        }

    }
}
