package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AppTest {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
//	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver024 = "D:\\One Drive\\OneDrive - Universidad de Oviedo\\SDI\\Lab\\PL-SDI-Sesion5-material\\"
//			+ "geckodriver024win64.exe";
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\luisc\\Desktop\\SDI\\geckodriver024win64.exe";

	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	} // Al finalizar la última prueba

	@AfterClass
	static public void end() {
		// //Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// Requisitos obligatorios

	// Público: registrare como usuario

	/**
	 * [Prueba1] Registro de Usuario con datos válidos.
	 */
	@Test
	public void PR01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "marcos@gmail.com", "Marcos Emilio", "Escobar Gaviria", "123456", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "marcos@gmail.com");

	}

	/**
	 * [Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	 * apellidos vacíos).
	 */

	@Test
	public void PR02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.

		PO_RegisterView.fillForm(driver, "", "Jose Manuel", "Perez", "77777", "77777");
		// COmprobamos el error de Nombre corto .
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "marcos@gmail.com", "", "Perez", "77777", "77777");
		// Comprobamos el errror de Apellido corto
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "marcos@gmail.com", "Josefo", "", "77777", "77777");
		// Comprobamos el errror de contraseña corto
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}

	/**
	 * [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña
	 * inválida).
	 */

	@Test
	public void PR03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "marcos@gmail.com", "Josefo", "Perez", "77777", "77778");
		// Comprobamos el errror de contraseña no coincide
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());

	}

	/**
	 * [Prueba4] Registro de Usuario con datos inválidos (email existente).
	 */
	@Test
	public void PR04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "marcos@gmail.com", "Josefo", "Perez", "77777", "77777");
		// Comprobamos el error del correo ya existente
		PO_RegisterView.checkKey(driver, "Error.signup.mail.duplicate", PO_Properties.getSPANISH());

	}

	// Usuario registrado: inicio de sesión
	/**
	 * [Prueba5] Inicio de sesión con datos válidos (administrador).
	 */

	@Test
	public void PR05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "admin@email.com");

	}

	/**
	 * [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
	 */
	@Test
	public void PR06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");
	}

	/**
	 * [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email
	 * y contraseña vacíos).
	 * 
	 */
	@Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "123456");
		// Comprobamos el error del correo ya existente
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());

		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "");
		// Comprobamos el error del correo ya existente
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

	/**
	 * [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email
	 * existente, pero contraseña incorrecta).
	 */
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "0");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_RegisterView.checkKey(driver, "Error.login", PO_Properties.getSPANISH());
	}

	// Usuario Registrado: Fin de sesión

	/**
	 * [Prueba9] Hacer click en la opción de salir de sesión y comprobar que se
	 * redirige a la página de inicio de sesión (Login)
	 */

	@Test
	public void PR09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		PO_View.checkElement(driver, "text", "Identifícate");

	}

	/**
	 * [Prueba10] Comprobar que el botón cerrar sesión no está visible si el usuario
	 * no está autenticado.
	 */

	@Test
	public void PR10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		SeleniumUtils.textoNoPresentePagina(driver, "Desconectar");
	}

	/////////////////////////////////////////////////////////////////////////////////

	// Listado de usuarios

	/**
	 * [Prueba11] Mostrar el listado de usuarios y comprobar que se muestran todos
	 * los que existen en el sistema.
	 */
	@Test
	public void PR11() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/user/list");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 5);

		driver.get("http://localhost:8090/user/list?page=1");

		elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 5);

		driver.get("http://localhost:8090/user/list?page=2");

		elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 3);

	}

	// Buscar Usuarios

	/**
	 * [Prueba12] Hacer una búsqueda con el campo vacío y comprobar que se muestra
	 * la página que corresponde con el listado usuarios existentes en el sistema.
	 */

	@Test
	public void PR12() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/user/list");

		WebElement boton = driver.findElement(By.id("botonBusqueda"));
		boton.click();

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 5);

		driver.get("http://localhost:8090/user/list?page=1");

		elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 5);

		driver.get("http://localhost:8090/user/list?page=2");

		elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 3);

	}

	/**
	 * [Prueba13] Hacer una búsqueda escribiendo en el campo un texto que no exista
	 * y comprobar que se muestra la página que corresponde, con la lista de
	 * usuarios vacía.
	 */

	@Test
	public void PR13() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/user/list");

		WebElement campo = driver.findElement(By.name("searchText"));
		WebElement boton = driver.findElement(By.id("botonBusqueda"));

		campo.sendKeys("Mariano Rajoy");
		boton.click();

		assertTrue(PO_View.checkElementNotExist(driver, "//tbody/tr"));

	}

	/**
	 * [Prueba14] Hacer una búsqueda con un texto específico y comprobar que se
	 * muestra la página que corresponde, con la lista de usuarios en los que el
	 * texto especificados sea parte de su nombre, apellidos o de su email.
	 */

	@Test
	public void PR14() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/user/list");

		WebElement campo = driver.findElement(By.name("searchText"));
		WebElement boton = driver.findElement(By.id("botonBusqueda"));

		campo.sendKeys("Pedro");
		boton.click();
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");
		PO_View.checkElement(driver, "text", "Pedro");
		PO_View.checkElement(driver, "text", "Díaz");

	}

	// Enviar invitación de amistad

	/**
	 * [Prueba15] Desde el listado de usuarios de la aplicación, enviar una
	 * invitación de amistad a un usuario. Comprobar que la solicitud de amistad
	 * aparece en el listado de invitaciones (punto siguiente).
	 */

	@Test
	public void PR15() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		driver.get("http://localhost:8090/user/list");

		WebElement campo = driver.findElement(By.name("searchText"));
		WebElement boton = driver.findElement(By.id("botonBusqueda"));

		campo.sendKeys("Pepe");
		boton.click();

		WebElement botonEnviar = driver.findElement(By.id("enviar"));
		botonEnviar.click();

	}

	/**
	 * [Prueba16] Desde el listado de usuarios de la aplicación, enviar una //
	 * invitación de amistad a un usuario al // que ya le habíamos enviado la
	 * invitación previamente. No debería dejarnos // enviar la invitación, se
	 * podría // ocultar el botón de enviar invitación o notificar que ya había sido
	 * enviada // previamente.
	 * 
	 */
	@Test
	public void PR16() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		driver.get("http://localhost:8090/user/list");

		WebElement campo = driver.findElement(By.name("searchText"));
		WebElement boton = driver.findElement(By.id("botonBusqueda"));

		campo.sendKeys("Pepe");
		boton.click();

		SeleniumUtils.esperarSegundos(driver, 5);

		PO_View.checkElement(driver, "text", "Enviada");

	}

	// Listar las invitaciones de amistad recibidas

	/**
	 * [Prueba17] Mostrar el listado de invitaciones de amistad recibidas. Comprobar
	 * con un listado que contenga varias invitaciones recibidas.
	 */

	@Test
	public void PR17() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		driver.get("http://localhost:8090/invitation/list");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 4);

	}

	// Aceptar invitación

	/**
	 * [Prueba18] Sobre el listado de invitaciones recibidas. Hacer click en el
	 * botón/enlace de una de ellas y comprobar que dicha solicitud desaparece del
	 * listado de invitaciones.
	 */

	@Test
	public void PR18() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pepe@gmail.com", "123456");

		driver.get("http://localhost:8090/invitation/list");

		WebElement botonEnviar = driver.findElement(By.id("aceptar"));
		botonEnviar.click();

		driver.get("http://localhost:8090/invitation/list");

		assertTrue(PO_View.checkElementNotExist(driver, "//tbody/tr"));

	}

	// Listado de amigos
	/**
	 * [Prueba19] Mostrar el listado de amigos de un usuario. Comprobar que el
	 * listado contiene los amigos que deben ser.
	 */

	@Test
	public void PR19() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		driver.get("http://localhost:8090/invitation/list");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		for (int i = 0; i < elementos.size(); i++) {
			WebElement boton = driver.findElement(By.id("aceptar"));
			if (boton != null) {
				boton.click();
				driver.get("http://localhost:8090/invitation/list");
			}
		}

		driver.get("http://localhost:8090/user/friends");

		elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 4);

	}

	// Internacionalización de todas las vistas

	/**
	 * [Prueba20] Visualizar al menos cuatro páginas en Español/Inglés/Español
	 * (comprobando que algunas de las etiquetas cambian al idioma
	 * correspondiente).Ejemplo, Página principal/Opciones Principales de
	 * Usuario/Listado de Usuarios.
	 */

	@Test
	public void PR20() {
		// index página 1

		PO_View.checkKey(driver, "welcome.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "language.change", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "login.message", PO_Properties.getSPANISH());

		PO_NavView.changeIdiom(driver, "btnEnglish");

		PO_View.checkKey(driver, "welcome.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "language.change", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "signup.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "login.message", PO_Properties.getENGLISH());

		PO_NavView.changeIdiom(driver, "btnSpanish");

		PO_View.checkKey(driver, "welcome.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "language.change", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "login.message", PO_Properties.getSPANISH());

		// login página 2

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		PO_View.checkKey(driver, "login.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "login.email", PO_Properties.getSPANISH());

		PO_NavView.changeIdiom(driver, "btnEnglish");

		PO_View.checkKey(driver, "login.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "login.email", PO_Properties.getENGLISH());

		PO_NavView.changeIdiom(driver, "btnSpanish");

		PO_View.checkKey(driver, "login.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "login.email", PO_Properties.getSPANISH());

		// registro página 3

		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		PO_View.checkKey(driver, "signup.welcome", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.email", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.name", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.lastName", PO_Properties.getSPANISH());

		PO_NavView.changeIdiom(driver, "btnEnglish");

		PO_View.checkKey(driver, "signup.welcome", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "signup.email", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "signup.name", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "signup.lastName", PO_Properties.getENGLISH());

		PO_NavView.changeIdiom(driver, "btnSpanish");

		PO_View.checkKey(driver, "signup.welcome", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.email", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.name", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.lastName", PO_Properties.getSPANISH());

		// lista de usuario página 4

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		driver.get("http://localhost:8090/user/list");
		// user.list.tittle, user.list.long.message, user.list.email, user.list.name
		PO_View.checkKey(driver, "user.list.tittle", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "user.list.long.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "user.list.email", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "user.list.name", PO_Properties.getSPANISH());

		PO_NavView.changeIdiom(driver, "btnEnglish");

		PO_View.checkKey(driver, "user.list.tittle", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "user.list.long.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "user.list.email", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "user.list.name", PO_Properties.getENGLISH());

		PO_NavView.changeIdiom(driver, "btnSpanish");

		PO_View.checkKey(driver, "user.list.tittle", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "user.list.long.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "user.list.email", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "user.list.name", PO_Properties.getSPANISH());

	}

	// Seguridad

	/**
	 * [Prueba21] Intentar acceder sin estar autenticado a la opción de listado de
	 * usuarios. Se deberá volver al formulario de login.
	 */

	@Test
	public void PR21() {
		// http://localhost:8090/user/list
		try {
			driver.get("http://localhost:8090/user/list");
		} catch (Exception e) {

			PO_View.checkElement(driver, "text", "Identificate");
		}
	}

	// [Prueba22] Intentar acceder sin estar autenticado a la opción de listado de
	// publicaciones de un usuario estándar. Se deberá volver al formulario de
	// login.

	@Test
	public void PR22() {
		// http://localhost:8090/user/list
		try {
			driver.get("http://localhost:8090/post/list");
		} catch (Exception e) {

			PO_View.checkElement(driver, "text", "Identificate");
		}

	}

	/**
	 * [Prueba23] Estando autenticado como usuario estándar intentar acceder a una
	 * opción disponible solo para usuarios administradores (Se puede añadir una
	 * opción cualquiera en el menú). Se deberá indicar un mensaje de acción
	 * prohibida
	 */
	@Test
	public void PR23() {
		// Logeo
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/user/delete/1");

		PO_View.checkElementNotExist(driver, "Forbidden");

	}

	// Crear una nueva publicación

	/**
	 * [Prueba24] Ir al formulario crear publicaciones, rellenarla con datos válidos
	 * y pulsar el botón Submit. Comprobar que la publicación sale en el listado de
	 * publicaciones de dicho usuario.
	 */

	@Test
	public void PR24() {
		// Logeo
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/post/add");

		WebElement campoTitulo = driver.findElement(By.id("campoTitulo"));
		campoTitulo.sendKeys("Titulo de prueba");

		WebElement campoPost = driver.findElement(By.id("campoPost"));
		campoPost.sendKeys("Texto de prueba");

		WebElement botonEnviar = driver.findElement(By.id("enviar"));
		botonEnviar.click();

		PO_View.checkElement(driver, "text", "Titulo de prueba");
		PO_View.checkElement(driver, "text", "Texto de prueba");

	}

	/**
	 * [Prueba25] Ir al formulario de crear publicaciones, rellenarla con datos
	 * inválidos (campo título vacío) y pulsar el botón Submit. Comprobar que se
	 * muestra el mensaje de campo obligatorio.
	 */
	@Test
	public void PR25() {
		// Logeo
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/post/add");

		WebElement campoTitulo = driver.findElement(By.id("campoTitulo"));
		campoTitulo.sendKeys("");

		WebElement campoPost = driver.findElement(By.id("campoPost"));
		campoPost.sendKeys("Texto de prueba");

		WebElement botonEnviar = driver.findElement(By.id("enviar"));
		botonEnviar.click();

		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}

	// Listado de mis publicaciones

	/**
	 * [Prueba26] Mostrar el listado de publicaciones de un usuario y comprobar que
	 * se muestran todas las que existen para dicho usuario.
	 */

	@Test
	public void PR26() {
		// Logeo
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/post/list");

		PO_View.checkElement(driver, "text", "pediaz@gmail.com");
		PO_View.checkElement(driver, "text", "Hola");
		PO_View.checkElement(driver, "text", "Buenas");

	}

	// : Listado de publicaciones de un amigo

	/**
	 * [Prueba27] Mostrar el listado de publicaciones de un usuario amigo y
	 * comprobar que se muestran todas las que existen para dicho usuario.
	 */

	@Test
	public void PR27() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		driver.get("http://localhost:8090/user/list");

		WebElement campo = driver.findElement(By.name("searchText"));
		WebElement boton = driver.findElement(By.id("botonBusqueda"));

		campo.sendKeys("Miguel");
		boton.click();

		WebElement botonEnviar = driver.findElement(By.id("enviar"));
		botonEnviar.click();
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		PO_LoginView.fillForm(driver, "mati@hotmail.com", "123456");

		driver.get("http://localhost:8090/invitation/list");

		WebElement botonac = driver.findElement(By.id("aceptar"));
		botonac.click();

		driver.get("http://localhost:8090/post/list/1");

		PO_View.checkElement(driver, "text", "pediaz@gmail.com");
		PO_View.checkElement(driver, "text", "Hola");
		PO_View.checkElement(driver, "text", "Buenas");

	}

	/**
	 * [Prueba28] Utilizando un acceso vía URL u otra alternativa, tratar de listar
	 * las publicaciones de un usuario que no sea amigo del usuario identificado en
	 * sesión. Comprobar que el sistema da un error de autorización.
	 */
	@Test
	public void PR28() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pediaz@gmail.com");

		driver.get("http://localhost:8090/post/list/2");

		PO_View.checkElement(driver, "text", "Error");

	}

	// : Crear una publicación con una foto adjunta

	/**
	 * [Prueba29] Desde el formulario de crear publicaciones, crear una publicación
	 * con datos válidos y una foto adjunta. Comprobar que en el listado de
	 * publicaciones aparecer la foto adjunta junto al resto de datos de la
	 * publicación.
	 */
	@Test
	public void PR29() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pediaz@gmail.com", "123456");

		driver.get("http://localhost:8090/post/add");

		WebElement campoTitulo = driver.findElement(By.id("campoTitulo"));
		campoTitulo.sendKeys("Titulo de prueba");

		WebElement campoPost = driver.findElement(By.id("campoPost"));
		campoPost.sendKeys("Texto de prueba");

		WebElement selector = driver.findElement(By.id("selector"));
		selector.sendKeys("C:\\mclaren.jpg"); // Aqui va a dar error porque la foto la tengo en local

		WebElement botonEnviar = driver.findElement(By.id("enviar"));
		botonEnviar.click();

		PO_View.checkElement(driver, "text", "Titulo de prueba");
		PO_View.checkElement(driver, "text", "Texto de prueba");

		
		PO_View.checkElement(driver, "free", "//td/div/img");
	}

	/**
	 * [Prueba30] Crear una publicación con datos válidos y sin una foto adjunta.
	 * Comprobar que la publicación se a creado con éxito, ya que la foto no es
	 * obligaría.
	 * 
	 */
	@Test
	public void PR30() {
		// Logeo
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pepe@gmail.com", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "pepe@gmail.com");

		driver.get("http://localhost:8090/post/add");

		WebElement campoTitulo = driver.findElement(By.id("campoTitulo"));
		campoTitulo.sendKeys("Titulo de prueba");

		WebElement campoPost = driver.findElement(By.id("campoPost"));
		campoPost.sendKeys("Texto de prueba");

		WebElement botonEnviar = driver.findElement(By.id("enviar"));
		botonEnviar.click();

		PO_View.checkElement(driver, "text", "pepe@gmail.com");
		PO_View.checkElement(driver, "text", "Titulo de prueba");
		PO_View.checkElement(driver, "text", "Texto de prueba");

	}

	// Administrador: listado de usuarios

	/**
	 * [Prueba31] Mostrar el listado de usuarios y comprobar que se muestran todos
	 * los que existen en el sistema.
	 */

	@Test
	public void PR31() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// COmprobamos que entramos en la pagina privada de Alumno

		driver.get("http://localhost:8090/user/list");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 5);

		driver.get("http://localhost:8090/user/list?page=1");

		elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 5);

		driver.get("http://localhost:8090/user/list?page=2");

		elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		assertTrue(elementos.size() == 4);

	}
}
