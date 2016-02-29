

        import java.util.LinkedList;
        import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner com = new Scanner(System.in);
        while(true) {
            System.out.println("Enter data:");
            String s = com.nextLine();
            if(s.equals("Close")) {System.out.println(s); break;}
            eval(s);
        }
    }

    public static boolean isDelim(char c) {
        return c == ' ';
    }
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%'|| c == '^';
    }

    public static int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    public static void processOperator(LinkedList<Double> st, char op) {
        double r = st.removeLast();
        double l = st.removeLast();
        switch (op) {
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
            case '%':
                st.add(l % r);
                break;
            case '^':
                st.add(Math.pow(l,r));
                break;
        }
    }

    public static void eval(String s) {
        LinkedList<Double> st = new LinkedList<Double>();
        LinkedList<Character> op = new LinkedList<Character>();
        boolean isError = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!isDelim(c) && c != '(' &&  c != ')' && !Character.isDigit(c) && !isOperator(c)) {
                isError = false;
                break;
            }
            if (isDelim(c))
                continue;
            if (c == '(') {
                op.add('(');
            }
            else if (c == ')') {
                while (op.getLast() != '(')
                    processOperator(st,op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                    processOperator(st, op.removeLast());
                op.add(c);
            } else {
                String operand = "";
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                    operand += s.charAt(i++);
                }
                --i;
                st.add(Double.parseDouble(operand));
            }
        }

        if(isError) {
            while (!op.isEmpty()) {
                processOperator(st, op.removeLast());
            }
            System.out.println(st.get(0));
        } else {
            System.out.println("Error");
        }
    }
}

