package biblioteca;

import java.util.Vector;

public class SolicitudRevistas {
	
	private Vector <Revista> solicitudrevistas=new Vector <Revista>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("SOLICITUD DE REVISTAS");
			System.out.println("-------------------");
			System.out.println("1. Añadir revista.");
			System.out.println("2. Eliminar revista.");
			System.out.println("3. Modificar revista.");
			System.out.println("4. Mostrar revistas.");
			System.out.println("5. Solicitud de artículos.");
			System.out.println("0. Volver al menú principal.");
			opcion=PedirDatos.leerEntero("seleccione una opcion");
			switch (opcion) {
			case 1:
				addRevista();
				break;
			case 2:
				delRevista();
				break;
			case 3:
				setRevista();
				break;
			case 4:
				mostrarRevistas();
				break;
			case 5:
				gestionarArticulos();
			case 0:
				System.out.println("Regresar al menú principal...");
				System.out.println();
				break;
			default:
				System.out.println("Seleccione una opción.");
				break;
			}
		} while (opcion!=0);
	}

	private void addRevista() {

		long codrevista=PedirDatos.leerLong("Digite el código de la revista que desea añadir.");
		if (buscarRevista(codrevista)!=-1) {
			System.out.println("No se puede añadir la revista con el código "+codrevista+" porque ya existe.");
			return;
		}
		String signatura=PedirDatos.leerCadena("Escriba la signatura de la revista.");
		String nombre=PedirDatos.leerCadena("Escriba el nombre de la revista.");
		String materia=PedirDatos.leerCadena("Escriba la materia de la revista.");
		Revista r=new Revista(codrevista, signatura, nombre, materia);
		this.gestionrevistas.addElement(r);
		System.out.println("La revista con el código "+codrevista+" se ha añadido correctamente.");
	}

	private void delRevista() {
		if (this.gestionrevistas.isEmpty()) {
			System.out.println(" Revista no existe.");
			return;
		}
		long codrevista=PedirDatos.leerLong("Digite el código de la revista que desea eliminar.");
		int pos=buscarRevista(codrevista);
		if (pos==-1) {
			System.out.println("Revista con el código "+codrevista+" no existe.");
			return;
		}
		this.gestionrevistas.remove(pos);
		System.out.println("La revista con el código "+codrevista+" ha sido eliminada correctamente.");
	}

	private void setRevista() {
		if (this.gestionrevistas.isEmpty()) {
			System.out.println("Revista no existe.");
			return;
		}
		long codrevista=PedirDatos.leerLong("Digite el código de la revista que desea modificar.");
		int pos=buscarRevista(codrevista);
		if (pos==-1) {
			System.out.println("Revista con el código "+codrevista+" no existe.");
			return;
		}
		System.out.println("Los datos de la revista con el código "+codrevista+" son:");
		System.out.println(this.gestionrevistas.elementAt(pos));
		String signatura=PedirDatos.leerCadena("Escriba la nueva signatura de la revista.");
		String nombre=PedirDatos.leerCadena("Escriba el nuevo nombre de la revista.");
		String materia=PedirDatos.leerCadena("Escriba la nueva materia de la revista.");
		Revista r=new Revista(codrevista, signatura, nombre, materia);
		this.gestionrevistas.add(pos, r);
		System.out.println("Revista con el código "+codrevista+" ha sido modificada correctamente.");
	}

	private void mostrarRevistas() {
		for (int i = 0; i < this.solicitudrevistas.size(); i++) {
			System.out.println(this.solicitudrevistas.elementAt(i));
			System.out.println("-------------------------");
		}
	}
	
	public void gestionarArticulos() {
		long codrevista=PedirDatos.leerLong("Digite el código de la revista.");
		int pos=buscarRevista(codrevista);
		if (pos==-1) {
			System.out.println("No existe ninguna revista con el código "+codrevista+".");
			return;
		}
		this.solicitudrevistas.elementAt(pos).solicitudrevistas();
	}
	
	public int buscarRevista(long codrevista) {
		for (int i = 0; i < this.solicitudrevistas.size(); i++) {
			if (this.solicitudrevistas.elementAt(i).getCodrevista()==codrevista) {
				return i;
			}
		}
		return -1;
	}
}
