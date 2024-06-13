package biblioteca;

import java.util.Vector;

public class SolicitudUsuarios {

	private Vector <Usuario> solicitudusuarios=new Vector <Usuario>();
	
	public void menu() {
		int opcion=0;
		do {
			System.out.println("SOLICITUD DE USUARIOS");
			System.out.println("-------------------");
			System.out.println("1. Añadir usuario.");
			System.out.println("2. Eliminar usuario.");
			System.out.println("3. Modificar usuario.");
			System.out.println("4. Mostrar usuarios.");
			System.out.println("0. Regresar al menú principal.");
			opcion=PedirDatos.leerEntero("Seleccione una opcion");
			switch (opcion) {
			case 1:
				addUsuario();
				break;
			case 2:
				delUsuario();
				break;
			case 3:
				setUsuario();
				break;
			case 4:
				mostrarUsuarios();
				break;
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

	private void addUsuario() {
		long codusuario=PedirDatos.leerLong("Digite el código del usuario que desea añadir.");
		if (buscarUsuario(codusuario)!=-1) {
			System.out.println("Usuario con el código "+codusuario+" ya existe.");
			return;
		}
		String nombre=PedirDatos.leerCadena("Digite el nombre del usuario.");
		String apellido1=PedirDatos.leerCadena("Digite el primer apellido del usuario.");
		String apellido2=PedirDatos.leerCadena("Digite el segundo apellido del usuario.");
		Usuario u=new Usuario(codusuario, nombre, apellido1, apellido2);
		this.gestionusuarios.addElement(u);
		System.out.println("Usuario con el código "+codusuario+" ha sido añadido correctamente.");
	}

	private void delUsuario() {
		if (this.gestionusuarios.isEmpty()) {
			System.out.println("Usuario no existe.");
			return;
		}
		long codusuario=PedirDatos.leerLong("Digite el código del usuario que desea eliminar.");
		int pos=buscarUsuario(codusuario);
		if (pos==-1) {
			System.out.println("Usuario con el código "+codusuario+" no existe.");
			return;
		}
		this.gestionusuarios.remove(pos);
		System.out.println("Usuario con el código "+codusuario+" ha sido eliminado correctamente.");
	}

	private void setUsuario() {
		if (this.gestionusuarios.isEmpty()) {
			System.out.println("Usuario no existe.");
			return;
		}
		long codusuario=PedirDatos.leerLong("Digite el código del usuario que desea modificar.");
		int pos=buscarUsuario(codusuario);
		if (pos==-1) {
			System.out.println("Usuario con el código "+codusuario+" no existe.");
			return;
		}
		System.out.println("Los datos del usuario con el código "+codusuario+" son:");
		System.out.println(this.gestionusuarios.elementAt(pos));
		String nombre=PedirDatos.leerCadena("Escriba el nuevo nombre del usuario.");
		String apellido1=PedirDatos.leerCadena("Escriba el nuevo primer apellido del usuario.");
		String apellido2=PedirDatos.leerCadena("Escriba el nuevo segundo apellido del usuario.");
		Usuario u=new Usuario(codusuario, nombre, apellido1, apellido2);
		this.gestionusuarios.add(pos, u);
		System.out.println("Usuario con el código "+codusuario+" ha sido modificado correctamente.");
	}

	private void mostrarUsuarios() {
		for (int i = 0; i < this.solicitudusuarios.size(); i++) {
			System.out.println(this.solicitudusuarios.elementAt(i));
			System.out.println("-------------------------");
		}
	}
	
	public int buscarUsuario(long codusuario) {
		for (int i = 0; i < this.solicitudusuarios.size(); i++) {
			if (this.solicitudusuarios.elementAt(i).getCodusuario()==codusuario) {
				return i;
			}
		}
		return -1;
	}
}