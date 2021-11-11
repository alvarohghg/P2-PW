package es.uco.pw.display.javabean;

import java.io.Serializable;

import es.uco.pw.display.common.Language;

// Todo javabean implementa la interfaz Serializable
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String login = "";
	private int idUser = -1;
	private Language userLang = Language.Spanish;
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Language getUserLang() {
		return userLang;
	}

	public void setUserLang(Language userLang) {
		this.userLang = userLang;
	}
	
	
}
