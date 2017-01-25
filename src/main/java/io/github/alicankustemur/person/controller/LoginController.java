package io.github.alicankustemur.person.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@RequestScoped
public class LoginController {

	private String username;
	private String password;

	public String login() {

		if (StringUtils.isBlank(username)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "( Gerekli alan )", "Kullanýcý adýnýzý girmediniz!"));
			return null;
		}

		if (StringUtils.isBlank(password)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "( Gerekli alan )", "Parolanýzý girmediniz!"));
			return null;
		}

		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try {
			currentUser.login(token);
		
		} catch (UnknownAccountException uae) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriþ baþarýsýz", "kullanýcý adýnýz yanlýþ"));
			return null;
		} catch (IncorrectCredentialsException ice) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriþ baþarýsýz", "parolanýz yanlýþ"));
			return null;
		} catch (LockedAccountException lae) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriþ baþarýsýz", "Bu kullanýcý adý kilitli"));
			return null;
		} catch (AuthenticationException aex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriþ baþarýsýz", "Kullanýcý adý veya þifre hatalý"));
			return null;
		}
		return "/index.xhtml?faces-redirect=true";
	}

	public String logout() {

		Subject currentUser = SecurityUtils.getSubject();

		try {
			currentUser.logout();
		} catch (Exception ex) {

		}

		return "/login";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
