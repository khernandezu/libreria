package biblioteca;

import java.util.Vector;

public class solicitudBiblioteca {
	private solicitudLibros gl=new solicitudLibros();
	private solicitudRevistas gr=new solicitudRevistas();
	private solicitudUsuarios gu=new solicitudUsuarios();
	
	private Vector <Prestamo> prestamos=new Vector<Prestamo>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("BIBLIOTECA DE TIBAS");
			System.out.println("----------------------------------");
			System.out.println("1. Solicitud de libros.");
			System.out.println("2. Solicitud de revistas.");
			System.out.println("3. Solicitud de usuarios.");
			System.out.println("4. Detalle préstamo.");
			System.out.println("5. Devolver préstamo.");
			System.out.println("6. Mostrar préstamos.");
			System.out.println("0. Salir.");
			opcion=PedirDatos.leerEntero("Indicar Solicitud");
			switch (opcion) {
			case 1:
				solicitudLibros();
				break;
			case 2:
				solicitudRevistas();
				break;
			case 3:
				solicitudUsuarios();
				break;
			case 4:
				detallePrestamo();
				break;
			case 5:
				devolverPrestamo();
				break;
			case 6:
				mostrarPrestamos();
				break;
			case 0:
				System.out.println("¡Adiós!");
				break;
			default:
				System.out.println("Escoger una opción");
				break;
			}
		} while (opcion!=0);
	}

	private void solicitudLibros() {
		gl.menu();
	}

	private void solicitudRevistas() {
		gr.menu();
	}

	private void solicitudUsuarios() {
		gu.menu();
	}
	
	public void realizarPrestamo() {
		long codusuario=PedirDatos.leerLong("Digite el código del usuario.");
		int pos=gu.buscarUsuario(codusuario);
		if (pos==-1) {
			System.out.println("El usuario con el código "+codusuario+" no existe.");
			return;
		}
		char tipomaterial=PedirDatos.leerCaracter("Escoja el articulo que desea. (L = Libro | R = Revista)");
		while (tipomaterial!='L'&&tipomaterial!='R'&&tipomaterial!='C') {
			tipomaterial=PedirDatos.leerCaracter("Valor incorrecto. Escoja el articulo que desea. (L = Libro | R = Revista)");
		}
		long codmaterial=0;
		switch (tipomaterial) {
		case 'L':
			codmaterial=PedirDatos.leerLong("Digite el PIN del libro que desea solicitar.");
			pos=gl.buscarLibro(codmaterial);
			if (pos==-1) {
				System.out.println("El libro con el PIN "+codmaterial+" no existe.");
				return;
			}
			break;
		case 'R':
			codmaterial=PedirDatos.leerLong("Digite el código de la revista que desea sacar.");
			pos=gr.buscarRevista(codmaterial);
			if (pos==-1) {
				System.out.println("La revista con el código "+codmaterial+" no existe.");
				return;
			}
			break;

		}
		if (buscarPrestamo(tipomaterial, codmaterial)!=-1) {
			System.out.println("El articulo que desea pedir se encuentra prestado actualmente.");
			return;
		}
		String fechaprestamo=PedirDatos.leerCadena("Digite la fecha del préstamo (DD/MM/AAAA).");
		Prestamo p=new Prestamo(codusuario, tipomaterial, codmaterial, fechaprestamo);
		this.prestamos.addElement(p);
		System.out.println("Préstamo realizado correctamente.");
	}
	
	public void devolverPrestamo() {
		if (this.prestamos.isEmpty()) {
			System.out.println("No se pueden devolver préstamos porque todavía no se ha realizado ninguno.");
			return;
		}
		long codusuario=PedirDatos.leerLong("Digite el código de usuario que realizó el préstamo.");
		char tipomaterial=PedirDatos.leerCaracter("Indique el tipo de material que se prestó.");
		long codmaterial=PedirDatos.leerLong("Digite el código del material que se prestó.");
		int pos=buscarPrestamo(codusuario, tipomaterial, codmaterial);
		if (pos==-1) {
			System.out.println("No existe ningún préstamo con los datos introducidos.");
			return;
		}
		String fechadevolucion=PedirDatos.leerCadena("Indique la fecha de devolución (DD/MM/AAAA).");
		this.prestamos.elementAt(pos).setFechadevolucion(fechadevolucion);
		System.out.println("Se ha devuelto el siguiente préstamo:\nCódigo del préstamo: "+(pos+1)+"\n"+this.prestamos.elementAt(pos));
	}
	
	public void mostrarPrestamos() {
		for (int i = 0; i < this.prestamos.size(); i++) {
			System.out.println(this.prestamos.elementAt(i));
			System.out.println("-------------------------");
		}
	}
	
	//Método de búsqueda para los préstamos
	private int buscarPrestamo(char tipomaterial, long codmaterial) {
		for (int i = 0; i < this.prestamos.size(); i++) {
			if (tipomaterial==this.prestamos.elementAt(i).getCodmaterial()&&codmaterial==this.prestamos.elementAt(i).getCodmaterial()&&
					this.prestamos.elementAt(i).getFechadevolucion()==null) {
				return i;
			}
		}
		return -1;
	}
	
	//Método de búsqueda para las devoluciones
	private int buscarPrestamo(long codusuario, char tipomaterial, long codmaterial) {
		for (int i = 0; i < this.prestamos.size(); i++) {
			if (codusuario==this.prestamos.elementAt(i).getCodusuario()&&tipomaterial==this.prestamos.elementAt(i).getCodmaterial()&&
					codmaterial==this.prestamos.elementAt(i).getCodmaterial()&&this.prestamos.elementAt(i).getFechadevolucion()==null) {
				return i;
			}
		}
		return -1;
	}
}
