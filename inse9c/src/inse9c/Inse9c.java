package inse9c;

public class Inse9c {

    public static void main(String[] args) {
        LoginUI l = new LoginUI();
        l.setVisible(true);
        DAO.connect();
        double n = 18/30;
        System.out.println(n);
    }

}
