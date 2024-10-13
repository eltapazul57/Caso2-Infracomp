import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
        Imagen imagen = new Imagen(fileName);

        int np = (imagen.alto * imagen.ancho * 3) / pageSize + (imagen.leerLongitud()/pageSize);
        int nr = (imagen.leerLongitud()*17 + 16);

        String mensajeCompleto = "";
        mensajeCompleto = mensajeCompleto + "P = " + pageSize + "\n";
        mensajeCompleto = mensajeCompleto + "NF = " + imagen.alto + "\n";
        mensajeCompleto = mensajeCompleto + "NC = " + imagen.ancho + "\n";
        mensajeCompleto = mensajeCompleto + "NR = "+ nr +"\n";
        mensajeCompleto = mensajeCompleto + "NP = "+ np +"\n";

        

        System.out.println(mensajeCompleto);
        imagen.crearReferencias(pageSize, np, nr);


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
        //PROBAR CON MÁS CASOS
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
