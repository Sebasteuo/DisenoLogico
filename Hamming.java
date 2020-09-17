import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Hamming {
	static String p1;
	static String p2;
	static String p3;
	static String p4;
	static String p5;
	
	
	static String[] columnNames = {"","P1","P2","D1","P3","D2","D3","D4","P4","D5","D6","D7","D8","D9","D10","D11","P5","D12"};
	static Object[][] data = {
		    {"Sin paridad", "","", "","", "","", "","", "","", "","", "","", "","",""},
		    {"P1", "","", "","", "","", "","", "","", "","", "","", "","",""},
		    {"P2", "","", "","", "","", "","", "","", "","", "","", "","",""},
		    {"P3", "","", "","", "","", "","", "","", "","", "","", "","",""},
		    {"P4", "","", "","", "","", "","", "","", "","", "","", "","",""},
		    {"P5", "","", "","", "","", "","", "","", "","", "","", "","",""},
		    {"Con paridad", "","", "","", "","", "","", "","", "","", "","", "","",""}
		};
	static JTable table = new JTable(data, columnNames);
	
	static String[] columnNames2 = {"","P1","P2","D1","P3","D2","D3","D4","P4","D5","D6","D7","D8","D9","D10","D11","P5","D12",
			"Prueba","Bit"};
	static Object[][] data2 = {
		    {"Palabra", "","", "","", "","", "","", "","", "","", "","", "","","","",""},
		    {"P1", "","", "","", "","", "","", "","", "","", "","", "","","","",""},
		    {"P2", "","", "","", "","", "","", "","", "","", "","", "","","","",""},
		    {"P3", "","", "","", "","", "","", "","", "","", "","", "","","","",""},
		    {"P4", "","", "","", "","", "","", "","", "","", "","", "","","","",""},
		    {"P5", "","", "","", "","", "","", "","", "","", "","", "","","","",""},
		    
		};
	static JTable table2 = new JTable(data2, columnNames2);
	
    public static void Fila(int numero, String datos, JFrame x ){
    	String cadena;
    	
    	if(datos.length()!= 12) {
    		
    		StringBuilder builder = new StringBuilder(12);

    	       int index = 0;
    	       String  prefix = "0";
    	       while (index < 12-datos.length())
    	       {
    	           builder.append(prefix);
    	          
    	           index += 1;
    	       }
    	       builder.append(datos);
    	       cadena = builder.toString();
    	}else {
    		
    		 cadena =datos;
    	}
    	String fila[] = { "Sin paridad","","", cadena.substring(0, 1),"",cadena.substring(1, 2),
    	cadena.substring(2, 3),cadena.substring(3, 4),"",cadena.substring(4, 5),
    	cadena.substring(5, 6), cadena.substring(6, 7),cadena.substring(7, 8),cadena.substring(8, 9),
    	cadena.substring(9, 10),cadena.substring(10, 11), "",cadena.substring(11, 12)};
    	
    	
    
    	 Hamming.data[0] = fila;
    	 paridad1(cadena,"1");
    	 paridad2(cadena,"1");
    	 paridad3(cadena,"1");
    	 paridad4(cadena,"1");
    	 paridad5(cadena,"1");
    	 aplicarParidad(cadena);
    	 
    	 
    	 

    }
    
    private static void paridad1(String dato, String paridad) {
    
    	char par= paridad.charAt(0);
    	String cadena = dato.substring(0, 1) + dato.substring(1, 2)+dato.substring(3, 4)+dato.substring(4, 5)+
    	dato.substring(6, 7)+dato.substring(8, 9)+dato.substring(10, 11)+dato.substring(11);
    	System.out.print(cadena);
    	
    	if(esPar(contarCaracteres(cadena, par))) {
    		p1 = "0";
    	}else {
    		p1 = "1";	
    	}
    	
    	String fila[] = { "P1",p1,"", cadena.substring(0, 1),"",cadena.substring(1, 2),
    	    	"",cadena.substring(2, 3),"",cadena.substring(3, 4),
    	    	"", cadena.substring(4, 5),"",cadena.substring(5, 6),
    	    	"",cadena.substring(6, 7), "",cadena.substring(7, 8)};
    	    	
    	Hamming.data[1] = fila;
    	
    	
    }
    
    private static void paridad2(String dato, String paridad) {
    	
    	char par= paridad.charAt(0);
    	String cadena = dato.substring(0, 1) + dato.substring(2, 4)+dato.substring(5, 7)+dato.substring(9, 11);
    	System.out.print(cadena);
    	
    	if(esPar(contarCaracteres(cadena, par))) {
    		p2 = "0";
    	}else {
    		p2 = "1";	
    	}
    	
    	String fila[] = { "P2","",p2, cadena.substring(0, 1),"",
    	    	"",cadena.substring(1, 2),cadena.substring(2, 3),"",
    	    	"", cadena.substring(3, 4),cadena.substring(4, 5),"",
    	    	"",cadena.substring(5, 6),cadena.substring(6),"",""};
    	    	
    	Hamming.data[2] = fila;
    	
    	
    }
  private static void paridad3(String dato, String paridad) {
    	
    	char par= paridad.charAt(0);
    	String cadena = dato.substring(1, 4) + dato.substring(7, 11);
    	System.out.print(cadena);
    	
    	if(esPar(contarCaracteres(cadena, par))) {
    		p3 = "0";
    	}else {
    		p3 = "1";	
    	}
    	
    	String fila[] = { "P3","","","",p3, cadena.substring(0, 1),cadena.substring(1, 2),cadena.substring(2, 3),
    	    	"","","","",cadena.substring(3, 4),cadena.substring(4, 5),
    	    	cadena.substring(5, 6),cadena.substring(6),"",""};
    	    	
    	Hamming.data[3] = fila;
    	
    	
    }
  private static void paridad4(String dato, String paridad) {
  	
  	char par= paridad.charAt(0);
  	String cadena = dato.substring(4, 11);
  	System.out.print(cadena);
  	
  	if(esPar(contarCaracteres(cadena, par))) {
  		p4 = "0";
  	}else {
  		p4 = "1";	
  	}
  	
  	String fila[] = { "P4","","","","","","","",p4, cadena.substring(0, 1),cadena.substring(1, 2),
  			cadena.substring(2, 3),cadena.substring(3, 4),cadena.substring(4, 5),
  	    	cadena.substring(5, 6),cadena.substring(6),"",""};
  	    	
  	Hamming.data[4] = fila;
  	
  	
  }
  
  private static void paridad5(String dato, String paridad) {
	    
  	char par= paridad.charAt(0);
  	String cadena = dato.substring(11);
  	System.out.print(cadena);
  	
  	if(esPar(contarCaracteres(cadena, par))) {
  		p5 = "0";
  	}else {
  		p5 = "1";	
  	}
  	
  	String fila[] = { "P5","","", "","","","","","", "","","","","","","",p5,cadena.substring(0)};
  	    	
  	Hamming.data[5] = fila;
  	
  	
  }
  
    public static int contarCaracteres(String cadena, char caracter) {
        int posicion, contador = 0;
        //se busca la primera vez que aparece
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) { //mientras se encuentre el caracter
            contador++;           //se cuenta
            //se sigue buscando a partir de la posición siguiente a la encontrada                                 
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
}
    static boolean esPar(int numero){
        if (numero%2==0) return true; else return false;
    }
    
    static void aplicarParidad(String cadena) {
    	String fila[] = { "Con paridad",p1,p2, cadena.substring(0, 1),p3,cadena.substring(1, 2),
    	    	cadena.substring(2, 3),cadena.substring(3, 4),p4,cadena.substring(4, 5),
    	    	cadena.substring(5, 6), cadena.substring(6, 7),cadena.substring(7, 8),cadena.substring(8, 9),
    	    	cadena.substring(9, 10),cadena.substring(10, 11), p5,cadena.substring(11, 12)};
    	    	
    	Hamming.data[6] = fila;
    }
    

}
