package com.nanotechnologies.jfxtest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.controlsfx.control.action.Action;

import javax.script.*;
import java.util.Stack;

public class Controller {
    protected int number;
    @FXML
    public TextField Display;
    @FXML
    public Button buttonONE;
    public Button buttonTWO;
    public Button buttonTHREE;
    public Button buttonFOUR;
    public Button buttonFIVE;
    public Button buttonSIX;
    public Button buttonSEVEN;
    public Button buttonEIGHT;
    public Button buttonNINE;
    public Button buttonO;
    @FXML
    public Button buttonMINUS;
    public Button buttonPLUS;


    // repetitive functions worth defining
    void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Ok");
            }
        });
    }

    void addText(String text) {
        Display.setText(Display.getText() + text);
    }

    public static int evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Integer> values = new
                Stack<>();

        Stack<Character> ops = new
                Stack<>();

        for (int i = 0; i < tokens.length; i++)  {
            if (tokens[i] == ' ')
                continue;

            if (tokens[i] >= '0' &&
                    tokens[i] <= '9') {
                StringBuffer sbuf = new
                        StringBuffer();

                while (i < tokens.length &&
                        tokens[i] >= '0' &&
                        tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.
                        toString()));

                i--;
            }

            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));
                ops.pop();
            }
            // Current token is an operator.
            else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' ||
                    tokens[i] == '/')  {

                while (!ops.empty() &&
                        hasPrecedence(tokens[i],
                                ops.peek()))
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }
        while (!ops.empty())
            values.push(applyOp(ops.pop(),
                    values.pop(),
                    values.pop()));
        return values.pop();
    }

    public static boolean hasPrecedence(
            char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 != '*' && op1 != '/') ||
                (op2 != '+' && op2 != '-');
    }

    // A utility method to apply an
    // operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static int applyOp(char op,
                              int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    // gui function definitions

    @FXML
    private void reset(ActionEvent x) {
        Display.setText("");
    }
    @FXML
    private void backspace(ActionEvent x) {

        if (!Display.getText().isEmpty() || !Display.getText().isBlank()) {
            Display.setText(Display.getText(0, Display.getLength()-1));
        }
//        System.out.println(Display.getText().toCharArray()[Display.getLength()-1]);
    }
    @FXML
    private void onPlusClick(ActionEvent x) {
        if (Display.getText().isBlank() || Display.getText().isEmpty()) {
            Display.setText("");
        } else if (Display.getText().endsWith("+") || Display.getText().endsWith("-")) {
            showMessage("Error", "Cannot add symbol to \"+\"");
        } else {
            Display.setText(Display.getText() + "+");
        }
    }
    @FXML
    private void onMinusClick(ActionEvent x) {
        if (Display.getText().isBlank() || Display.getText().isEmpty()) {
            Display.setText("");
        } else if (Display.getText().endsWith("+") || Display.getText().endsWith("-")) {
            showMessage("Error", "Cannot add symbol to \"-\"");
        } else {
            Display.setText(Display.getText() + "-");
        }
    }

    @FXML
    private void calculate(ActionEvent x) throws ScriptException {
        if (Display.getText().endsWith("+") || Display.getText().endsWith("-")) {
            showMessage("Math Error", "You cannot end the expression with an arithmetic symbol");
        } else {
            String result = String.valueOf(evaluate(Display.getText()));
            Display.setText(result);
        }
    }
    @FXML
    private void onnumberclick(ActionEvent x) {
        if (x.getSource() == buttonONE) { addText("1");
        } else if (x.getSource() == buttonTWO) { addText("2");
        } else if (x.getSource() == buttonTHREE) { addText("3");
        } else if (x.getSource() == buttonFOUR) { addText("4");
        } else if (x.getSource() == buttonFIVE) { addText("5");
        } else if (x.getSource() == buttonSIX) { addText("6");
        } else if (x.getSource() == buttonSEVEN) { addText("7");
        } else if (x.getSource() == buttonEIGHT) { addText("8");
        } else if (x.getSource() == buttonNINE) { addText("9");
        }  else if (x.getSource() == buttonO) { addText("0");
        }
    }
}