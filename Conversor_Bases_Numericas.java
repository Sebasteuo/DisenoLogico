import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.x500.X500Principal;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import java.awt.geom.Line2D;

public class Conversor_Bases_Numericas {
    private static final String[] chars = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    public static void main(String[] args) {
    	
        String[] valoresBases = {"2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
        
        //ACA GENERAMOS LOS ESPACIOS EN BLANCO
        final JTextField numeroDeUsuario = new JTextField(22);
        final JTextField numeroEnBinario = new JTextField(22);   numeroEnBinario.setEditable(false);
        final JTextField numeroEnOctal = new JTextField(22);   numeroEnOctal.setEditable(false);
        final JTextField numeroEnDecimal = new JTextField(22);   numeroEnDecimal.setEditable(false);
        final JTextField hileraEnBinario = new JTextField(22);   hileraEnBinario.setEditable(false);
        final JTextField bitDeFalla = new JTextField(22);   hileraEnBinario.setEditable(false);
        final JTextField errorHamming = new JTextField(22);   errorHamming.setEditable(false);
        
        final JComboBox base1 = new JComboBox(valoresBases); base1.setSelectedIndex(14); //Ac√° se selecciona que sea Hexadecimal directo
        final JComboBox base2 = new JComboBox(valoresBases);
        
        JButton boton = new JButton("Convertir");
        
        JFrame x = new JFrame("TAREA DISENO LOGICO");
        x.setLayout(new GridBagLayout());       
        
        
        //ACA SE GENERAN LOS TEXTOS EN PANTALLA
        x.add(new JLabel("Numero "), new GridBagConstraints(0,0,1,1,0,0,21,0,new Insets(4,4,2,2),0,0));
        x.add(new JLabel("Binario "), new GridBagConstraints(0,4,1,1,0,0,21,0,new Insets(4,4,2,2),0,0));
        x.add(new JLabel("Octal "), new GridBagConstraints(0,6,1,1,0,0,21,0,new Insets(4,4,2,2),0,0));
        x.add(new JLabel("Decimal "), new GridBagConstraints(0,7,1,1,0,0,21,0,new Insets(2,4,2,2),0,0));
        x.add(new JLabel("Binario con error"), new GridBagConstraints(0,8,1,1,0,0,21,0,new Insets(2,4,2,2),0,0));
        x.add(new JLabel("Paridad "), new GridBagConstraints(0,9,1,1,0,0,21,0,new Insets(2,4,2,2),0,0));
        x.add(new JLabel("Bit a cambiar "), new GridBagConstraints(0,1,1,1,0,0,21,0,new Insets(2,4,2,2),0,0));
        x.add(new JLabel("Bit de error con Hamming "), new GridBagConstraints(0,17,1,1,0,0,21,0,new Insets(4,4,2,2),0,0));
        x.add(numeroDeUsuario,new GridBagConstraints(1,0,3,1,0,0,21,2,new Insets(4,2,2,4),0,0));
    
        //ACA POSICIONAMOS LAS COSAS
        x.add(boton,  new GridBagConstraints(3,2,1,1,0,0,10,0,new Insets(2,4,4,2),0,0));
        x.add(numeroEnBinario,new GridBagConstraints(3,4,3,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(numeroEnOctal,new GridBagConstraints(3,6,6,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(numeroEnDecimal,new GridBagConstraints(3,7,5,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(hileraEnBinario,new GridBagConstraints(3,8,5,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(bitDeFalla,new GridBagConstraints(3,1,5,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(errorHamming,new GridBagConstraints(3,17,5,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        
        Choice ParidadChooser = new Choice();
        ParidadChooser.add("Par");
        ParidadChooser.add("Impar");
        x.add(ParidadChooser, new GridBagConstraints(3,9,1,1,0,0,21,0,new Insets(4,4,2,2),0,0));
        
        Hamming table = new Hamming();
        
        TableColumn column = null;
        for (int i = 0; i < 18; i++) {
            column = table.table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75); //first column is bigger
            } else {
                column.setPreferredWidth(25);
            }
        }
        
        
        for (int i = 0; i < 20; i++) {
            column = table.table2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(50); //first column is bigger
            } else if(i == 19){
            	column.setPreferredWidth(30);
            }else if( i == 18){
            	column.setPreferredWidth(50);
            }else
            { 
                column.setPreferredWidth(25);
            }
        }
        
        x.add(new JLabel("C·lculo de los bits de paridad en el cÛdigo Hamming"), new GridBagConstraints(10,0,1,1,0,0,10,0,new Insets(0,0,0,0),0,0));
        x.add(table.table.getTableHeader(),new GridBagConstraints(10,1,1,1,0,0,10,0,new Insets(0,0,4,4),0,0));
        x.add(table.table,new GridBagConstraints(10,2,1,6,0,0,10,0,new Insets(0,0,4,4),0,0));
        
        
        
        x.add(new JLabel("ComprobaciÛn de los bits de paridad"), new GridBagConstraints(10,8,1,1,0,0,10,0,new Insets(0,0,0,0),0,0));
        x.add(table.table2.getTableHeader(),new GridBagConstraints(10,9,1,1,0,0,10,0,new Insets(0,0,8,8),0,0));
        x.add(table.table2,new GridBagConstraints(10,10,1,8,0,0,10,0,new Insets(0,0,8,8),0,0));

     
        
        x.setResizable(false);
        x.pack();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setLocationRelativeTo(null);
        
        boton.addActionListener(new ActionListener(){

			@Override public void actionPerformed(ActionEvent e) {
            	numeroDeUsuario.setText(numeroDeUsuario.getText().toUpperCase());
                String numeroEditable = numeroDeUsuario.getText();
                bitDeFalla.setText(bitDeFalla.getText().toUpperCase());
                int fallo = Integer.parseInt(bitDeFalla.getText());
                if(ComprobarNumero(numeroEditable, base1.getSelectedIndex()+2)){
                    int cont = 0;
                    if (numeroEditable.contains(".")){
                        cont = (-1)*(numeroEditable.substring(numeroEditable.indexOf(".")+1).length());
                    }
                    numeroEditable = transformarBaseADecimal(numeroEditable, base1.getSelectedIndex()+2,cont);
                    
                    //COMPROBACION DE QUE SEA MENOS DE 3 DIGITOS
                    if (Double.parseDouble(numeroEditable)>4095){
                    	numeroEnBinario.setText("ERROR: (Max. FFF), ingrese otro ");
                    	numeroEnOctal.setText("ERROR: (Max. FFF), ingrese otro ");
                    	numeroEnDecimal.setText("ERROR: (Max. FFF), ingrese otro ");
                        return;
                    } else if (Double.parseDouble(numeroEditable)<=-10000000){
                    	numeroEnBinario.setText("ERROR: Numero peque√±o (Max. -9999999)");
                        return;
                    }
                    if(numeroEditable.endsWith(".0")) numeroEditable = numeroEditable.replace(".0", "");
                    numeroEnBinario.setText( transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+2) );
                    numeroEnOctal.setText( transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+8) );
                    numeroEnDecimal.setText( transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+10) );
                    Hamming.Fila(1, transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+2),x,
                    		ParidadChooser.getItem(ParidadChooser.getSelectedIndex()));
                    x.repaint();
                    
                    //ACA SE LLAMA LA FUNCI”N QUE DIBUJA EL NRZI
                    drawNRZI(numeroEditable, base2);
                    
                    //COMPROBACION DEL FORMATO DEL NUMERO
                } else if(numeroEditable.contains(",")){
                	numeroEnBinario.setText("ERROR: Formato Invalido");
                	numeroEnOctal.setText("ERROR: Formato Invalido");
                	numeroEnDecimal.setText("ERROR: Formato Invalido");
                } else if (numeroEditable.isEmpty()){
                	numeroEnBinario.setText("Ingrese un numero");
                	numeroEnOctal.setText("Ingrese un numero");
                	numeroEnDecimal.setText("Ingrese un numero");
                } else {
                	numeroEnBinario.setText("ERROR: Numero no valido, ingrese otro");
                	numeroEnOctal.setText("ERROR: Numero no valido, ingrese otro");
                	numeroEnDecimal.setText("ERROR: Numero no valido, ingrese otro");
                }
                
                String paridad1 = table.p1 + table.p2 + table.p3 +table.p4 + table.p5;
                String dataChanged = dataError(fallo, transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+2));
                
                //AQUI SE HACE LA L”GICA DE LA TABLA 2 EN LA TABLA 1
                hileraEnBinario.setText(dataChanged);
                Hamming.Fila(1, dataChanged,x,ParidadChooser.getItem(ParidadChooser.getSelectedIndex()));
                String paridad2 = table.p1 + table.p2 + table.p3 +table.p4 + table.p5;
                String bitFallado = bitFallo(paridad1,paridad2);
                errorHamming.setText(binaryToDecimal(bitFallado));
            }
        });

        x.setVisible(true);
    }

    
    public static String dataError(int error, String data) {
    	String bitToChange = data.substring(error-1, error);
    	String change;
    	String result;
    	if(bitToChange.equals("1")) {
    		change = "0";
    	}else {
    		change = "1";
    	}
    	if(error==1) {
    		result = change + data.substring(1);
    	}else if(error == 12){
    		result = data.substring(0, 11) + change;
    	}else {
    		result = data.substring(0, error-1) + change + data.substring(error) ;
    	}
    	return result;
    }
    
    public static String bitFallo(String paridad1, String paridad2) {
    	int count = 0;
    	String binaryResult = "";
    	while(count <= 4) {
    		if(paridad1.substring(count, count+1).equals(paridad2.substring(count, count+1))) {
    			binaryResult = binaryResult + "0";
    		}else {
    			binaryResult = binaryResult + "1";
    		}
    		count++;
    	}
    	return binaryResult;
    }
    
    //SE TRANSFORMA A BASE DECIMAL
    private static String transformarBaseADecimal(String numero, int base, int cont){
        if(numero.isEmpty()){ return "0"; }
        int exp = cont;
        if (!numero.substring(numero.length()-1).equals("."))   exp++;
        String anterior = transformarBaseADecimal(numero.substring(0,numero.length()-1),base, exp);
        double valor;
        switch (numero.substring(numero.length()-1)){
            case "-": return "-";
            case ".": return anterior;
            case "A": valor = ( 10*Math.pow(base,cont) ); break;
            case "B": valor = ( 11*Math.pow(base,cont) ); break;
            case "C": valor = ( 12*Math.pow(base,cont) ); break;
            case "D": valor = ( 13*Math.pow(base,cont) ); break;
            case "E": valor = ( 14*Math.pow(base,cont) ); break;
            case "F": valor = ( 15*Math.pow(base,cont) ); break;
            default:  valor = (Integer.parseInt( numero.substring(numero.length()-1) )*Math.pow(base,cont) );
        }
        if (anterior.equals("-")){
            return anterior+""+String.valueOf(valor);
        } else {
            if (anterior.startsWith("-")){
                return String.valueOf(Double.parseDouble(anterior)-valor);
            } else {
                return String.valueOf(Double.parseDouble(anterior)+valor);
            }
        }
    }
    
    //SE TRANSFORMA DE DECIMAL A CUALQUIER OTRO
    private static String transformarDecimalABase(String numero, int base){
        if(numero.contains(".")){
            String entero = transformarDecimalABase(numero.substring(0,numero.indexOf(".")), base);
            String x = "0."+numero.substring( numero.indexOf(".")+1,numero.length() );
            Double decimal = Double.parseDouble(x);
            x="";
            for(int i=0;i<5;i++){
                x = x + "" +chars[(int)Math.floor(decimal*base)];
                decimal = decimal*base - (int)Math.floor(decimal*base);
                
            }
            return entero+"."+x;
        } else {
            int num = Integer.parseInt(numero);
            if(Math.round(num/base)==0){
                if (Integer.parseInt(numero)<0){
                    return "-"+chars[Math.abs(num)];
                } else {
                    return chars[Math.abs(num)];
                }
            }
            
            String anterior = transformarDecimalABase(String.valueOf(num/base),base);
            int resto = Math.abs(num%base);
            
            return anterior+chars[resto];
        }
    }
    
    public static void drawNRZI(String numeroEditable, JComboBox base2) {
    	String Binary = transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+2);
    	class NRZI extends JPanel {
        	
        	@Override
        	protected void paintComponent(Graphics g) {
        		
        		int count = 0;
        		String actual;
        		
        		int large = Binary.length();
        		int space = 500/large;
        
        		int high = 25;
        		int low = 75;
        		int in = 25;
        		
        		String one = "1";
        		
        		int initial = 10;
        		int end = initial + space;
        		
        		super.paintComponent(g);
        		g.setColor(Color.RED);
        		
        		while(count < large) {
        			actual = Binary.substring(count,count+1);
        			//System.out.println((count + 1) + " " + actual);
        			//System.out.println(initial);
        			//System.out.println(end);
        			if(actual.equals(one)) {
        				g.drawLine(initial, 25, initial, 75);
        				if(in == 25) {
        					in = 75;
        				}else {
        					in = 25;
        				}
        				g.drawLine(initial, in, end, in);
        			}else {
        				g.drawLine(initial, in, end, in);
        			}
        			initial = end;
        			end = initial + space;
        			count++;
        		}
        	}
        	
        	public void drawNRZI() {
        		JFrame.setDefaultLookAndFeelDecorated(true);
        		JFrame window = new JFrame("NRZI");
        		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		window.setBackground(Color.black);
        		window.setSize(535, 150);
        		NRZI panel = new NRZI();
        		window.add(panel);
        		window.setVisible(true);
        	}
        	
        }
        NRZI draw = new NRZI();
        draw.drawNRZI();
    }
 
    public static String binaryToDecimal(String binary) {
    	String decimal = "";
    	String stringBit;
    	int result = 0;
    	int count = 0;
    	int lenght = binary.length();
    	while(count < lenght) {
    		stringBit = binary.substring(count, count+1);
    		result += Integer.parseInt(stringBit)*Math.pow(2, count);
    		count++;
    	}
    	return decimal+result;
    }
    
    private static boolean ComprobarNumero(String numero, int base){
        if (numero.contains("."))   numero = numero.replaceFirst("\\.","");
        if (numero.startsWith("-")) numero = numero.replaceFirst("\\-","");
        if(numero.isEmpty()) return false;
        for(int i=0;i<=15;i++){
            if(numero.isEmpty()) return true;
            if(numero.contains(chars[i]) && i<base) 
                numero = numero.replaceAll(chars[i], "");
        }
        return numero.isEmpty();
    }
    
}
