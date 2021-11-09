package main;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import business.Criticas;
import business.GestorCriticas;
import business.GestorEspectaculos;
import business.GestorUsuario;
import business.Usuario;
import business.AbstractEspectaculo.categoria;

import data.dao.CriticasDAO;
import data.dao.UsuarioDAO;
import data.dao.PuntualDAO;




public class Main{
	public static void main(String[] args) throws IOException {
		Scanner entradaInt = new Scanner(System.in);
		Scanner entradaSt = new Scanner(System.in);
		Scanner entradaD = new Scanner(System.in);
		Scanner entradacorreo = new Scanner(System.in);
		Scanner entradaF = new Scanner(System.in);

		ArrayList<Usuario>listausuarios=new ArrayList<Usuario>();

		GestorUsuario gu1 = new GestorUsuario(listausuarios);
		GestorCriticas GC1= GestorCriticas.getInstancia();
		GestorEspectaculos GE=new GestorEspectaculos();
		UsuarioDAO UDAO=new UsuarioDAO();
		CriticasDAO CDAO=new CriticasDAO();
		PuntualDAO PDAO= new PuntualDAO();
		
		int opcion;
		int intentos=0;
		int op=0;
		
		String ab;
		String correoInicioSesion = null;
		String autor2;
		String nombreRegister,apellidosRegister,nickRegister,correoRegister, tipoRegister;
		String tituloCrear,espectaculoCrear,puntuacionCrear,reviewCrear ;
		
		GE.guardarBDlistas();
		//GE.leerFicheroMultiple();
		//GE.leerFicheroPuntual();
		//PDAO.obtenerPuntual();
		//GE.leerFicheroTemporada();
		//gu1.leerFicheroUsuarios(listausuarios);
		gu1.guardarBDU();
		//UDAO.obtenerUsuarios();
		//GC1.leerFicheroCriticas();
		//CDAO.obtenerCriticas();
		GC1.guardarBDC();
		
		System.out.println("Desea registrarse [1] o iniciar sesion [2] o salir [0]\n");
		opcion=entradaInt.nextInt();
		if(opcion==1) {
			System.out.println("Introduzca el correo");
			correoRegister=entradaSt.nextLine();
				if(gu1.existeUsuario(correoRegister)) {
					System.out.println("Ese correo est� ya asociado a una cuenta\n");
				}else {
					System.out.println("Introduzca el nombre\n");
					nombreRegister=entradaSt.nextLine();
					System.out.println("Introduzca los apellidos\n");
					apellidosRegister=entradaSt.nextLine();
					System.out.println("Introduzca el nick\n");
					nickRegister=entradaSt.nextLine();
					System.out.println("Eres administrador si [1] , no [0]\n");
					tipoRegister=entradaSt.nextLine();
					gu1.register(nombreRegister,apellidosRegister,nickRegister,correoRegister,tipoRegister);
					System.out.println("Usuario de correo "+correoRegister+" a�adido correctamente\n");
				}
			opcion=2;
		}
		if(opcion==2) {
			do {
			if(intentos==3) {
				System.out.println("Saliendo del programa, intentos superados...");
				System.exit(0);
			}
			System.out.println("Introduzca su correo ("+ (3-intentos) +" intentos):");
			correoInicioSesion=entradacorreo.nextLine();
			intentos++;
			}while(!gu1.existeUsuario(correoInicioSesion) && intentos<=3);
					}
		else{
			System.exit(0);
		}
		
		String tituloC,descrC,fechaCS,fechaCS1,diaC=null;
		Date fechaC,fechaC1;
		int aforoC, locaC;
		categoria cat=null;
		ArrayList<Date> lf=new ArrayList<Date>();
		
		String tipo=gu1.tipoUsuario(correoInicioSesion);
		if(tipo.equals("1")) {
			do {
				System.out.println("Selecciona una opcion");
				System.out.println("1:Dar de alta un espectaculo");
				System.out.println("2:Cancelar un espectaculo");
				System.out.println("3:Actualizar un espectaculo");
				System.out.println("4:Contabilizar la venta de entradas");
				System.out.println("Otro:Salir");
				op=entradaInt.nextInt();
				
				switch(op) {
					case 1:
						System.out.println("Como es el espectaculo que quieres dar de alta");
						System.out.println("Puntual [1] Temporal [2] Multiple [3]");
						int q=entradaInt.nextInt();
						
						if(q==1) {
							System.out.println("Titulo del espectaculo:");
							tituloC=entradaSt.nextLine();
							if(!GE.existeEspectaculo(tituloC)) {
								System.out.println("Descripcion:");
								descrC=entradaSt.nextLine();
								System.out.println("Categoria");
								System.out.println("Selecciona una: Concierto [0] Obra [1] Monologo [2]");
								int y=entradaInt.nextInt();
								do {
									if(y==0) {
										cat=categoria.concierto;
										y=4;
									}else if(y==1) {
										cat=categoria.obra;
										y=4;
									}else if(y==2) {
										cat=categoria.monologo;
										y=4;
									}else {
										System.out.println("Opcion incorrecta, vuelve a intentarlo ");
										System.out.println("Selecciona una: Concierto [1] Obra [2] Monologo [3]");
										y=entradaInt.nextInt();
									}
								}while(y>=0 && y<=2 );
								System.out.println("Aforo disponible");
								aforoC=entradaSt.nextInt();
								System.out.println("Localidades vendidas");
								locaC=entradaSt.nextInt();
								System.out.println("Fecha del espectaculo, el formato es yyyy-mm-dd");
								fechaCS=entradaD.nextLine();

								fechaC=Date.valueOf(fechaCS);
								System.out.println(fechaC);

								GE.registerEspectaculoP(tituloC,descrC, cat, aforoC, locaC,fechaC);
							}else {
								System.out.println("Titulo ya utiliado");
							}
						}else if(q==2) {
							
							System.out.println("Titulo del espectaculo:");
							tituloC=entradaSt.nextLine();
							if(!GE.existeEspectaculo(tituloC)) {
								System.out.println("Descripcion:");
								descrC=entradaSt.nextLine();
								System.out.println("Categoria");
								System.out.println("Selecciona una: Concierto [0] Obra [1] Monologo [2]");
								int y=entradaInt.nextInt();
								do {
									if(y==0) {
										cat=categoria.concierto;
										y=4;
									}else if(y==1) {
										cat=categoria.obra;
										y=4;
									}else if(y==2) {
										cat=categoria.monologo;
										y=4;
									}else {
										System.out.println("Opcion incorrecta, vuelve a intentarlo ");
										System.out.println("Selecciona una: Concierto [1] Obra [2] Monologo [3]");
										y=entradaInt.nextInt();
									}
								}while(y>=0 && y<=2 );
								System.out.println("Aforo disponible");
								aforoC=entradaInt.nextInt();
								System.out.println("Localidades vendidas");
								locaC=entradaInt.nextInt();
								int jojo;
								do {
									System.out.println("Dia de la semana donde se realiza el espectaculo");
									System.out.println("[1] Lunes [2] Martes [3] Miercoles [4] Jueves [5] Viernes [6] Sabado [7] Domingo");
									jojo=entradaInt.nextInt();
									switch(jojo) {
										case 1:
											diaC="Lunes";
										break;
										case 2:
											diaC="Martes";
	
											break;
										case 3:
											diaC="Miercoles";
	
											break;
										case 4:
											diaC="Jueves";
	
											break;
										case 5:
											diaC="Viernes";
	
											break;
										case 6:
											diaC="Sabado";
	
											break;
										case 7:
											diaC="Domingo";
	
											break;
										default:
											System.out.println("prueba otra vez");
									}
									
								}while(jojo<=0 && jojo>=7);
								//System.out.println(diaC);				
								System.out.println("Fecha de inicio, el formato es yyyy-mm-dd ");
								fechaCS=entradaD.nextLine();
								fechaC=Date.valueOf(fechaCS);
								System.out.println("Fecha de fin, el formato es yyyy-mm-dd ");
								fechaCS1=entradaD.nextLine();
								fechaC1=Date.valueOf(fechaCS1);
								GE.registerEspectaculoT(tituloC, descrC, cat, aforoC, locaC, diaC, fechaC, fechaC1);
							}else {
								System.out.println("Titulo ya utiliado");
							}
							
						}else if(q==3) {
							
							System.out.println("Titulo del espectaculo:");
							tituloC=entradaSt.nextLine();
							if(!GE.existeEspectaculo(tituloC)) {
								System.out.println("Descripcion:");
								descrC=entradaSt.nextLine();
								System.out.println("Categoria");
								System.out.println("Selecciona una: Concierto [0] Obra [1] Monologo [2]");
								int y=entradaInt.nextInt();
								do {
									if(y==0) {
										cat=categoria.concierto;
										y=4;
									}else if(y==1) {
										cat=categoria.obra;
										y=4;
									}else if(y==2) {
										cat=categoria.monologo;
										y=4;
									}else {
										System.out.println("Opcion incorrecta, vuelve a intentarlo ");
										System.out.println("Selecciona una: Concierto [1] Obra [2] Monologo [3]");
										y=entradaInt.nextInt();
									}
								}while(y>=0 && y<=2 );
								System.out.println("Aforo disponible");
								aforoC=entradaSt.nextInt();
								System.out.println("Localidades vendidas");
								locaC=entradaSt.nextInt();
								System.out.println("Escriben las fechas del espectaculo");
								System.out.println("Cuantas fechas va a tener");
								int g=entradaInt.nextInt();
								for(int i=0;i<g;i++) {
									System.out.println("Fecha,, el formato es yyyy-mm-dd: ");
									fechaCS1=entradaD.nextLine();
									fechaC1=Date.valueOf(fechaCS1);
									lf.add(fechaC1);
								}
								GE.registerEspectaculoM(tituloC, descrC, cat, aforoC, locaC, lf);
							}else {
								System.out.println("Titulo ya utiliado");
							}
							
						}else {
							System.out.println("La opcion introducida es incorrecta");
						}
						
					break;
					case 2:
						System.out.println("Quiere cancelar todo el espectaculo [0] o una fecha en particular [1]");
						GE.guardarBDlistas();
						int j=entradaInt.nextInt();
						System.out.println("Que espectaculo quieres cancelar");
						String titc=entradaSt.nextLine();
						if(j==0) {
							GE.eliminarEspectaculoTodas(titc);
						}else if(j==1) {
							int n=GE.tipoEvento(titc);
							if(n==1) {
								System.out.println("Fecha del espectaculo, el formato es yyyy-mm-dd");
								fechaCS=entradaSt.nextLine();
								fechaC=Date.valueOf(fechaCS);
								GE.eliminarEspectaculoUnaM(titc, fechaC);
							}else if(n==2) {
								System.out.println("Fecha del espectaculo, el formato es yyyy-mm-dd");
								fechaCS=entradaSt.nextLine();
								fechaC=Date.valueOf(fechaCS);
								GE.eliminarEspectaculoUnaT(titc, fechaC);
							}else if(n==3) {
								System.out.println("Fecha del espectaculo, el formato es yyyy-mm-dd");
								fechaCS=entradaSt.nextLine();
								fechaC=Date.valueOf(fechaCS);
								GE.eliminarEspectaculoUnaP(titc, fechaC);
							}else {
								System.out.println("Ha ocurrido un fallo");
							}							
						}else {
							System.out.println("Opcion incorrecta");
						}												
					break;
					case 3:
						String nuevoTitulo = null, nuevaDescripcion = null, nuevoDia=null, fechaAux=null;
						categoria nuevaCategoria=null;
						Date nuevaFecha=null,viejaFecha=null, nuevaFechaIni=null, nuevaFechaFin=null; 
						int nuevoAforo=0, nuevasVendiddas=0;
						
						System.out.println("Que espectaculo quieres modificar");
						ab=entradaSt.nextLine();
						int z=GE.tipoEvento(ab);
						if(z==1) {
							int opcionUpdate;
							System.out.println("Seleccione qu� desea cambiar:\n");
							System.out.println("[1].Titulo");
							System.out.println("[2].Descripcion");
							System.out.println("[3].Categoria");
							System.out.println("[4].Aforo");
							System.out.println("[5].Localidades vendidads");
							System.out.println("[6].Actualizar fecha");
							System.out.println("Otro:Salir\n");
							opcionUpdate=entradaInt.nextInt();
							switch(opcionUpdate) {
								case 1:
									System.out.println("Nuevo titulo");
									nuevoTitulo=entradaSt.nextLine();
									GE.updateEspectaculoM(ab,nuevoTitulo,nuevaDescripcion,nuevaCategoria ,nuevoAforo ,nuevasVendiddas ,null ,null,opcionUpdate );
								break;
								case 2:
									System.out.println("Nueva descripcion");
									nuevaDescripcion=entradaSt.nextLine();
									GE.updateEspectaculoM(ab,nuevoTitulo,nuevaDescripcion,nuevaCategoria ,nuevoAforo ,nuevasVendiddas ,null ,null,opcionUpdate );
								break;
								case 3:
									System.out.println("Nueva categoria");
									System.out.println("Selecciona una: Concierto [0] Obra [1] Monologo [2]");
									int y=entradaInt.nextInt();
									do {
										if(y==0) {
											nuevaCategoria=categoria.concierto;
										}else if(y==1) {
											nuevaCategoria=categoria.obra;
										}else if(y==2) {
											nuevaCategoria=categoria.monologo;
										}else {
											System.out.println("Opcion incorrecta, vuelve a intentarlo ");
											System.out.println("Selecciona una: Concierto [1] Obra [2] Monologo [3]");
											y=entradaInt.nextInt();
										}
									}while(y>=0 && y<=2 );
									GE.updateEspectaculoM(ab,nuevoTitulo,nuevaDescripcion,nuevaCategoria ,nuevoAforo ,nuevasVendiddas ,null ,null,opcionUpdate );
								break;
								case 4:
									System.out.println("Nuevo aforo");
									nuevoAforo=entradaInt.nextInt();
									GE.updateEspectaculoM(ab,nuevoTitulo,nuevaDescripcion,nuevaCategoria ,nuevoAforo ,nuevasVendiddas ,null ,null,opcionUpdate );
								break;
								case 5:
									System.out.println("Nueva vendidas");
									nuevasVendiddas=entradaInt.nextInt();
									GE.updateEspectaculoM(ab,nuevoTitulo,nuevaDescripcion,nuevaCategoria ,nuevoAforo ,nuevasVendiddas ,null ,null,opcionUpdate );
								break;
								case 6:
									System.out.println("Fecha a cambiar, el formato es yyyy-mm-dd");
									fechaAux=entradaD.nextLine();
									viejaFecha=Date.valueOf(fechaAux);
									System.out.println("Nueva fecha, el formato es yyyy-mm-dd");
									fechaAux=entradaD.nextLine();
									nuevaFecha=Date.valueOf(fechaAux);
									GE.updateEspectaculoM(ab,nuevoTitulo,nuevaDescripcion,nuevaCategoria ,nuevoAforo ,nuevasVendiddas ,viejaFecha ,nuevaFecha,opcionUpdate );
								break;
							}
						}else if(z==2) {
							int opcionUpdate;
							System.out.println("Seleccione qu� desea cambiar:\n");
							System.out.println("[1].Titulo");
							System.out.println("[2].Descripcion");
							System.out.println("[3].Categoria");
							System.out.println("[4].Aforo");
							System.out.println("[5].Localidades vendidads");
							System.out.println("[6].Dia");
							System.out.println("[7].Fecha inicio");
							System.out.println("[8].Fecha fin");
							System.out.println("Otro:Salir\n");
							opcionUpdate=entradaInt.nextInt();
							switch(opcionUpdate) {
								case 1:
									System.out.println("Nuevo titulo");
									nuevoTitulo=entradaSt.nextLine();
									GE.updateEspectaculoT(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFechaFin, nuevaFechaIni, nuevoDia, opcionUpdate);
								break;
								case 2:
									System.out.println("Nueva descripcion");
									nuevaDescripcion=entradaSt.nextLine();
									GE.updateEspectaculoT(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFechaFin, nuevaFechaIni, nuevoDia, opcionUpdate);
								break;
								case 3:
									System.out.println("Nueva categoria");
									System.out.println("Selecciona una: Concierto [0] Obra [1] Monologo [2]");
									int y=entradaInt.nextInt();
									do {
										if(y==0) {
											nuevaCategoria=categoria.concierto;
											y=4;
										}else if(y==1) {
											nuevaCategoria=categoria.obra;
											y=4;
										}else if(y==2) {
											nuevaCategoria=categoria.monologo;
											y=4;
										}else {
											System.out.println("Opcion incorrecta, vuelve a intentarlo ");
											System.out.println("Selecciona una: Concierto [1] Obra [2] Monologo [3]");
											y=entradaInt.nextInt();
										}
									}while(y>=0 && y<=2 );
									GE.updateEspectaculoT(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFechaFin, nuevaFechaIni, nuevoDia, opcionUpdate);
								break;
								case 4:
									System.out.println("Nuevo aforo");
									nuevoAforo=entradaInt.nextInt();
									GE.updateEspectaculoT(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFechaFin, nuevaFechaIni, nuevoDia, opcionUpdate);
								break;
								case 5:
									System.out.println("Nueva vendidas");
									nuevasVendiddas=entradaInt.nextInt();
									GE.updateEspectaculoT(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFechaFin, nuevaFechaIni, nuevoDia, opcionUpdate);
								break;
								case 6:
									int jojo;
									do {
										System.out.println("Dia de la semana donde se realiza el espectaculo");
										System.out.println("[1] Lunes [2] Martes [3] Miercoles [4] Jueves [5] Viernes [6] Sabado [7] Domingo");
										jojo=entradaInt.nextInt();
										switch(jojo) {
										case 1:
											nuevoDia="Lunes";
										break;
										case 2:
											nuevoDia="Martes";

											break;
										case 3:
											nuevoDia="Miercoles";

											break;
										case 4:
											nuevoDia="Jueves";

											break;
										case 5:
											nuevoDia="Viernes";

											break;
										case 6:
											nuevoDia="Sabado";

											break;
										case 7:
											nuevoDia="Domingo";

											break;
										default:
											System.out.println("prueba otra vez");
										}
										
									}while(jojo<=0 && jojo>=7);
									
									GE.updateEspectaculoT(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFechaFin, nuevaFechaIni, nuevoDia, opcionUpdate);
								break;
								case 7:
									System.out.println("Nueva fecha inicio, el formato es yyyy-mm-dd");
									fechaAux=entradaD.nextLine();
									nuevaFechaIni=Date.valueOf(fechaAux);
									GE.updateEspectaculoT(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFechaFin, nuevaFechaIni, nuevoDia, opcionUpdate);
								break;
								case 8:
									System.out.println("Nueva fecha fin, el formato es yyyy-mm-dd");
									fechaAux=entradaD.nextLine();
									nuevaFechaFin=Date.valueOf(fechaAux);
									GE.updateEspectaculoT(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFechaFin, nuevaFechaIni, nuevoDia, opcionUpdate);
								break;
							}
						}else if(z==3) {
							int opcionUpdate;
							System.out.println("Seleccione qu� desea cambiar:\n");
							System.out.println("[1].Titulo");
							System.out.println("[2].Descripcion");
							System.out.println("[3].Categoria");
							System.out.println("[4].Aforo");
							System.out.println("[5].Localidades vendidads");
							System.out.println("[6].Fecha ");
							System.out.println("Otro:Salir\n");
							opcionUpdate=entradaInt.nextInt();
							switch(opcionUpdate) {
								case 1:
									System.out.println("Nuevo titulo");
									nuevoTitulo=entradaSt.nextLine();
									GE.updateEspectaculoP(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFecha, opcionUpdate);
								break;
								case 2:
									System.out.println("Nueva descripcion");
									nuevaDescripcion=entradaSt.nextLine();
									GE.updateEspectaculoP(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFecha, opcionUpdate);
								break;
								case 3:
									System.out.println("Nueva categoria");
									System.out.println("Selecciona una: Concierto [0] Obra [1] Monologo [2]");
									int y=entradaInt.nextInt();
									do {
										if(y==0) {
											nuevaCategoria=categoria.concierto;
											y=4;
										}else if(y==1) {
											nuevaCategoria=categoria.obra;
											y=4;
										}else if(y==2) {
											nuevaCategoria=categoria.monologo;
											y=4;
										}else {
											System.out.println("Opcion incorrecta, vuelve a intentarlo ");
											System.out.println("Selecciona una: Concierto [1] Obra [2] Monologo [3]");
											y=entradaInt.nextInt();
										}
									}while(y>=0 && y<=2 );
									GE.updateEspectaculoP(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFecha, opcionUpdate);
								break;
								case 4:
									System.out.println("Nuevo aforo");
									nuevoAforo=entradaInt.nextInt();
									GE.updateEspectaculoP(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFecha, opcionUpdate);
								break;
								case 5:
									System.out.println("Nueva vendidas");
									nuevasVendiddas=entradaInt.nextInt();
									GE.updateEspectaculoP(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFecha, opcionUpdate);
								break;
								case 6:
									System.out.println("Nueva fecha, el formato es yyyy-mm-dd ");
									fechaAux=entradaD.nextLine();
									nuevaFecha=Date.valueOf(fechaAux);
									GE.updateEspectaculoP(ab, nuevoTitulo, nuevaDescripcion, nuevaCategoria, nuevoAforo, nuevasVendiddas, nuevaFecha, opcionUpdate);
								break;
							}
						}else {
							System.out.println("Ha ocurrido un fallo");
						}	
					break;
					case 4:
						
						ArrayList<String>w=GE.entradasDisponibles();
						System.out.println(w);
						
					break;
					
				}
				
			}while(op>=1 && op<=4);
		}
		
		else if (tipo.equals("0")){			
			do {
				System.out.println("Selecciona una opcion");
				System.out.println("1:Ver entradas disponibles para un espectaculo");
				System.out.println("2:Buscar un espectaculo por titulo o categoria");
				System.out.println("3:Buscar proximos espectaculo");
				System.out.println("4:Publicar una critica");
				System.out.println("5:Ver criticas");
				System.out.println("6:Eliminar una critica");
				System.out.println("7:Valorar una critica");
				System.out.println("Otro:Salir");
				op=entradaInt.nextInt();
				
				switch(op) {
					case 1:
						Date dateAux;
						String dateAux1, tit;
						System.out.println("Dime el espectaculo: ");
						tit=entradaSt.nextLine();
						System.out.println("Dime la fecha, el formato es yyyy-mm-dd: ");
						dateAux1=entradaD.nextLine();
						dateAux=Date.valueOf(dateAux1);
						if(GE.existeEspectaculo(tit)) {
							System.out.println(GE.localidadesDisponibles(tit, dateAux));
						}
						else {
							System.out.println("El titulo no exise");
						}
					break;
					case 2:
						System.out.println("Como deseas buscar por titulo [0] o por categoria [1]");
						int k=entradaInt.nextInt();
						if(k==0) {
							System.out.println("Escribe el titulo");
							String espec1=entradaSt.nextLine();
							if(GE.existeEspectaculo(espec1)) {
								System.out.println(GE.imprimirEspectaculoTitulo(espec1));
							}
							else {
								System.out.println("El espectaculo no existe");
							}
						}
						else if(k==1) {
							System.out.println("Por que categoria quieres ver obra [0] monologo [1] concierto [2]");
							int p=entradaInt.nextInt();
							if(p==0) {
								System.out.println(GE.imprimirEspectaculoCategoria(categoria.obra));
								p=4;
							}else if(p==1) {
								System.out.println(GE.imprimirEspectaculoCategoria(categoria.monologo));
								p=4;
							}else if(p==2) {
								System.out.println(GE.imprimirEspectaculoCategoria(categoria.concierto));
								p=4;
							}else {
								System.out.println("Opcion incorrecta ");
							}
						}
						else {
							System.out.println("La opion introducida es incorrecta");
						}
						
					break;
					case 3://Acabar falta cosa
						java.sql.Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						System.out.println("Fecha actual: "+ fecha);
						ArrayList<String> lt1= new ArrayList<String>();
						categoria cataux=null;
						System.out.println("Quieres filtrar por categoria S/N");
						String v=entradaSt.nextLine();
						if(v.equals("S") || v.equals("s")) {
							System.out.println("Por que categoria quieres filtrar");
							int p;
							do {
								System.out.println("Obra [0] monologo [1] concierto [2]");
								p=entradaInt.nextInt();
								if(p==0) {
									cataux=(categoria.obra);
									p=4;
								}else if(p==1) {
									cataux=(categoria.monologo);
									p=4;
								}else if(p==2) {
									cataux=(categoria.concierto);
									p=4;
								}else {
									System.out.println("Opcion incorrecta, prueba otra vez ");
								}
							}while(p>=0 && p<=2);
							lt1=GE.entradasDisponiblesCategoria(fecha, cataux);
							for(int i=0;i<lt1.size();i++) {
								System.out.println(lt1.get(i));
							}
						}else if(v.equals("n") || v.equals("N")) {
							System.out.println("Los proximos espectaculos son:");
							lt1=GE.proximosEspectaculos(fecha);
							for(int i=0;i<lt1.size();i++) {
								System.out.println(lt1.get(i));
							}
						}else {
							System.out.println("Opcion invalida");
						}
						
					break;
					case 4:
						
						System.out.println("Titulo de la critica:\n");
						tituloCrear=entradaSt.nextLine();
						
						if(GC1.existeTitulo(tituloCrear)) {
							System.out.println("Titulo ya utilizado \n");
						}else {
							System.out.println("Espectaculo: \n");
							espectaculoCrear=entradaSt.nextLine();
							if(GE.existeEspectaculo(espectaculoCrear)) {
								float puntuacion;
								System.out.println("Puntuacion del 0 al 5:\n");
								puntuacion=Float.parseFloat(entradaSt.nextLine());
								if(puntuacion>5 || puntuacion<0) {
									do {
									System.out.println("Rango inadecuado, repite \n");
									puntuacion=Float.parseFloat(entradaSt.nextLine());
									}while(puntuacion>5 || puntuacion<0);
								}
								puntuacionCrear=Float.toString(puntuacion);
								System.out.println("Rese�a:\n");
								reviewCrear=entradaSt.nextLine();
								if(GC1.crearCritica(tituloCrear,espectaculoCrear,puntuacionCrear,reviewCrear,correoInicioSesion,"0")==false) {
									System.out.println("No se creo la critica");
								}
								
								
							}else {
								System.out.println("El espectaculo no existe");
							}
							
						}
						
						
					break;
					case 5:
						ArrayList<Criticas>LC1=new ArrayList<Criticas>();
						LC1=GC1.verCriticas();
						int cont=1;
						
						for(int i=0;i<LC1.size();i++) {
							System.out.println("Critica "+cont+": " +LC1.get(i).getTitulo());
							cont++;
						}
						System.out.println("Introduzca el titulo a ver");
						String aux;
						aux=entradaSt.nextLine();
						int a=0;
						for(int i=0;i<LC1.size();i++) {
							if(aux.equals(LC1.get(i).getTitulo())) {
								System.out.println("Titulo: "+ LC1.get(i).getTitulo());
								System.out.println("Espectaculo: "+ LC1.get(i).getEspectaculo());
								System.out.println("Puntuacion: "+ LC1.get(i).getPuntuacion());
								System.out.println("Review: "+ LC1.get(i).getReview());
								System.out.println("Autor: "+LC1.get(i).getAutor());
								System.out.println("Valoraciones: "+ LC1.get(i).getValoraciones());
								System.out.println("\n");
								a=1;
							}
						}
						if(a==0) {
							System.out.println("La critica que deseas ver no existe \n");
						}
					break;
					case 6:
						ArrayList<Criticas>LC2=new ArrayList<Criticas>();
						LC2=GC1.verCriticas();
						System.out.println("Que critica quieres borrar?\n");
						for(int i=0; i< LC2.size(); i++) {
							if(correoInicioSesion.equals(LC2.get(i).getAutor())){
								System.out.println(LC2.get(i).getTitulo()+"\n");
							}
						}
						String titulo;
						titulo=entradaSt.nextLine();
						GC1.borraCritica(titulo,correoInicioSesion);
					break;
					case 7:
						ArrayList<Criticas>LCUser=new ArrayList<Criticas>();
						
						ArrayList<Criticas>LC4=new ArrayList<Criticas>();
						LC4=GC1.verCriticas();
						if(LC4.size()==0){
							System.out.println("No hay criticas disponibles para votar");
							
							
							
						}else {
							System.out.println("Que critica quieres votar?\n");
							for(int i=0; i< LC4.size(); i++) {
								if(!correoInicioSesion.equals(LC4.get(i).getAutor())){
									LCUser.add(LC4.get(i));
								}
							}
							if(LCUser.size()==0) {
								System.out.println("No hay criticas de otros autores");
							}else {
								for(int i=0; i< LCUser.size(); i++) {
									System.out.println("- "+LCUser.get(i).getTitulo());
								}
								String titulo3;
								titulo3=entradaSt.nextLine();
								
								float puntuacion;
								System.out.println("Valoracion del 0 al 5:\n");
								puntuacion=entradaF.nextFloat();
								if(puntuacion>5 || puntuacion<0) {
									do {
									System.out.println("Rango inadecuado, repite \n");
									puntuacion=Float.parseFloat(entradaF.nextLine());
									}while(puntuacion>5 || puntuacion<0);
								}
								
								GC1.votarCritica(correoInicioSesion, puntuacion,titulo3);
							}
							
						}

					break;
				}
			}while(op>=1 && op<=7);
		}
		
		else {
			System.out.println("Ha habido un problema iniciando sesion \n");
			System.exit(0);
		}
		
		System.out.println("�Saliendo del programa!\n");
		System.exit(0);
	}

}