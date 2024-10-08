import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

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
        //TODO
        /* 
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("Nombre del archivo con la imagen a procesar: ");
            ruta = br.readLine();
            // Cargar la imagen desde la ruta proporcionada
            imagen = new Imagen(ruta);
            System.out.println("Nombre del archivo con el mensaje a esconder: ");
            String ruta2 = br.readLine();
            // Leer el archivo de texto que contiene el mensaje
            int longitud = leerArchivoTexto(ruta2);
            // Esconder el mensaje en la imagen
            imagen.esconder(mensaje, longitud);
            // Escribir la imagen modificada con el mensaje escondido
            imagen.escribirImagen("salida" + ruta);
            // El bitmap de salida debería poder abrirse en un editor de imágenes sin percibir cambios
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            System.out.println("Nombre del archivo con la imagen a procesar: ");
            String ruta = br.readLine();
            Imagen imagen = new Imagen(ruta);
            System.out.println("Nombre del archivo con el mensaje a esconder: ");
            String ruta2 = br.readLine();
            //int longitud = leerArchivoTexto(ruta2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void opcionRecuperarMensaje(Scanner scanner) {
        //TODO
    }
}
