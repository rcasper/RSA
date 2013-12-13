import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Ryan Kasprzyk
 */
public class RSA {
    static BigInteger p, q, tot, e, d, plain, cipher;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Read test data
        /*BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        p = new BigInteger(in.readLine(), 16);
        q = new BigInteger(in.readLine(), 16);
        e = new BigInteger(in.readLine(), 16);
        plain = new BigInteger(in.readLine(), 16);
        cipher = new BigInteger(in.readLine(), 16);
        
        // Close input reader, open output writer
        in.close();*/
        //BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter p: ");
        p = new BigInteger(scan.next(), 10);
        System.out.print("\nEnter q: ");
        q = new BigInteger(scan.next(), 10);
        System.out.print("\nEnter e: ");
        e = new BigInteger(scan.next(), 10);
        System.out.print("\nEnter plain: ");
        plain = new BigInteger(scan.next(), 10);
        //Generate n
        BigInteger n = p.multiply(q);
        
        // Calculate Euler's totient function phi(n)
        BigInteger tP = p.subtract(new BigInteger("1",10));
        BigInteger tQ = q.subtract(new BigInteger("1",10));
        tot = tP.multiply(tQ);
        
        // Calculate d
        BigInteger d = EEA(e, tot)[0];
        /*out.write(d.toString(16));
        out.newLine();*/
        
        // Encipher test plaintext
        BigInteger tCipher = plain.modPow(e, n);
        System.out.println("\n" + tCipher.toString());
        /*out.write(tCipher.toString(16));
        out.newLine();*/
        
        // Decipher test ciphertext
        //BigInteger tPlain = cipher.modPow(d, n);
        /*out.write(tPlain.toString(16));
        out.newLine();
        out.close();*/
    }
    
    public static BigInteger[] EEA(BigInteger a, BigInteger b) {
        if (b.intValue() == 0) {
            return new BigInteger[]{new BigInteger("1", 10), new BigInteger("0", 10)};
        } else {
            BigInteger q = a.divide(b);
            BigInteger r = a.mod(b);
            BigInteger[] R = EEA(b, r);
            BigInteger i = (R[0].subtract(q.multiply(R[1]))).mod(tot).abs();

            return new BigInteger[]{R[1], i};
        }
    }
}
