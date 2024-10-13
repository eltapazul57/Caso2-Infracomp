import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths; 
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class App {    
    public static void main(String[] args) {
        App app = new App();
        app.mostrarMenu();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("Menú Principal:");
            System.out.println("1. Generar referencias de página");
            System.out.println("2. Calcular datos de fallas de página y hits");
            System.out.println("3. Esconder mensaje en imagen");
            System.out.println("4. Recuperar mensaje de imagen");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    opcionGenerarReferencias(scanner);
                    break;
                case 2:
                    opcionCalcularFallas(scanner);
                    break;
                case 3:
                    opcionEsconderMensaje(scanner);
                    break;
                case 4:
                    opcionRecuperarMensaje(scanner);
                    break;
            }
        }
        scanner.close();
    }

    public void opcionGenerarReferencias(Scanner scanner) {
        System.out.print("Ingrese el tamaño de página: ");
        int pageSize= scanner.nextInt();
        System.out.print("Ingrese el nombre del archivo que guarda la imagen: ");
        String fileName = scanner.next();
        //TODO terminar de implementar
    }

    public void opcionCalcularFallas(Scanner scanner) {
        //TODO
    }


    public void opcionEsconderMensaje(Scanner scanner) {
        //TODO PROBAR CON MÁS CASOS
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            //Lectura de IMAGEN
            System.out.println("Nombre del archivo con la imagen a procesar: ");
            String ruta = br.readLine();
            Imagen imagen = new Imagen(ruta); //generar la imagen para usarla
            System.out.println("LONGITUD IMAGEN: "+imagen.leerLongitud());
            //Lectura de MENSAJE
            System.out.println("Nombre del archivo con el mensaje a esconder: ");
            String ruta2 = br.readLine();
            String contenido = new String(Files.readAllBytes(Paths.get(ruta2)));
            System.out.println("LONGITUD MENSAJE: "+contenido.length());
            char[] arregloCaracteres = contenido.toCharArray();
            System.out.println("LONGITUD ARREGLO CARACTERES: " + arregloCaracteres.length);
            //Esconder mensaje
            imagen.esconder(arregloCaracteres, arregloCaracteres.length);
            imagen.escribirImagen(ruta);

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        
        
    }
    
    public void opcionRecuperarMensaje(Scanner scanner) {
        //TODO PROBAR CON MÁS CASOS
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("Nombre del archivo con el mensaje escondido: ");
            String ruta = br.readLine();
            System.out.println("Nombre del archivo para almacenar el mensaje recuperado: ");
            String salida = br.readLine();
            // Leer la imagen y el mensaje escondido
            Imagen imagen = new Imagen(ruta);
            int longitud = imagen.leerLongitud();
            System.out.println("LONGITUD IMAGEN: " + longitud);
            char[] mensaje = new char[longitud];
            // Recuperar el mensaje de la imagen
            imagen.recuperar(mensaje, longitud);
            // Convertir el mensaje char[] a un String
            String mensajeCompleto = new String(mensaje);
            // Escribir el mensaje en el archivo de salida
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(salida))) {
                writer.write(mensajeCompleto);
            }
            System.out.println("Mensaje recuperado y guardado en: " + salida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
