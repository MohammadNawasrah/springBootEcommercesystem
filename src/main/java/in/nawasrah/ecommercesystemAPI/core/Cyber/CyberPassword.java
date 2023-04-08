package in.nawasrah.ecommercesystemAPI.core.Cyber;

public class CyberPassword {
    private int key = 4;

    public String encryption(String pass) {
        return presses(pass, 1);
    }

    public String decryption(String pass) {

        return presses(pass, 0);
    }

    String presses(String pass, int xx) {
        char[] textB = pass.toCharArray();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (char x : textB) {
            if (xx == 0)
                x -= key;
            else
                x += key;
            textB[counter] = x;
            counter++;
        }
        String str = String.valueOf(textB);
        return str;
    }
}
