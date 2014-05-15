/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commxbee;

/**
 *
 * @author thibeaua
 */
public class TrameXBee {
    private char dest;
    private char src;
    private char code;
    private char m1;
    private char m2;

    public TrameXBee(char dest, char src, char code, char m1, char m2) {
        this.dest = dest;
        this.src = src;
        this.code = code;
        this.m1 = m1;
        this.m2 = m2;
    }

    public char getDest() {
        return dest;
    }

    public char getSrc() {
        return src;
    }

    public char getCode() {
        return code;
    }

    public char getM1() {
        return m1;
    }

    public char getM2() {
        return m2;
    }
    
    public char[] getTrame(){
        char[] result = new char[5];
        result[0] = dest;
        result[1] = src;
        result[2] = code;
        result[3] = m1;
        result[4] = m2;
        return result;
    }
    

    @Override
    public String toString(){
        String result = "trame : " + dest + " "+src +" "+code+" "+m1+" "+m2;
        return(result);
    }
}
