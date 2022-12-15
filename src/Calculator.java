import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Calculator implements ActionListener {

    JFrame frame;
    TextField textfield;
    Button[] numberButtons = new Button[10];
    Button[] functionButtons = new Button[8];
    Button addButton, subButton, mulButton, divButton;
    Button decButton, equButton, delButton, clrButton;
    Panel panel;

    Font myFont = new Font("Times New Roman", Font.BOLD, 30);
    Font smFont = new Font("Times New Roman", Font.BOLD, 17);

    int num1=0, num2=0, result=0;
    char operator;
    boolean clearText = false;

    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new TextField("0");
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("x");
        divButton = new Button("÷");
        decButton = new Button("CS242");       //For possible future impementation of doubles
        equButton = new Button("=");
        delButton = new Button("Delete");
        clrButton = new Button("Clear");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for(int i=0; i<8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for(int i=0; i<10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(205, 430, 145, 50);
        decButton.setFont(smFont);

        panel = new Panel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);


        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);

        //textfield.setText("0");
    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(clearText){textfield.setText("");}
        for (int i=0; i<10; i++) {
            if (e.getSource() == numberButtons[i]) {
                clearText = false;
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textfield.setText("");
        }
        if (e.getSource() == addButton) {
            num1 = Integer.parseInt(textfield.getText());
            //operator = '+';
            textfield.setText(num1 + "+");
        }
        if (e.getSource() == subButton) {
            num1 = Integer.parseInt(textfield.getText());
            //operator = '-';
            textfield.setText(num1 + "-");
        }
        if (e.getSource() == mulButton) {
            num1 = Integer.parseInt(textfield.getText());
            //operator = 'x';
            textfield.setText(num1 + "x");
        }
        if (e.getSource() == divButton) {
            num1 = Integer.parseInt(textfield.getText());
            //operator = '÷';
            textfield.setText(num1 + "÷");  //num1 + "/"
        }
        if(e.getSource() == equButton) {

            parser(textfield.getText());

            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case 'x' -> result = num1 * num2;
                case '÷' -> result = num1 / num2;
            }

            textfield.setText(num1 + "" + operator + "" + num2 + "=" + result);  //String.valueOf(result)num1 + operator + num2 + "=" + result
            clearText = true;
        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for (int i=0; i<string.length()-1; i++) {
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }

    }
    public void parser(String str) {
        char[] ch = new char[str.length()];
        String strNum1 = "";
        String strNum2 = "";
        int temp;
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        int j;
        for (j = 0; j < ch.length; j++) {
            if ((ch[j] == 'x') || (ch[j] == '÷') || (ch[j] == '+') || (ch[j] == '-')) {
                operator = ch[j];
                break;
            } else {
                strNum1 = strNum1 + ch[j];
            }
        }
        for (temp = j+1; temp < ch.length; temp++) {
            strNum2 = strNum2 + ch[temp];
        }
        num1 = Integer.parseInt(strNum1);
        num2 = Integer.parseInt(strNum2);
    }
}


