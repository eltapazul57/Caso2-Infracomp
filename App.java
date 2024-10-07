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
        
    }

}
