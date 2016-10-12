
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author Pipjak
 */

public class KnuthMorrisPratt {
    
    /* fields */
    
    // globaly held text
    private String text;
    
    // globaly held pattern
    private String pattern;
   
    // length
    private int len;
    
    // prefix function
    private int[] s;
    
    // result held here
    private List<Integer> result;
    
    /* constructor */
    
    // defult constructor
    KnuthMorrisPratt(){}
    
    // custom constructor
    KnuthMorrisPratt(String text, String pattern){
        
        // put text into the global
        this.text = text;
        
        // put pattern into the global
        this.pattern = pattern;
        
        // doing this conglomerate is probably inefective:
        // point : |pattern| => indices_pattern 0,..., pattern.length() - 1 and
        // |$| => indices_$ = 0; and |text| => i = 0, ... text.length() - 1 
        // so conglo is gonna be represented by indConglo function:
        // this.conglo = bld.append(pattern).append("$").append(text).toString();
        
        // the one there is for the "DOOR" symbol, in our case its gonna be "$":
        this.len = this.pattern.length() + 1 + this.text.length();
        
        this.s = new int[this.len];
        
        this.result = new ArrayList(this.len);
        
    }
    
    private char charConglo(int i){
        
        char res;
        
        if(i < this.pattern.length() && 0 <= i ){
        
            res = this.pattern.charAt(i);
        }
        else if( i== this.pattern.length() ){
            
            res = '$';
        }
        else{
            
            res = this.text.charAt(i- this.pattern.length() - 1); 
        }
        
        return res;
    }
    
    private void prefixFunction(){
        
        int border = 0;
        
        //By default the zero-th element of the prefix function is 0
        s[0] = 0;
        
        for(int i = 1; i < this.len; i++){
            
            // looping till border>0 and ...
            while(border > 0 && this.charConglo(i) != this.charConglo(border)){
                
                border = s[border - 1];
            }
            
            
            if(this.charConglo(border) == this.charConglo(i)){
                border = border +1;}
            else{
                border = 0;
            }
            
        s[i] = border;
        }
    }
    
    
    private void findAllOccurrences(){
    
        int c = 2*this.pattern.length();
        
        
        this.prefixFunction();
        
        
        for(int i = this.pattern.length()+1; i < this.len  ; i++ ){
            
            if( s[i] == this.pattern.length() ){
                
                this.result.add(i - c);
            }
        }
    }
    
    
    
   
    
   // fast scanner class
   class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    // Find all the occurrences of the pattern in the text and return
    // a list of all positions in the text (starting from 0) where
    // the pattern starts in the text.
   
    public void findPattern() {
        
       this.findAllOccurrences();
    }

    static public void main(String[] args) throws IOException {
        new KnuthMorrisPratt().run();
    }

    public void print(List<Integer> x) {
        
        
        for(int i = 0; i<x.size();i++){
            System.out.print(x.get(i) + " ");
        }
        
        /*
        x.stream().forEach((a) -> {
            System.out.print(a + " ");
        });
       */
        System.out.println();
    }

    public void run() throws IOException {
        
        FastScanner scanner = new FastScanner();
        
        String patTern = scanner.next();
        String teXt = scanner.next();
        
        KnuthMorrisPratt g = new KnuthMorrisPratt( teXt, patTern );
        
        g.findPattern();
        print(g.result);
    } 
}
