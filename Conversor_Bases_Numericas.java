import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
        
        final JComboBox base1 = new JComboBox(valoresBases); base1.setSelectedIndex(14); //Acá se selecciona que sea Hexadecimal directo
        final JComboBox base2 = new JComboBox(valoresBases);
        
        JButton boton = new JButton("Convertir");
        
        JFrame x = new JFrame("TAREA DISEÑO LÓGICO");
        x.setLayout(new GridBagLayout());
        
        //ACA SE GENERAN LOS TEXTOS EN PANTALLA
        x.add(new JLabel("Número "), new GridBagConstraints(0,0,1,1,0,0,21,0,new Insets(4,4,2,2),0,0));
        x.add(new JLabel("Binario "), new GridBagConstraints(0,4,1,1,0,0,21,0,new Insets(4,4,2,2),0,0));
        x.add(new JLabel("Octal "), new GridBagConstraints(0,6,1,1,0,0,21,0,new Insets(4,4,2,2),0,0));
        x.add(new JLabel("Decimal "), new GridBagConstraints(0,7,1,1,0,0,21,0,new Insets(2,4,2,2),0,0));
        x.add(new JLabel("Hilera en Binario "), new GridBagConstraints(0,8,1,1,0,0,21,0,new Insets(2,4,2,2),0,0));
        x.add(new JLabel("Bit de Falla "), new GridBagConstraints(0,9,1,1,0,0,21,0,new Insets(2,4,2,2),0,0));
        x.add(numeroDeUsuario,                new GridBagConstraints(1,0,3,1,0,0,21,2,new Insets(4,2,2,4),0,0));
    
        //ACA POSICIONAMOS LAS COSAS
        x.add(boton,  new GridBagConstraints(3,2,1,1,0,0,10,0,new Insets(2,4,4,2),0,0));
        x.add(numeroEnBinario,new GridBagConstraints(1,4,3,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(numeroEnOctal,new GridBagConstraints(3,6,6,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(numeroEnDecimal,new GridBagConstraints(3,7,12,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(hileraEnBinario,new GridBagConstraints(3,8,12,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        x.add(bitDeFalla,new GridBagConstraints(3,9,12,1,1,1,10,2,new Insets(2,2,4,4),0,0));
        
        x.setResizable(false);
        x.pack();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setLocationRelativeTo(null);
        
        boton.addActionListener(new ActionListener(){

            @Override public void actionPerformed(ActionEvent e) {
            	numeroDeUsuario.setText(numeroDeUsuario.getText().toUpperCase());
                String numeroEditable = numeroDeUsuario.getText();
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
                    	numeroEnBinario.setText("ERROR: Numero pequeño (Max. -9999999)");
                        return;
                    }
                    if(numeroEditable.endsWith(".0")) numeroEditable = numeroEditable.replace(".0", "");
                    numeroEnBinario.setText( transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+2) );
                    numeroEnOctal.setText( transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+8) );
                    numeroEnDecimal.setText( transformarDecimalABase(numeroEditable, base2.getSelectedIndex()+10) );
                    
                    
                    //COMPROBACION DEL FORMATO DEL NUMERO
                } else if(numeroEditable.contains(",")){
                	numeroEnBinario.setText("ERROR: Formato Inválido");
                	numeroEnOctal.setText("ERROR: Formato Inválido");
                	numeroEnDecimal.setText("ERROR: Formato Inválido");
                } else if (numeroEditable.isEmpty()){
                	numeroEnBinario.setText("Ingrese un número");
                	numeroEnOctal.setText("Ingrese un número");
                	numeroEnDecimal.setText("Ingrese un número");
                } else {
                	numeroEnBinario.setText("ERROR: Número no válido, ingrese otro");
                	numeroEnOctal.setText("ERROR: Número no válido, ingrese otro");
                	numeroEnDecimal.setText("ERROR: Número no válido, ingrese otro");
                }
            }
        });

        x.setVisible(true);
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
