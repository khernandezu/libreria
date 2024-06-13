package biblioteca;

import java.util.Vector;

public class GestionLibros {

	private Vector <Libro> solicitudlibros=new Vector<Libro>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("Solicitud de Libros");
			System.out.println("-----------------");
			System.out.println("1. Añadir libro.");
			System.out.println("2. Eliminar libro.");
			System.out.println("3. Modificar libro.");
			System.out.println("4. Mostrar libro.");
			System.out.println("0. Regresar al menú principal.");
			opcion=PedirDatos.leerEntero("Indique su solicitud");
			switch (opcion) {
			case 1:
				addLibro();
				break;
			case 2:
				delLibro();
				break;
			case 3:
				setLibro();
				break;
			case 4:
				mostrarLibros();
				break;
			case 0:
				System.out.println("Regresar al menú principal...");
				System.out.println();
				break;
			default:
				System.out.println("Digite una opción.");
				break;
			}			
		} while (opcion!=0);
	}

	private void addLibro() {
		
		long isbn=PedirDatos.leerLong("Digite el PIN del libro que desea añadir.");
		if (buscarLibro(isbn)!=-1) {
			System.out.println("No se puede añadir el libro con el PIN "+isbn+" porque ya existe.");
			return;
		}
		//String signatura=PedirDatos.leerCadena("¿Desea modificar la signatura?");
		
		String signatura=PedirDatos.leerCadena("Escriba la signatura.");
		String titulo=PedirDatos.leerCadena("Escriba el título.");
		String autor=PedirDatos.leerCadena("Escriba el autor.");
		String materia=PedirDatos.leerCadena("Escriba la materia.");
		String editorial=PedirDatos.leerCadena("Escriba la editorial.");
		Libro l=new Libro(isbn, signatura, titulo, autor, materia, editorial);
		this.gestionlibros.addElement(l);
		System.out.println("El libro con el PIN "+isbn+" se ha creado correctamente.");
	}

	private void delLibro() {
		if (this.gestionlibros.isEmpty()) {
			System.out.println("libro no existe.");
			return;
		}
		long isbn=PedirDatos.leerLong("Introduzca el PIN del libro que desea eliminar.");
		int pos=buscarLibro(isbn);
		if (pos==-1) {
			System.out.println("No se puede eliminar el libro con el PIN "+pos+" porque no existe.");
			return;
		}
		this.gestionlibros.remove(pos);
		System.out.println("El libro con el PIN "+isbn+" ha sido eliminado correctamente.");
	}

	private void setLibro() {
		if (this.gestionlibros.isEmpty()) {
			System.out.println("libros no existe.");
			return;
		}
		long isbn=PedirDatos.leerLong("Introduzca el PIN del libro que desea modificar.");
		int pos=buscarLibro(isbn);
		if (pos==-1) {
			System.out.println("No se puede modificar el libro con el PIN "+isbn+" porque no existe.");
			return;
		}
		System.out.println("Los datos del libro con el PIN "+isbn+" son:");
		System.out.println(this.gestionlibros.elementAt(pos));
		String signatura=PedirDatos.leerCadena("Escriba la nueva signatura.");
		String titulo=PedirDatos.leerCadena("Escriba el nuevo título.");
		String autor=PedirDatos.leerCadena("Escriba el nuevo autor.");
		String materia=PedirDatos.leerCadena("Escriba la nueva materia.");
		String editorial=PedirDatos.leerCadena("Escriba la nueva editorial.");
		Libro l=new Libro(isbn, signatura, titulo, autor, materia, editorial);
		this.gestionlibros.add(pos, l);
		System.out.println("El libro con el PIN "+isbn+" ha sido modificado correctamente.");
	}

	private void mostrarLibros() {
		for (int i = 0; i < this.solicitudlibros.size(); i++) {
			System.out.println(this.solicitudlibros.elementAt(i));
			System.out.println("-------------------------");
		}
	}
	
	public int buscarLibro(long isbn) {
		for (int i = 0; i < this.solicitudlibros.size(); i++) {
			if (this.solicitudlibros.elementAt(i).getISBN()==isbn) {
				return i;
			}
		}
		return -1;
	}
}