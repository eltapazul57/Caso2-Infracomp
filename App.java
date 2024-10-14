import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths; 

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
        int pageSize = scanner.nextInt();
        System.out.print("Ingrese el nombre del archivo que guarda la imagen: ");
        String fileName = scanner.next();
        Imagen imagen = new Imagen(fileName);

        int np = (imagen.alto * imagen.ancho * 3) / pageSize + (imagen.leerLongitud() / pageSize);
        int nr = (imagen.leerLongitud() * 17 + 16);

        String mensajeCompleto = "";
        mensajeCompleto = mensajeCompleto + "P = " + pageSize + "\n";
        mensajeCompleto = mensajeCompleto + "NF = " + imagen.alto + "\n";
        mensajeCompleto = mensajeCompleto + "NC = " + imagen.ancho + "\n";
        mensajeCompleto = mensajeCompleto + "NR = " + nr + "\n";
        mensajeCompleto = mensajeCompleto + "NP = " + np + "\n";

        mensajeCompleto = mensajeCompleto + imagen.crearReferencias(pageSize, np, nr);
        System.out.println(mensajeCompleto);

        
        System.out.print("Ingrese el nombre del archivo (ingrese .txt al final) para guardar las referencias: ");
        String outputFileName = scanner.next();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(mensajeCompleto);
            System.out.println("Referencias guardadas en el archivo " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void opcionCalcularFallas(Scanner scanner) {
        //TODO

        int numMarcos = 4; // esto debe cambiar por parametro
        int numReferencias = 0;
        int numPaginas = 0;

        int hits = 0;
        int miss = 0;

        int[][] marcosOcupados = new int[numMarcos][2];
        for (int i = 0; i < numMarcos; i++) {
            marcosOcupados[i][0] = -1; // número de página 
            marcosOcupados[i][1] = -1; // RecentlyUsed -> suma 10 si lee, suma 1 si escribe. 
        }

        int indice = buscarIndicePagina(marcosOcupados, 13);
        modificarRecentlyUsed(marcosOcupados, indice, 0);

        indice = buscarIndicePagina(marcosOcupados, 10);
        modificarRecentlyUsed(marcosOcupados, indice, 0);

        for (int i = 0; i < numMarcos; i++) {
            System.out.println("Marco " + i + ": Página " + marcosOcupados[i][0] + ", RecentlyUsed " + marcosOcupados[i][1]);
        }
        System.out.println("xdxdxd");


        int[] referencias = new int[numReferencias];

    }

    private void modificarRecentlyUsed(int[][] marcosOcupados, int indiceMarco, int tipo) {
        if (tipo == 0 && marcosOcupados[indiceMarco][1]%10 != 0) {
            marcosOcupados[indiceMarco][1] += 10;
        } 
        if  (tipo == 1 && marcosOcupados[indiceMarco][1]%2 != 0){
            marcosOcupados[indiceMarco][1] += 1;
        }
    }

    private int buscarIndicePagina(int[][] marcosOcupados,int pagina){
        for (int i = 0; i < marcosOcupados.length; i++) {
            if (marcosOcupados[i][0] == pagina) {
                return i;
            }
        }
        int indice = buscarIndiceMenorRecentlyUsed(marcosOcupados);
        marcosOcupados[indice][0] = pagina;
        marcosOcupados[indice][1] = 0;
        return indice;
    }

    private int buscarIndiceMenorRecentlyUsed(int[][] marcosOcupados){
        int min = marcosOcupados[0][1];
        int indice = 0;
        for (int i = 1; i < marcosOcupados.length; i++) {
            if (marcosOcupados[i][1] < min) {
                min = marcosOcupados[i][1];
                indice = i;
            }
        }
        return indice;
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
